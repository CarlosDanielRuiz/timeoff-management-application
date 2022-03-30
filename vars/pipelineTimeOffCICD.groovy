def call(body) {
    pipelineParams = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    taggedImage = pipelineParams.dockerBuildImage ?: 'carlosdruizg/timeoff-app-demo:latest'
    dockerfileName           = pipelineParams.dockerfileName ?: 'Dockerfile'
    dockerBuildContext       = pipelineParams.dockerBuildContext ?: '.'

    pipeline {
        agent any
        environment {
            DOCKER_HUB_CREDS = credentials('DockerHubCredentials')
        }
        stages {
            stage('Docker Hub login') {
                steps {
                    script {
                        //utilDockerLogin dockerLoginUsername = "$DOCKER_HUB_CREDS_USR", dockerLoginPassword = "$DOCKER_HUB_CREDS_PSW"
                        sh 'docker login --username $DOCKER_HUB_CREDS_USR --password $DOCKER_HUB_CREDS_PSW'
                    }
                }
            }
            stage('TimeOff Build - Docker Publish Steps') {
                steps {
                    // Docker Build
                    utilDockerBuild taggedImage: taggedImage, dockerfileName: dockerfileName, dockerBuildContext: dockerBuildContext
                    // Docker Push
                    utilDockerPush taggedImage: taggedImagee
                }
            }
            stage('TimeOff Deployment - Docker Compose Update and Clean') {
                steps {
                    withCredentials(bindings: [sshUserPrivateKey(credentialsId: 'Vagrant', \
                                                keyFileVariable: 'SSH_KEY_FOR_VAGRANT')]) {
                    sh'ssh -o "StrictHostKeyChecking=no" -i $SSH_KEY_FOR_VAGRANT vagrant@192.168.100.2 "docker-compose pull && docker-compose up --build --force-recreate -d && docker image prune --force --all"'
                    }
                    // Docker Clean
                    utilDockerRemove taggedImage: taggedImage
                }
            }
        }
    }
}

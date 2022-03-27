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
                    utilAwsEcrAuth dockerRegistryUrl: dockerPullRegistryUrl
                }
            }
            stage('TimeOff Build - Docker Steps') {
                steps {
                    // Docker Login
                    utilDockerLogin dockerLoginUsername = $DOCKER_HUB_CREDS_USR dockerLoginPassword = $DOCKER_HUB_CREDS_PSW
                    // Docker Build
                    utilDockerBuild taggedImage: taggedImage, dockerfileName: dockerfileName, dockerBuildContext: dockerBuildContext
                    // Docker Push
                    utilDockerPush taggedImage: taggedImage
                    // Docker Clean
                    utilDockerRemove taggedImage: taggedImage
                }
            }
            // stage('TimeOff Deployment - Docker Compose Update') {
            //     steps {
            //         // WIP
            //     }
            // }
        }
    }
}

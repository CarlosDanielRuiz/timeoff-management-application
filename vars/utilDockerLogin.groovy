def call(Map library=[:]) {
    // Description: Log in to a Docker registry 
    dockerLoginUsername = library.dockerLoginUsername
    dockerLoginPassword = library.dockerLoginPassword

    sh "docker login --username ${dockerLoginUsername}  --password ${dockerLoginPassword}"
    echo "Login on Docker Hub Succeeded"
}

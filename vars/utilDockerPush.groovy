def call(Map library=[:]) {
    // Description: Push Docker image to the registry you are logged in
    taggedImage = library.taggedImage
    dockerPushOptions = library.dockerPushOptions == null ? '' : library.dockerPushOptions

    sh "docker push ${dockerPushOptions} ${taggedImage}"
    echo "The Docker ${taggedImage} image was pushed"
}

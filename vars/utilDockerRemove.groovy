def call(Map library=[:]) {
    // Description: Remove one or more images
    taggedImage = library.taggedImage
    dockerRemoveOptions = library.dockerRemoveOptions == null ? '' : library.dockerRemoveOptions

    sh "docker rmi --force ${dockerRemoveOptions} ${taggedImage}"
    echo "The Docker ${taggedImage} image was deleted"
}

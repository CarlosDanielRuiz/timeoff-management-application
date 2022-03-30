def call(Map library=[:]) {
    // Description: Builds a Docker image from a Dockerfile and a “context" without using any cache and forcing download the latest base version
    taggedImage = library.taggedImage
    dockerfileName = library.dockerfileName ?: 'Dockerfile'
    dockerBuildContext = library.dockerBuildContext ?: '.'
    dockerBuildOptions = library.dockerBuildOptions == null ? '' : library.dockerBuildOptions

    sh "docker build --pull --no-cache -f ${dockerfileName} ${dockerBuildOptions} ${dockerBuildContext} -t ${taggedImage}"
    echo "The Docker ${taggedImage} image was built"
}

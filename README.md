# TimeOff.Management - Release Manager Challenge
Web application for managing employee absences with automatic deployment using VMware, Vagrant, Ansible, Jenkins and GitHub.

- [Requirements](#requirements)
- [TimeOff.Management Application](#timeoffmanagement-application)
    - [Building the Docker Image](#building-the-docker-image)
    - [Running the Docker Image](#running-the-docker-image)

## Requirements
- Docker >= 20.10.13 (For local testing of [timeoff-app](timeoff-app/README.md))

## TimeOff.Management Application
#### Building the Docker Image
```bash
docker build --pull --no-cache -f timeoff-app/Dockerfile -t timeoff:latest timeoff-app/
```
> Note: Docker flags `--pull --no-cache` are used to prevent any cache from base image and Docker layer during local testing.

#### Running the Docker Image
```bash
docker run --rm -d -p 3000:3000 --name alpine_timeoff timeoff
```
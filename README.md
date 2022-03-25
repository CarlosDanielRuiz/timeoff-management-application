# TimeOff.Management - Release Manager Challenge
Web application for managing employee absences with automatic deployment using VMware, Vagrant, Ansible, Jenkins and GitHub.

- [Requirements](#requirements)
- [TimeOff.Management Application](#timeoffmanagement-application)
    - [Building the Docker Image](#building-the-docker-image)
    - [Running the Docker Image](#running-the-docker-image)

## Architecture
![Architecture](docs/imgs/architecture.jpg)

---
## Requirements
- Docker >= 20.10.13 (For [timeoff-app](timeoff-app/README.md) local testing)
- VMware Fusion >= 12.1.0
- Vagrant >= 2.2.19

## TimeOff.Management Application
#### Building the Docker Image
```bash
docker build --pull --no-cache -f timeoff-app/Dockerfile -t timeoff:latest timeoff-app/
```
> **Note:** Docker flags `--pull --no-cache` are used to prevent any cache from base image and Docker layer during local testing.

#### Running the Docker Image
```bash
docker run --rm -d -p 3000:3000 --name alpine_timeoff timeoff
```

## Vagrant
#### Deployment with Vagrant
Using the Vagrantfile you just need to `vagrant up` and everything is installed and configured for you to work.

#### Destroy Vagrant running machine
This command stops the running machine Vagrant is managing and destroys all resources that were created during the machine creation process. 
```bash
vagrant destroy --force debian10
```

## Ansible
For manual Ansible execution and testing.
```bash
cd ansible
ansible-playbook docker-install.yml
ansible-playbook run-timeoff-container.yml
```
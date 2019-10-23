# Docker Demonstration of two Docker containers communicating

## Course
https://www.udemy.com/docker-tutorial-for-devops-run-docker-containers

## Instructions
The instructions below refer to files in the spring-boot-redis folder

## Container 1
* Redis
* docker pull redis:3.2.0 (runs redis on port 6379)

## Container 2
* Spring Boot Web App see build.sh

## Running the two containers
* see rr.sh
* the boot webapp will run via Netbeans boot as well, but will look for the
dockerlocal to find the redis server
* this is overrun by the environment variable to point to the link name

## Communication between the containers
* --link points to the name of the redis-container
* this turns the name into the host_name for the redis server
* the spring boot env variable sets boot to override the redis_host variable in app props


## Link command places host entries
* the link command places a host entry into the etc/hosts file of
the source container. You can view assigned internal ips using

docker inspect <sha> | grep IP

## Docker Compose
* dup.sh illustrates docker compose for the redis server and the boot client startup
* build is done prior to docker compose command (mvn and docker-compose)
* the env variable issue is set in the docker-compose.yml file
* the volumes command in the docker-compose file maps the docs folder to app folder inside the container
* ddown.sh will bring the containers down and delete them, also removes any volume mappings
* a .env file (at the same location as the yml file) can be used for global env variables that can be substituted in the yml file via ${VAR_NAME}
* a separate file can be specified with variables only available to the container (env_file: .env_fred) these will be specific to the container

## Docker Compose Commands

* docker-compose ps (see all services under the current docker-compose)
* docker-compose logs (-f) to follow
* docker-compose logs <container> -f
* docker-compose stop (to stop with out delete)
* docker-compose start (to restart)
* docker-compose rm to remove the containers
  
## Docker Registry Folder

Docker compose file for docker registry

## Jenkins Server Folder

Docker compose file for Jenkins server, Jenkins slave and Docker Registry

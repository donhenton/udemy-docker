# Docker Demonstration of two Docker container communicating


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
* build is done prior to docker compose command
* the env variable issue is set in the docker-compose.yml file
* the volumes command in the docker-compose file maps the docs folder to app folder inside the container
* ddown.sh will bring the containers down and delete them, also removes any volume mappings
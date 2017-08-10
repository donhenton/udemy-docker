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

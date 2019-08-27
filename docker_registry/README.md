# DOCKER REGISTRY

<https://code-maze.com/docker-hub-vs-creating-docker-registry/>
<https://github.com/docker/distribution/issues/2862> (use of env variable for relative urls)

## Starting the registry
```docker-compose up -d``` in the folder with the docker-compose.yml file

## Stopping the registry
```docker-compse down``` in the folder with the docker-compose.yml file

## Url for the repository

<http://localhost:50000/v2/_catalog>

## Configuring

* add an entry to /etc/hosts for my-registry -> 127.0.0.1

## Pushing images

* tag your local image that you want to push with the info to the local registry:
* ```docker tag java:latest my-registry:50000/javaimages/java:latest```
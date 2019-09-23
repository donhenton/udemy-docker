# JENKINS SERVER

## Starting Docker Compose

* ```docker-compose up -d```
* jenkins username: admin 
* password: admin

## Pipeline for Docker Build

<https://medium.com/@gustavo.guss/jenkins-building-docker-image-and-sending-to-registry-64b84ea45ee9>

## Steps for the creating the build pipeline in Jenkins

* New Item
* Pipeline
* Script as below

## Groovy pipeline for cloning, building, pushing the image

```groovy
pipeline {
  environment {
    registry = "my-registry:5000"
    GIT_SSL_NO_VERIFY = 1
    NODE_TLS_REJECT_UNAUTHORIZED=0
    dockerImage = ''
  }
  agent any
  stages {
    stage('Cloning Git') {
      steps {
        sh 'git config --global http.sslVerify "false"'
        git 'https://github.com/donhenton/node-docker-simple.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build(registry + "/donhenton/node-docker-simple:$BUILD_NUMBER")
        }
      }
    }
    stage('Deploy Image') {
      steps{
        script {
          docker.withRegistry('http://' + registry ) {
            dockerImage.push()
          }
        }
      }
    }
    stage('Remove Unused docker image') {
      steps{
        sh "docker rmi $registry/donhenton/node-docker-simple:$BUILD_NUMBER"
      }
  }
 }
}
```

The various variables used to disable TLS will not be needed in an environment where TLS is handled properly
The github repo docker file also has a number of SSL check disablers that should be removed as well


## Other Pipelines

```groovy
pipeline {
    agent {
        label '!windows'
    }

    environment {
        DISABLE_AUTH = 'true'
        DB_ENGINE    = 'sqlite'
        GIT_SSL_NO_VERIFY=1
    }

    stages {
        stage('Build') {
            steps {
                echo "Database engine is ${DB_ENGINE}"
                echo "DISABLE_AUTH is ${DISABLE_AUTH}"
                echo "SSL VERIFY is ${GIT_SSL_NO_VERIFY}"
                sh 'printenv'
            }
        }
    }
}
```


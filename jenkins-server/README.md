# JENKINS SERVER

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
        git 'https://github.com/donhenton/node-todo-frontend.git'
      }
    }
    stage('Building image') {
      steps{
        script {
          dockerImage = docker.build(registry + "/gustavo/todo:$BUILD_NUMBER")
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
        sh "docker rmi $registry/gustavo/todo:$BUILD_NUMBER"
      }
  }
 }
}
```

The various variables used to disable TLS will not be needed in an environment where TLS is handled properly
The github repo docker file also has a number of SSL check disablers that should be removed as well

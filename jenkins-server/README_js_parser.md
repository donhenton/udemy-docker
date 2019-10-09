# Jenkins Pipeline for JS-Parser

https://github.com/donhenton/js-parser.git

https://hackernoon.com/running-karma-tests-with-headless-chrome-inside-docker-ae4aceb06ed3
https://dzone.com/articles/how-to-run-karma-tests-in-a-docker-container


```groovy
def gitVar = "http.sslVerify"

pipeline {
  environment {
    GIT_SSL_NO_VERIFY = 1
    NODE_TLS_REJECT_UNAUTHORIZED=0
    GIT_VAR="http.sslVerify"
  }
  agent {
        docker {
            image 'donhenton/docker-gulp-sass-node' 
        }
    }
stages {

        stage('Cloning Git') {
            
            steps {
                sh 'git config --global http.sslVerify "false"'

                git 'https://github.com/donhenton/js-parser.git'
            }
        }
        stage('npm install') {
            steps {
                    sh 'npm config set strict-ssl false'
                    sh 'npm install'
                }
            }
        stage('run tests') {
            steps {
                    
                    sh 'gulp test'
                }
            }
    }


}

```
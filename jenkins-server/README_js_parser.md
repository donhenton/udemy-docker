# Jenkins Pipeline for JS-Parser

https://github.com/donhenton/js-parser.git
https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-linux-x86_64.tar.bz2
https://hackernoon.com/running-karma-tests-with-headless-chrome-inside-docker-ae4aceb06ed3
https://dzone.com/articles/how-to-run-karma-tests-in-a-docker-container
https://github.com/karma-runner/karma-chrome-launcher/issues/189

## SSL
in the agent section, if you add 
```args  '-v /Users/<username>/ssl-installs/ssl-certs:/etc/ssl/certs'```
will allow the instance to have certs present
in the mapped folder there should be a crt and pem version of the bundle



```groovy
pipeline {
  environment {
    NONSENSE=1

  }
  agent {
        docker {
            image 'donhenton/docker-gulp-sass-node'
            args  '-v /Users/<username>/ssl-installs/ssl-certs:/etc/ssl/certs'
        }
    }
stages {

        stage('Cloning Git') {
            steps {
                git 'https://github.com/donhenton/js-parser.git'
            }
        }
        stage('npm install') {
            steps {
                    sh 'npm set cafile /etc/ssl/certs/ca-bundle.pem'
                    sh 'npm install'
                }
            }
        stage('run tests') {
            steps {
                    sh 'gulp test'
                }
            }
    }
//https://bitbucket.org/ariya/phantomjs/downloads/phantomjs-2.1.1-linux-x86_64.tar.bz2

}

```
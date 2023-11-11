pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-leonardofacens	')
  }
  tools { 
    maven 'Maven 3.8.3' 
    jdk 'jdk17' 
  }
  stages {
    stage("Verify tooling") {
      steps {
        script {
          if (isUnix()) {
            sh '''
              timedatectl
              docker version
              docker info
              docker compose version 
              curl --version
            '''
          } else {
            bat '''
              docker version
              docker info
              docker compose version 
              curl --version
            '''
          }
        }
      }
    }
    stage('Prune Docker data') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker system prune -a --volumes -f'
          } else {
            bat 'docker system prune -a --volumes -f'
          }
        }
      }
    }
    stage('Test and Build') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn clean package'     
          } else {
            bat 'mvn clean package' 
          }
        }
      }
      post {
        success {
          junit 'target/surefire-reports/**/*.xml' 
        }
      }
    }
    stage('Build Docker image') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker build -t leonardofacens/atdd-devops-e-qas:latest .'
          } else {
            bat 'docker build -t leonardofacens/atdd-devops-e-qas:latest .'
          }
        }
      }
    }
    stage('Docker Login') {
      steps {
        script {
          if (isUnix()) {
            sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
          } else {
            bat 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
          }
        }
      }
    }
    stage('Docker Push') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker push leonardofacens/atdd-devops-e-qas:latest'
          } else {
            bat 'docker push leonardofacens/atdd-devops-e-qas:latest'
          }
        }
      }
    }
  }
  post {
    always {
      script {
        if (isUnix()) {
          sh 'docker logout'
        } else {
          bat 'docker logout'
        }
      }
    }
  }
}
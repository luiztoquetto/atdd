pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-leonardofacens	')
  }
  stages {
    stage("Verify tooling") {
      steps {
        script {
          if (isUnix()) {
            sh '''
              sh date
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
    stage('Start container') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker compose up -d --no-color --wait'
            sh 'docker compose ps'
          } else {
            bat 'docker compose up -d --no-color --wait'
            bat 'docker compose ps'
          }
        }
      }
    }
    stage('Run tests against the container (GET /cursos)') {
      steps {
        script {
          if (isUnix()) {
            sh 'curl http://localhost:9090/cursos'
          } else {
            bat 'curl http://localhost:9090/cursos'
          }
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
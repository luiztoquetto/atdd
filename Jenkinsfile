pipeline {
  agent any
  stages {
    stage("verify tooling") {
      steps {
        script {
          if (isUnix()) {
            sh '''
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
    stage('Run tests against the container') {
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
  }

}
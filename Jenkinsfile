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
    stage('Test and Build') {
      steps {
        script {
          if (isUnix()) {
            sh 'mvn clean package install'     
          } else {
            bat 'mvn clean package install' 
          }
        }
        junit 'target/surefire-reports/**/*.xml' 
        step([ 
          $class: 'JacocoPublisher',
          changeBuildStatus: true,
          minimumInstructionCoverage: '80',
          maximumInstructionCoverage: '90',
          sourcePattern: '**/src',
          inclusionPattern: '**/*.class',
          exclusionPattern: '**/infra/repositorios/**/*,**/presenter/dtos/**/*, **/AtddApplication.*',
          sourceExclusionPattern: '**/infra/repositorios/**/*,**/presenter/dtos/**/*, **/AtddApplication.*'
        ])
        script {
          if (currentBuild.result == 'FAILURE') {
              error("A cobertura de testes está baixa!")
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
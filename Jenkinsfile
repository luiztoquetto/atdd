pipeline {
  agent any
  environment {
    DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials-leonardofacens	')
    AZURE_CREDENTIALS = credentials('f8e4af6e-217b-4309-8ba9-10180e77d7d2')
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
          exclusionPattern: '**/infra/repositorios/**/*,**/presenter/dtos/**/*, **/AtddApplication.*,**/ServletInitializer.*,**/Module.java',
          sourceExclusionPattern: '**/infra/repositorios/**/*,**/presenter/dtos/**/*, **/AtddApplication.*, **/ServletInitializer.*,**/Module.java'
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
    stage('Staging - Prune Docker volume') {
      steps {
        script {
          if (isUnix()) {
            sh 'docker volume prune --filter "label=com.docker.compose.project=atdd" -f'
          } else {
            bat 'docker volume prune --filter "label=com.docker.compose.project=atdd" -f'
          }
        }
      }
    }
    stage('Staging - Run') {
      steps {
        script {
          if (isUnix()) {
            sh """
              export AMBIENTE=homolog 
              docker compose up -d --no-color --wait
              docker compose ps
            """
          } else {
            bat 'AMBIENTE=homolog docker compose up -d --no-color --wait'
            bat 'docker compose ps'
          }
        }
      }
    }
    stage('Staging - Get Cursos') {
      steps {
        sleep(time:10,unit:"SECONDS")
        script {
          if (isUnix()) {
            sh 'curl http://localhost:9090/cursos'
          } else {
            bat 'curl http://localhost:9090/cursos'
          }
        }
      }
    }
    stage('Prod - Deploy (restarting azure app service)') {
      steps {
        script {
          if (isUnix()) {
            sh 'az login -u $AZURE_CREDENTIALS_USR -p $AZURE_CREDENTIALS_PSW'
            sh 'az webapp restart --name facens-devops-qa-ac2 --resource-group facens-devops-qa-ac2'
          } else {
            bat 'az login -u $AZURE_CREDENTIALS_USR -p $AZURE_CREDENTIALS_PSW'
            bat 'az webapp restart --name facens-devops-qa-ac2 --resource-group facens-devops-qa-ac2'
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
          sh 'az logout'
        } else {
          bat 'docker logout'
          bat 'az logout'
        }
      }
    }
  }
}
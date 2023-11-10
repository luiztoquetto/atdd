pipeline {
    agent any
    stages {
        stage("verify tooling") {
            steps {
                script {
                    def cmd = isUnix() ? 'sh' : 'bat'
                    catchError(buildResult: 'FAILURE') {
                        echo "Verifying tooling..."
                        ${cmd} '''
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
            parallel {
                stage('Prune') {
                    steps {
                        script {
                            def cmd = isUnix() ? 'sh' : 'bat'
                            catchError(buildResult: 'FAILURE') {
                                echo "Pruning Docker data..."
                                ${cmd} 'docker system prune -a --volumes -f'
                            }
                        }
                    }
                }
                stage('Start container') {
                    steps {
                        script {
                            def cmd = isUnix() ? 'sh' : 'bat'
                            catchError(buildResult: 'FAILURE') {
                                echo "Starting container..."
                                ${cmd} 'docker compose up -d --no-color --wait'
                                ${cmd} 'docker compose ps'
                            }
                        }
                    }
                }
            }
        }
        stage('Run tests against the container') {
            steps {
                script {
                    def cmd = isUnix() ? 'sh' : 'bat'
                    catchError(buildResult: 'FAILURE') {
                        echo "Running tests..."
                        ${cmd} 'curl http://localhost:9090'
                    }
                }
            }
        }
    }
}

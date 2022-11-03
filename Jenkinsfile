pipeline {
    agent {
        docker { image 'maven:latest' }
    }
    environment{
        DATABASE_URL_TEST='postgres://gael:@postgres:5432/qieam'
        POSTGRES_USER='gael'
        POSTGRES_DB='qieam'
        POSTGRES_HOST_AUTH_METHOD='trust'
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Run') {
            docker.image('postgres:15.0-alpine').withRun("-e POSTGRES_HOST_AUTH_METHOD=${env.POSTGRES_HOST_AUTH_METHOD} -e POSTGRES_USER=${env.POSTGRES_USER} -e POSTGRES_DB=${env.POSTGRES_DB}") { db -> 
                docker.image('postgres:15.0-alpine').inside("--link ${db.id}:postgres") {
                    sh '''
                        psql --version
                        until psql -h ${POSTGRES_HOST} -U ${POSTGRES_USER} -c "select 1" > /dev/null 2>&1 || [ $RETRIES -eq 0 ]; do
                          echo "Waiting for postgres server, $((RETRIES-=1)) remaining attempts..."
                          sleep 1
                        done
                       '''
                }
            }
        }
    }
}

pipeline {
    agent any
    environment{
        DATABASE_URL_TEST='postgres://gael:@postgres:5432/qieam'
        POSTGRES_USER='gael'
        POSTGRES_DB='qieam'
        POSTGRES_HOST_AUTH_METHOD='trust'
    }
    stages {
        stage('Build') {
            agent {
                docker { image 'maven:latest' }
            }
            steps {
                sh 'mvn compile'
            }
        }
        stage('Run') {
            steps {
                script {        
                    docker.image('postgres:15.0-alpine').withRun("-e POSTGRES_HOST_AUTH_METHOD=${env.POSTGRES_HOST_AUTH_METHOD} -e POSTGRES_USER=${env.POSTGRES_USER} -e POSTGRES_DB=${env.POSTGRES_DB}") { db -> 
                        docker.image('postgres:15.0-alpine').inside("--link ${db.id}:postgres") {
                            sh '''
                                while !pg_isready -d $DATABASE_URL_TEST -U $POSTGRES_USER
                                do
                                    sleep 1
                                done
                               '''
                        }
                    }
                }
            }
        }
    }
}

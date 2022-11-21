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
                    docker.image('postgres:15.0-alpine').withRun("--env POSTGRES_HOST_AUTH_METHOD=${env.POSTGRES_HOST_AUTH_METHOD} --env POSTGRES_USER=${env.POSTGRES_USER} --env POSTGRES_DB=${env.POSTGRES_DB}") { db -> 
                        docker.image('postgres:15.0-alpine').inside("--link ${db.id}:postgres") {
                            sh '''
                                while !pg_isready -d $DATABASE_URL_TEST -U $POSTGRES_USER
                                do
                                    sleep 1
                                done
                               '''
                        }
                        docker.image('maven:latest').inside {
                            sh 'mvn compile' 
                            sh 'mvn clean test'
                        }
                    }
                }
            }
        }
        stage('build && SonarQube analysis') {
            steps {
                mvn verify sonar:sonar -Dsonar.projectKey="Integration-continue_TP2" -Dsonar.host.url="http://localhost:9000" -Dsonar.login="sqp_ad8a10336fe7f4fae5efbaca401fc9b9d81e6269"
            }
        }
    }
}

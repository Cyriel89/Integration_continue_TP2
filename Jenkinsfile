pipeline {
    agent any
    stages {
        stage('Intégration test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}
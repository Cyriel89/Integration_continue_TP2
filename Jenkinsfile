pipeline {
    agent {
        docker { image 'maven:latest' }
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
    }
}

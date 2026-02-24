pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Maven') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t personapi-app .'
            }
        }

        stage('Run Docker Container') {
            steps {
                sh '''
                docker rm -f personapi-container || true
                docker run -d -p 8080:8080 --name personapi-container personapi-app
                '''
            }
        }
    }
}
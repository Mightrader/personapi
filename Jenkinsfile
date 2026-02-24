pipeline {
    agent any

    stages {

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

        stage('Run Docker Compose') {
            steps {
                sh 'docker compose up -d'
            }
        }
    }
}
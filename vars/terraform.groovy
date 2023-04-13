def call() {
 pipeline {
    agent any

    parameters {
        string(name: 'ENV', defaultValue: '', description: 'Which Environment?')
        string(name: 'ACTION', defaultValue: '', description: 'Which Action?')
    }
     options {
        ansiColor('xterm')
     }

     stages {
        stage('Init') {
            steps {
                sh 'sudo terraform init -backend-config=${ENV}-env/state.tfvars'
            }
        }
        stage('Apply') {
            steps {
                 sh "sudo terraform apply -auto-approve -var-file=${ENV}-env/${ENV}.tfvars"
                // sh 'echo'
            }
        }
     }
     post {
        always {
            cleanWs()
        }
     }
 }
}
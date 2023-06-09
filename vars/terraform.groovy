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
                sh 'terraform init -backend-config=${ENV}-env/state.tfvars'
            }
        }
        stage('Apply') {
            steps {
                 sh "terraform ${ACTION} -auto-approve -var-file=${ENV}-env/${ENV}.tfvars"
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
def call() {
pipeline {
    agent any 
        options {
         ansiColor('xterm')
    }
        parameters {
        string(name: 'app_version', defaultValue: '', description: 'App Version')
        string(name: 'component', defaultValue: '', description: 'Component')
        string(name: 'environment', defaultValue: '', description: 'Environment')
    }
        stages {
            stage('Clone Application') {
                steps {
                    dir('APP') {
                        git branch: 'main', url: "https://github.com/Siva-Sai-Deepak-Pulipaka/${component}"
                    }
                }
            }
            stage('Deploy Helm Chart') {
                steps {
                     script {
                        sh 'helm install ${component} APP/helm/${environment}.yaml'
                }
            }
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
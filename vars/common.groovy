def compile() {
    if(app_lang == "nodejs") {
        sh 'npm install'
    }
    if(app_lang == "maven") {
        sh 'mvn package'
    }
}

def testcases() {
    // if(app_lang == "nodejs") {
    //     sh 'npm install'
    // }
    // if(app_lang == "maven") {
    //     sh 'mvn package'
    // }
    // if(app_lang == "golang") {
    //     sh 'echo'
    // }
    sh 'echo Ok'
}

def codequality() {
    withAWSParameterStore(credentialsId: 'PARAM', naming: 'absolute', path: '/sonarqube', recursive: true, regionName: 'us-east-1') 
    {
    //   sh 'sonar-scanner -Dsonar.host.url=http://172.31.8.146:9000 -Dsonar.login=${SONARQUBE_USER} -Dsonar.password=${SONARQUBE_PASS} -Dsonar.projectKey=${component} ${sonar_extra_opts} -Dsonar.qualitygate.wait=true'
    sh 'echo Ok'
    }
}
def prepareArtifacts() {
    sh 'echo ${TAG_NAME} >VERSION'
    if (app_lang == "nodejs" || app_lang == "angular") {
    sh 'zip -r ${component}-${TAG_NAME}.zip * -x Jenkinsfile'
}

}

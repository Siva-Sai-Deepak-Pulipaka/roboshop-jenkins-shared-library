// this is for scripted pipeline as we apply many conditions to jenkins so to make it easy we are using seperate file this
// call is a default function  
def call() {
    
    if (!env.sonar_extra_opts) {            //means if var is not there then the value is empty
        env.sonar_extra_opts = ""
    }

    node('workstation') {

        try {
            stage('Clean Code') {
                // sh 'rm -rf *'
                cleanWs()     // this is default func to clean everything in  jenkins workspace
                git branch: 'main', url: 'https://github.com/Siva-Sai-Deepak-Pulipaka/cart'
            }

            sh 'env'     // displays environment variables


            if(env.BRANCH_NAME != "main") {
             stage('Compile/Build') {
                common.compile()
             }
            }

            
            if(env.TAG_NAME)
            stage('Test Cases') {
                common.testcases()
                }
            
            stage('Code Quality') {
                        common.codequality()
                    }
    
        } catch (e) {

                    mail body: "<h1>${component} - Pipeline Failed \n ${BUILD_URL}</h1>", from: "cloudprojectmail001@gmail.com", subject: "${component} - Pipeline Failed", to: "cloudprojectmail001@gmail.com", mimeType: 'text/html'
                }
            }

}
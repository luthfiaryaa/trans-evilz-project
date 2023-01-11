pipeline {
    agent any
    stages {
       /* stage('clone repo'){
            steps{
                git branch: 'main', credentialsId:'github-token', url: 'https://github.com/projektimb/reactjs.git'
            }
        } */
      stage('Clone Code'){
	steps{
        sh """
        
        rm -rf java
        
        git clone https://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@github.com/projektimb/java.git
        
        cd java && ls -lah
        """
	}
    }
/*        stage('build react'){
            steps{
                sh '''npm install
                npm run build'''
            }
        } */
/*        stage('build react + docker image'){
            steps{
                script{
                    app = docker.build("reactjs:evilcorps-v1")
                }
            }
        }  */
/*        stage('push image'){
            steps{
                script{
                    withDockerRegistry([credentialsId: "docker-hub-credentials", url: ""]){
                    dockerImage.push()
                    }
                }
            }
        } */
/*        stage('deploy image'){
            steps{
                script{
                    echo "Pushing the image to docker hub"
                    def localImage = "reactjs:evilcorps-v1"
                    
                    def repositoryName = "irvanmr/reacjs:evilcorps-v1"
                    
                    sh "docker tag reactjs:irvan-v1 irvanmr/reactjs:evilcorps-v1"
                    
                    docker.withRegistry("","docker-hub-credentials"){
                        def image = docker.image("irvanmr/reactjs:evilcorps-v1");
                        image.push()
                    }
                }
            }
        } */
	    
/*         stage('Build Code'){
             steps{
        sh """
        
        cd reactjs
        
        npm install
        
        npm run build
        
        """
	}
    } */
	    stage('Build Code'){
		    steps{
			    
		    sh """
		    
		    cd java
		    
		    mvn clean install
		    
		    """
		    
		    }
	    }
	    
    stage('Build Docker Image'){
	steps{

        sh """
        
        cd java
	
	sudo docker stop java
	
	sudo docker rm java
        
        sudo docker build -t irvanmr/java:evilcorps-v1 .
        
        """
	}
    }
/*    stage('Push Image'){
	steps{
        
        sh """
        
        sudo cat /home/irvan-bootcamp/password.txt | sudo docker login -u irvanmr --password-stdin
        
        sudo docker push irvanmr/java:evilcorps-v1
        """
	}
    } */
       
    stage('Deploy to Server'){
	steps{

        sh """
        
        sudo su
        
      
	
	sudo docker run --name java-v1 -dp 87:8080 irvanmr/java:evilcorps-v1
         
        """
	}
    } 

     /*   stage('remote ubuntu'){
            steps{
		          
        sudo docker pull irvanmr/java:evilcorps-v1
	
        sudo docker image rm irvanmr/java:evilcorps-v1
              sudo docker run --name reactjs -dp 89:80 irvanmr/reactjs:evilcorps-v1
        
                ssh vaan@10.0.2.4
                def remote = [:]
                remote.name = 'reactjs'
                remote.host = 'reactjs.irvan.com'
                remote.user = 'vaan'
                remote.password = '   '
                remote.allowAnyHosts = true
                stage('remote ssh'){
                    sshGet remote: remote, from: docker.image('irvanmr/reactjs:irvan-v1')
                } 
            }
        } */
    }
}

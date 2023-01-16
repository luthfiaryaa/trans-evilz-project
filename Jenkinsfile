pipeline{
        agent any
        stages{
      stage('Clone Code'){
              steps{

                sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'rm -rf java && git clone https://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@github.com/projektimb/java.git && cd java && ls -lah',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
              }

    }

    stage('Build Code'){
            steps{

                sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'cd java && mvn clean install',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
            }

    }

    stage('Build Docker Image'){
            steps{

                sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'cd java && docker build -t irvanmr/java:evilcorps-v2 .',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
            }

    }

/*    stage('Push Image'){
            steps{

                sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'cat /home/irvan-bootcamp/password.txt | docker login -u irvanmr --password-stdin',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )

                sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'docker push irvanmr/java:evilcorps-v2',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
            }

    }
*/

    stage('Deploy to Server'){
            steps{

        sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'docker stop javaEvilcorpsV2 && docker rm javaEvilcorpsV2',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
/*
        sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'docker rmi irvanmr/java:evilcorps-v2',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )

        sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'docker pull irvanmr/java:evilcorps-v2',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
   */
        sshPublisher(
          continueOnError: false,
          failOnError: true,
          publishers: [
            sshPublisherDesc(
              configName: "ssh-irvan-bootcamp",
              transfers: [
                            sshTransfer(
                                cleanRemote: false,
                                excludes: '',
                                execCommand: 'docker run -dp 5555:8080 --name javaEvilcorpsV2 irvanmr/java:evilcorps-v2',
                                execTimeout: 120000,
                            )
                        ],
              //transfers: [sshTransfer(sourceFiles: 'my-app.jar')],
              verbose: true
            )
          ]
        )
            }

    }
        }
}

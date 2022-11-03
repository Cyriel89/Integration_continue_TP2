FROM jenkins/jenkins:lts
RUN apt-get update && apt-get install -y maven
ENV JENKINS_SLAVE_AGENT_PORT 50001
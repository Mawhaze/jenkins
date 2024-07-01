FROM jenkins/inbound-agent:latest
USER root
# RUN apt-get update && apt-get install -y \
#     passwd \
#     groupadd \
#     && rm -rf /var/lib/apt/lists/*
RUN groupadd -g 988 docker && usermod -aG docker jenkins
USER jenkins
FROM donhenton/maven-docker

RUN apt-get update && apt-get install -y openssh-server docker.io
RUN mkdir /var/run/sshd
RUN echo 'root:changeit' | chpasswd && usermod -U root
COPY ./sshd_config /etc/ssh/sshd_config
#RUN sed -i 's/PermitRootLogin prohibit-password/PermitRootLogin yes/' /etc/ssh/sshd_config

# SSH login fix. Otherwise user is kicked off after login
RUN sed 's@session\s*required\s*pam_loginuid.so@session optional pam_loginuid.so@g' -i /etc/pam.d/sshd
VOLUME /var/run/docker.sock
ENV NOTVISIBLE "in users profile"
RUN echo "export VISIBLE=now" >> /etc/profile

RUN mkdir -p /var/registrydata
VOLUME /var/registrydata

EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]
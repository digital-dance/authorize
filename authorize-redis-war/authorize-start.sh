#!/bin/sh
#echo '192.168.0.118 www.mq.ingress' >> /etc/hosts
#mv /jar/mq-adapter-boot-zuul.jar /app/
echo 'ls -l /tomcat'
ls -l /tomcat
echo 'ls -l /tomcat/8'
ls -l /tomcat/8
/tomcat/8/bin/catalina.sh start
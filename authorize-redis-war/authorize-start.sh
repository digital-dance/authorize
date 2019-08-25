#!/bin/sh
#echo '192.168.0.118 www.mq.ingress' >> /etc/hosts
#mv /jar/mq-adapter-boot-zuul.jar /app/
echo 'ls -l /tomcat'
ls -l /tomcat
echo 'ls -l /tomcat/8'
ls -l /tomcat/8
echo 'ls -l /tomcat/8/conf'
ls -l /tomcat/8/conf
echo 'ls -l /tomcat/8/webapps'
ls -l /tomcat/8/webapps
echo 'ls -l /tomcat/8/webapps/ROOT'
ls -l /tomcat/8/webapps/ROOT

mkdir -p /app/logs
mkdir -p /app/logs/authorize
mkdir -p /app/logs/authorize/logs

chmod -R 777 /app
chmod -R 777 /app/*
chmod -R 777 /app/logs/authorize
chmod -R 777 /app/logs/authorize/logs

/tomcat/8/bin/catalina.sh start
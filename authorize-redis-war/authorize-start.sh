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

chmod -R 777 /tomcat/8/webapps
chmod -R 777 /tomcat/8/webapps/*

mkdir -p /app/logs
mkdir -p /app/logs/authorize
mkdir -p /app/logs/authorize/logs

chmod -R 777 /app
chmod -R 777 /app/*
chmod -R 777 /app/logs/authorize
chmod -R 777 /app/logs/authorize/logs

#echo 'export TOMCAT_HOME=/tomcat/8' >> /etc/profile
#echo 'export CATALINA_HOME=/tomcat/8' >> /etc/profile
#
#echo 'export PATH=$PATH:$TOMCAT_HOME/bin' >> /etc/profile
#source /etc/profile

#/tomcat/8/bin/catalina.sh start
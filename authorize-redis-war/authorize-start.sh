#!/bin/sh
#echo '192.168.0.118 www.mq.ingress' >> /etc/hosts
#mv /jar/mq-adapter-boot-zuul.jar /app/
#echo 'ls -l /tomcat'
#ls -l /tomcat
#echo 'ls -l /tomcat/8'
#ls -l /tomcat/8
#echo 'ls -l /tomcat/8/conf'
#ls -l /tomcat/8/conf
#echo 'ls -l /tomcat/8/webapps'
#ls -l /tomcat/8/webapps
#
#echo 'ls -l /tomcat/8/bin'
#ls -l /tomcat/8/bin
#
#echo 'ls -l /tomcat/8/lib'
#ls -l /tomcat/8/lib
#
#chmod -R 777 /tomcat/8/webapps
#chmod -R 777 /tomcat/8/webapps/*

mkdir -p /app
mkdir -p /app/logs
mkdir -p /app/logs/authorize

chmod -R 777 /app
chmod -R 777 /app/*
chmod -R 777 /app/logs/authorize
echo 'chmod -R 777 /app/logs/authorize'

#which java
#
#ls -lrt  /usr/bin/java
#ls -lrt  /usr/lib/jvm/default-jvm/bin/java
#ls -lrt  /usr/lib/jvm/default-jvm
#ls -lrt  /usr/lib/jvm/java-8-oracle

#export JAVA_HOME=/usr/lib/jvm/java-8-oracle
#export PATH=$JAVA_HOME/bin:$PATH
#export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

#echo 'export JAVA_HOME=/usr/lib/jvm/java-8-oracle' >> /etc/profile
#echo 'export PATH=$JAVA_HOME/bin:$PATH' >> /etc/profile
#echo 'export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar' >> /etc/profile
#
#echo 'export TOMCAT_HOME=/tomcat/8' >> /etc/profile
#echo 'export CATALINA_HOME=/tomcat/8' >> /etc/profile
#
#echo 'export PATH=$PATH:$TOMCAT_HOME/bin' >> /etc/profile
#source /etc/profile
#
#echo 'JAVA_HOME='
#echo $JAVA_HOME

#java -version
ln -s /usr/local/tomcat /app/tomcat
sh /usr/local/tomcat/bin/startup.sh
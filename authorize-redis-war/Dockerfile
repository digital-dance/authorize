#FROM java:8
#FROM www.digital.dance.com:5001/repository/digital.dance.docker/alpine-oraclejdk8:8
#FROM tomcat
#FROM hub.c.163.com/library/tomcat:latest
#FROM tomcat:8.5.38-jre8-alpine
#FROM buglife/tomcat-apr:latest
FROM www.digital.dance.com:5001/repository/digital.dance.docker/tomcat:v2

#VOLUME /app
#RUN mkdir -p /tomcat/8/
#RUN mkdir -p /tomcat/8/logs/

#RUN chmod -R 777 /tomcat
#RUN chmod -R 777 /tomcat/8

#RUN chmod -R 777 /tomcat/8/logs

#RUN mkdir -p /app
#RUN mkdir -p /app/logs
#RUN mkdir -p /app/logs/authorize/

ARG BUILD_WORKDIR

ARG WAR_FILE
ARG SH_FILE
ARG SERVER_PORT

#RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}

#COPY docker-source/8 /tomcat/8

#COPY ${WAR_FILE} /usr/local/tomcat/webapps/
COPY ${WAR_FILE} /usr/local/tomcat/webapps/authorize.war
#COPY ${WAR_FILE} /opt/apache-tomcat-8.0.36/webapps/authorize.war

#COPY ${SH_FILE} /usr/local/tomcat/authorize-start.sh

#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-zuul.jar"]
#RUN chmod -R 777 /tomcat/*
#RUN chmod -R 777 /tomcat/authorize-start.sh
#RUN chmod -R 777 /tomcat/8/*
#
#RUN chmod -R 777 /opt/apache-tomcat-8.0.36
#RUN chmod -R 777 /opt/apache-tomcat-8.0.36/*
#RUN ln -s /usr/local/tomcat /app/tomcat

#RUN chmod -R 777 /app
#RUN chmod -R 777 /app/*
#RUN chmod -R 777 /app/logs/authorize
#ENTRYPOINT ["/usr/local/tomcat/authorize-start.sh"]

#ENV JAVA_HOME=/usr/lib/jvm/java-8-oracle
#ENV JRE_HOME=$JAVA_HOME/jre
#ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH
#
#ENV CATALINA_HOME=/tomcat/8
#ENV CATALINA_BASE=/tomcat/8
#
#ENV PATH=/sbin:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin:$PATH

#EXPOSE 8080

#ENTRYPOINT ["/tomcat/8/bin/startup.sh","run"]
#CMD /tomcat/8/bin/startup.sh &

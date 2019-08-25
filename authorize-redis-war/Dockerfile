#FROM java:8
FROM www.digital.dance.com:5001/repository/digital.dance.docker/alpine-oraclejdk8:8
#VOLUME /app
RUN mkdir -p /tomcat/8/
#RUN mkdir -p /tomcat/8/logs/

RUN chmod -R 777 /tomcat
RUN chmod -R 777 /tomcat/8

#RUN chmod -R 777 /tomcat/8/logs

RUN mkdir -p /app/logs
RUN mkdir -p /app/logs/authorize
RUN mkdir -p /app/logs/authorize/logs

ARG BUILD_WORKDIR

ARG WAR_FILE
ARG SH_FILE
ARG SERVER_PORT

RUN echo ${BUILD_WORKDIR}

WORKDIR ${BUILD_WORKDIR}

COPY docker-source/8 /tomcat/8

COPY ${WAR_FILE} /tomcat/8/webapps/authorize.war

COPY ${SH_FILE} /tomcat/authorize-start.sh



#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/jar/mq-adapter-boot-zuul.jar"]
RUN chmod -R 777 /tomcat/*
RUN chmod -R 777 /tomcat/authorize-start.sh
RUN chmod -R 777 /tomcat/8/*

RUN chmod -R 777 /app
RUN chmod -R 777 /app/*
RUN chmod -R 777 /app/logs/authorize
RUN chmod -R 777 /app/logs/authorize/logs
#ENTRYPOINT ["/tomcat/authorize-start.sh"]


ENV JAVA_HOME=/usr/lib/jvm/java-8-oracle
ENV JRE_HOME=$JAVA_HOME/jre
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib:$CLASSPATH

ENV CATALINA_HOME=/tomcat/8
ENV CATALINA_BASE=/tomcat/8

ENV PATH=/sbin:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin:$PATH


EXPOSE ${SERVER_PORT}

ENTRYPOINT ["/tomcat/8/bin/startup.sh","run"]
#CMD /tomcat/8/bin/startup.sh &

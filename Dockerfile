FROM openjdk:11.0-jdk 
#openjdk kvůli m1 chipům

ARG APP_HOME=/usr/app

COPY target/*.jar ${APP_HOME}/push-ntf-system.jar
COPY src/main/resources/application.properties /config/application.properties

EXPOSE 8080

ENV JAVA_OPTS=""
ENV APP_HOME=${APP_HOME}
CMD java $JAVA_OPTS -jar ${APP_HOME}/push-ntf-system.jar --spring.config.location=file:/config/application.properties
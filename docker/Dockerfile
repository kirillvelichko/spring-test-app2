FROM adoptopenjdk:15.0.2_7-jre-hotspot
ARG JAR=build/libs/app.jar
RUN mkdir /opt/app
COPY ${JAR} /opt/app
CMD ["java", "-jar", "/opt/app/app.jar"]
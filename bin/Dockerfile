FROM openjdk:17-jdk-slim
WORKDIR /atdd
COPY target/*.war /atdd/atdd-0.0.1-SNAPSHOT.war
EXPOSE 9090
CMD java -XX:+UseContainerSupport -Xmx512m -Dserver.port=9090 -jar atdd-0.0.1-SNAPSHOT.war 
FROM openjdk:17-jdk-alpine
COPY target/HospitalBasico-0.0.1-SNAPSHOT.war hospitalBasico.war
EXPOSE 3000
ENTRYPOINT [ "java","-jar", "hospitalBasico.war"]
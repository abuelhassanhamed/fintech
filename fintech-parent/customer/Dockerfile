FROM openjdk:17
#COPY target/order-0.0.1-SNAPSHOT.jar app.jar
ADD /target /target
 EXPOSE 8080
 ENTRYPOINT [ "java", "-jar","target/${project.build.finalName}.jar" ]
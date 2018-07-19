FROM openjdk:8
ADD target/carrentalapp.jar carrentalapp.jar
EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "carrentalapp.jar"]
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/test","-jar","/carrentalapp.jar"]

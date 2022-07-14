#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

# Add the application's jar to the container
COPY target/MS_Savings-0.0.1-SNAPSHOT.jar MS_Savings-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/MS_Savings-0.0.1-SNAPSHOT.jar"]
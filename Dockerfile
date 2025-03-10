FROM amazoncorretto:17

COPY target/restaurant-1.0.0.jar app.jar
CMD ["java", "-jar", "app.jar"]
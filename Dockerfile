FROM maven:3.8.2-openjdk-11
WORKDIR /backend
COPY . .
RUN mvn clean install
CMD mvn spring-boot:run
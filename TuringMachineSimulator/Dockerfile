FROM ubuntu:22.04

WORKDIR /usr/src/app

RUN apt-get update

RUN apt-get -y install openjdk-19-jdk

# RUN java -version

RUN apt-get -y install maven

#RUN mvn -version

COPY . .

RUN mvn package

CMD java -jar target/TuringMachineSimulator-1.0-SNAPSHOT.jar

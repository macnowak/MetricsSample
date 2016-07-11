FROM ubuntu:15.10
MAINTAINER Maciej Nowak

ENV DEBIAN_FRONTEND noninteractive

RUN apt-get update
RUN apt-get install -y wget
RUN apt-get install -y openjdk-7-jre
RUN apt-get install -y openjdk-7-jdk

RUN useradd -d /home/esuser -m esuser
WORKDIR /home/esuser
FROM ubuntu:15.10
MAINTAINER Maciej Nowak

ENV DEBIAN_FRONTEND noninteractive

RUN mkdir /opt/jdk

RUN apt-get update
RUN apt-get install -y wget

RUN \
    wget --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u111-b14/jdk-8u111-linux-x64.tar.gz && \
    tar -zxf jdk-8u111-linux-x64.tar.gz -C /opt/jdk && \
    update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_111/bin/java 1 && \
    update-alternatives --install /usr/bin/javac javac /opt/jdk/jdk1.8.0_111/bin/javac 1

RUN java -version

ENV JAVA_HOME /opt/jdk/jdk1.8.0_111
ENV PATH $PATH:$JAVA_HOME/bin

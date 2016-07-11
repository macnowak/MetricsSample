FROM java_image
MAINTAINER Maciej Nowak

ENV DEBIAN_FRONTEND noninteractive

RUN \
  wget https://download.elastic.co/logstash/logstash/logstash-all-plugins-2.3.3.tar.gz && \
  tar xvzf logstash-all-plugins-2.3.3.tar.gz && \
  rm -f logstash-all-plugins-2.3.3.tar.gz && \
  chown -R esuser:esuser logstash-2.3.3

# logstash.conf and Dockerfile are on same location
ADD logstash.conf /home/esuser

RUN \
    wget http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.36/mysql-connector-java-5.1.36.jar && \
    chmod 777 mysql-connector-java-5.1.36.jar

CMD logstash-2.3.3/bin/logstash -f logstash.conf

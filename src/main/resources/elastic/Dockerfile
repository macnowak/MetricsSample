FROM java_image
MAINTAINER Maciej Nowak

ENV DEBIAN_FRONTEND noninteractive

RUN mkdir /data

RUN \
 wget https://download.elasticsearch.org/elasticsearch/release/org/elasticsearch/distribution/tar/elasticsearch/2.3.3/elasticsearch-2.3.3.tar.gz && \
 tar xvzf elasticsearch-2.3.3.tar.gz && \
 rm -f elasticsearch-2.3.3.tar.gz && \
 chown -R esuser:esuser elasticsearch-2.3.3

# elasticsearch.yml and Dockerfile are on same location
ADD elasticsearch.yml /home/esuser/elasticsearch-2.3.3/config/elasticsearch.yml
ENTRYPOINT elasticsearch-2.3.3/bin/elasticsearch
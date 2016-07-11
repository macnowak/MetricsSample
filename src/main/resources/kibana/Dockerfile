FROM java_image
MAINTAINER Maciej Nowak

ENV DEBIAN_FRONTEND noninteractive

RUN \
 wget https://download.elastic.co/kibana/kibana/kibana-4.5.2-linux-x64.tar.gz && \
 tar xvzf kibana-4.5.2-linux-x64.tar.gz && \
 rm -f kibana-4.5.2-linux-x64.tar.gz && \
 chown -R esuser:esuser kibana-4.5.2-linux-x64

ADD kibana.yml kibana-4.5.2-linux-x64/config/kibana.yml

ENTRYPOINT kibana-4.5.2-linux-x64/bin/kibana
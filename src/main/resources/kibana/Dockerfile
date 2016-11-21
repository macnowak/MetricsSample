FROM java_image
MAINTAINER Maciej Nowak

RUN useradd -d /home/esuser -m esuser
WORKDIR /home/esuser

ENV DEBIAN_FRONTEND noninteractive
ENV KIBANA_VER=5.0.0

RUN \

 wget https://artifacts.elastic.co/downloads/kibana/kibana-${KIBANA_VER}-linux-x86_64.tar.gz && \
 tar xvzf kibana-${KIBANA_VER}-linux-x86_64.tar.gz && \
 rm -f kibana-${KIBANA_VER}-linux-x86_64.tar.gz && \
 mv kibana-${KIBANA_VER}-linux-x86_64 kibana && \
 chown -R esuser:esuser kibana

ADD kibana.yml kibana/config/kibana.yml

#RUN kibana/bin/kibana-plugin install  x-pack

ENTRYPOINT kibana/bin/kibana
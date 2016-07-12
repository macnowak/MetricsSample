## Docker configuration :

* MySql :
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=audit -e MYSQL_USER=audit -e MYSQL_PASSWORD=audit -d mysql:latest

* Java image :

java_image/ docker build -t java_image .

* ElasticSearch :

elastic/ docker build -t es_image .

* Logstash

logstash/ docker build -t logstash_image .

* Kibana

kibana/ docker build -t kibana_image .


## Running :

* docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=audit -e MYSQL_USER=audit -e MYSQL_PASSWORD=audit -d mysql:latest
* docker run --user esuser --name es es_image
* docker run --name logstash --link es:es --link mysql:mysql logstash_image
* docker run --name kibana --link es:es -p 5601:5601 kibana_image
* http://10.0.75.2:5601


Based on :

http://logz.io/learn/docker-monitoring-elk-stack/

## ToDo :

* <s>collecting metrics from endpoints and jvm
* <s>add console log metrics
* send metrics to elastic
* configure file log
* read log file in logstash
* create dashboard
* create multi nodes to simulate traffic
* create docker compose
## Docker configuration :

* MySql :
docker run -p 3306:3306 --name mysql -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=audit -e MYSQL_USER=audit -e MYSQL_PASSWORD=audit -d mysql:latest

## Running :

* docker-compose up
* ES - http://localhost:9200/
* KIBANA - http://localhost:5601
* GRAFANA - http://localhost:3000
* GRAPHITE - http://localhost:80
* PROMETHEUS - http://localhost:9090

## ToDo :

* <s>collecting metrics from endpoints and jvm</s>
* <s>add console log metrics</s>
* <s>send metrics to elastic</s>
* configure file log
* read log file in logstash
* create dashboard
* create multi nodes to simulate traffic
* <s>create docker compose</s>
* <s>try graphana</s>
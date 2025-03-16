docker pull mongo:7.0.5
docker pull mysql:8.3.0
docker pull confluentinc/cp-zookeeper:7.5.0
docker pull confluentinc/cp-kafka:7.5.0
docker pull confluentinc/cp-schema-registry:7.5.0
docker pull provectuslabs/kafka-ui:latest
docker pull mysql:8
docker pull quay.io/keycloak/keycloak:24.0.1
docker pull grafana/loki:main
docker pull prom/prometheus:v2.46.0
docker pull grafana/tempo:2.2.2
docker pull grafana/grafana:10.1.0
docker pull kennegervais/new-api-gateway:latest
docker pull kennegervais/new-product-service:latest
docker pull kennegervais/new-order-service:latest
docker pull kennegervais/new-inventory-service:latest
docker pull kennegervais/new-notification-service:latest
docker pull kennegervais/new-config-server:latest
docker pull kennegervais/new-discovery:latest

kind load docker-image -n catalog-ms-ks8 mongo:7.0.5
kind load docker-image -n catalog-ms-ks8 mysql:8.3.0
kind load docker-image -n catalog-ms-ks8 confluentinc/cp-zookeeper:7.5.0
kind load docker-image -n catalog-ms-ks8 confluentinc/cp-kafka:7.5.0
kind load docker-image -n catalog-ms-ks8 confluentinc/cp-schema-registry:7.5.0
kind load docker-image -n catalog-ms-ks8 provectuslabs/kafka-ui:latest
kind load docker-image -n catalog-ms-ks8 mysql:8
kind load docker-image -n catalog-ms-ks8 quay.io/keycloak/keycloak:24.0.1
kind load docker-image -n catalog-ms-ks8 grafana/loki:main
kind load docker-image -n catalog-ms-ks8 prom/prometheus:v2.46.0
kind load docker-image -n catalog-ms-ks8 grafana/tempo:2.2.2
kind load docker-image -n catalog-ms-ks8 grafana/grafana:10.1.0
kind load docker-image -n catalog-ms-ks8 kennegervais/new-api-gateway:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-product-service:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-order-service:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-inventory-service:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-notification-service:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-discovery:latest
kind load docker-image -n catalog-ms-ks8 kennegervais/new-config-server:latest
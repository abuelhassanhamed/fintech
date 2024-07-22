FROM quay.io/strimzi/kafka:latest-kafka-3.4.0
ARG DEBEZIUM_CONNECTOR_VERSION=2.1.3.Final
ENV KAFKA_CONNECT_PLUGIN_PATH=/tmp/connect-plugins

RUN mkdir $KAFKA_CONNECT_PLUGIN_PATH &&\
    cd $KAFKA_CONNECT_PLUGIN_PATH &&\
    curl -sfSL https://repo1.maven.org/maven2/io/debezium/debezium-connector-postgres/2.1.3.Final/debezium-connector-postgres-2.1.3.Final-plugin.tar.gz | tar xz
RUN  cd $KAFKA_CONNECT_PLUGIN_PATH &&\
    curl -sfSL https://repo1.maven.org/maven2/io/debezium/debezium-connector-mysql/${DEBEZIUM_CONNECTOR_VERSION}/debezium-connector-mysql-${DEBEZIUM_CONNECTOR_VERSION}-plugin.tar.gz | tar xz     &&\
    cd debezium-connector-mysql &&\
    curl -sfSL https://repo1.maven.org/maven2/io/debezium/debezium-interceptor/${DEBEZIUM_CONNECTOR_VERSION}/debezium-interceptor-${DEBEZIUM_CONNECTOR_VERSION}.jar -o debezium-interceptor-${DEBEZIUM_CONNECTOR_VERSION}.jar 
   
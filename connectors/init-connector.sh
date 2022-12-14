sleep 30s

curl -X POST -H "Content-Type: application/JSON" --data '{"name": "cdc-connector", "config": {
  "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
  "slot.name": "debezium_json",
  "database.user": "postgres",
  "database.dbname": "postgres",
  "database.server.name": "postgres",
  "plugin.name": "pgoutput",
  "tombstones.on.delete": "false",
  "key.converter.schemas.enable": "false",
  "database.hostname": "postgres",
  "database.password": "password",
  "value.converter.schemas.enable": "false",
  "key.converter": "org.apache.kafka.connect.json.JsonConverter",
  "value.converter": "org.apache.kafka.connect.json.JsonConverter",
  "table.include.list": "ppro.party,ppro.document,ppro.investment,ppro.product",
  "topic.prefix": "postgres"
}}}' http://connect:8083/connectors/

exit
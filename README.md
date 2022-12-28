# ppro_push-ntf-system
This is the school project for "Pokročilé programování"

- je možné spustit plně lokálně
- spustit pomocí: ```docker-compose -p ppro up -d```

### Postup:

1) mvn clean install - na úrovni root adresáře projektu
2) ```docker-compose -p ppro build```
3) ```docker-compose -p ppro up```

### Swagger-UI URL:

http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/

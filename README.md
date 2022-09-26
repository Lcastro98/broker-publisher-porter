# Portero de la unidad

Este proyecto está pensado para resolver las siguientes dos situaciones de envio de correspondencia haciendo uso de RabbitMQ:
- Cuando el administrador le envía correspondencia a todo el piso.
- Cuando el administrador le envía correspondencia solo a los pisos impares.

Para su correcto funcionamiento es necesario que se tenga un contenedor de RabbitMQ corriendo, en el archivo application.yml está la cofiguración para la correcta conexión, modificar de ser necesario.

### Ejemplo Postman
Para enviar un mensaje debe incluir el exchange, la routingkey y el mensaje en los parametros como se muestra a continuación.
![alt text](https://github.com/Lcastro98/broker-publisher-porter/blob/master/src/main/java/com/sofka/broker/images/postmanExample.png)

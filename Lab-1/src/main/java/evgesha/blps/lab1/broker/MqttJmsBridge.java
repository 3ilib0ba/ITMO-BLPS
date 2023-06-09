package evgesha.blps.lab1.broker;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MqttJmsBridge implements MqttCallback {
    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    private final JmsTemplate jmsTemplate;

    public MqttJmsBridge(
            JmsTemplate jmsTemplate
    ) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setTopicQueueBridge(
            String mqttTopic
    ) throws MqttException {
        MqttClient mqttClient = new MqttClient(mqttBrokerUrl, MqttClient.generateClientId());
        mqttClient.setCallback(this);
        mqttClient.connect();

        mqttClient.subscribe(mqttTopic);
    }

    @Override
    public void connectionLost(Throwable throwable) {
        // Обработка потери соединения с MQTT брокером
    }

    @Override
    public void messageArrived(String mqttTopic, MqttMessage mqttMessage) {
        // При получении сообщения по MQTT, отправляем его в JMS очередь (КАК ОБЪЕКТ, ЧТОБЫ МОЖНО БЫЛО ПЕРЕДАВАТЬ POJO)
        jmsTemplate.send(mqttTopic, session -> session.createObjectMessage(mqttMessage.getPayload()));
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // Обработка успешной доставки сообщения MQTT (не требуется для этого примера)
    }
}


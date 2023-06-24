package evgesha.blps.lab1.broker;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.io.Serializable;

@Component
public class MqttPublisher {
    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    public void publishToTopic(String topicName, Serializable message) {
        try {
            MqttClient client = new MqttClient(mqttBrokerUrl, MqttClient.generateClientId());
            client.connect();

            MqttMessage mqttMessage = new MqttMessage(SerializationUtils.serialize(message));
            mqttMessage.setQos(1);
            client.publish(topicName, mqttMessage);

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


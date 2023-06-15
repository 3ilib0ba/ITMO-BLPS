package evgesha.blps.lab1.broker;
import org.eclipse.paho.client.mqttv3.*;

public class MqttPublisher {
    public static void main(String[] args) {
        String broker = "tcp://localhost:1883";
        String topic = "ABOBA123";
        String message = "Hello, ActiveMQ via MQTT!";

        try {
            MqttClient client = new MqttClient(broker, MqttClient.generateClientId());
            client.connect();

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(1);
            client.publish(topic, mqttMessage);

            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}


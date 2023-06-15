package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.broker.MqttJmsBridge;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
public class BrokerController {
    private final MqttJmsBridge mqttJmsBridge;

    public BrokerController(MqttJmsBridge mqttJmsBridge) {
        this.mqttJmsBridge = mqttJmsBridge;
    }

    @GetMapping("/broker/setBridge")
    public ResponseEntity<?> getCurrentRecipeByIdWithComments(
            @RequestParam(name = "topic_and_queue_name")
            @NotBlank String name
    ) {
        try {
            mqttJmsBridge.setTopicQueueBridge(name);
        } catch (MqttException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("SET");
    }
}

package evgesha.blps.lab1.controller;

import evgesha.blps.lab1.broker.MqttJmsBridge;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class BrokerController {
    private final MqttJmsBridge mqttJmsBridge;

    public BrokerController(MqttJmsBridge mqttJmsBridge) {
        this.mqttJmsBridge = mqttJmsBridge;
    }

//    @GetMapping("/broker/setBridge")
//    public ResponseEntity<?> getCurrentRecipeByIdWithComments(
//            @RequestParam(name = "topic_and_queue_name")
//            @NotBlank String name
//    ) {
//        try {
//            mqttJmsBridge.setTopicQueueBridge(name);
//        } catch (MqttException e) {
//            return ResponseEntity.internalServerError().build();
//        }
//        return ResponseEntity.ok("SET");
//    }
}

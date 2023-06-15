package evgesha.blps.lab1;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import evgesha.blps.lab1.broker.MqttJmsBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
public class Lab1Application implements CommandLineRunner {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MqttJmsBridge mqttJmsBridge;

    public static void main(String[] args) {
        SpringApplication.run(Lab1Application.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

        mqttJmsBridge.setTopicQueueBridge("EMAIL");
        mqttJmsBridge.setTopicQueueBridge("STAT_COUNT_VIEWS");

    }

}

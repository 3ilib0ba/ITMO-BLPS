package evgesha.blps.lab1.camunda;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloDelegate {
    @JobWorker(type = "hello_world", autoComplete = true)
    public Map<String, Object> helloWorld(final JobClient client, final ActivatedJob job) {
        log.info("JOB: hello world!");

        Map<String, Object> map = new HashMap<>();
        map.put("answer_from_hello_world", "ANSWER FROM SERVER");

        //client.newCompleteCommand(job.getKey()).variables(map);
        return map;
    }

}

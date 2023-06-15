package evgesha.blps.lab1.broker;

import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
public class SampleJmsErrorHandler implements ErrorHandler {

    // ... logger

    @Override
    public void handleError(Throwable t) {
//        LOG.warn("In default jms error handler...");
//        LOG.error("Error Message : {}", t.getMessage());
    }

}

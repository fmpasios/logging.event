package org.pollfish.SerializeDeserializeLoggingRequest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.pollfish.logging.service.*;

import java.util.Map;

/**
 * Created by fmpasios on 14/9/2018.
 */
public class LoggingRequestDeserializer implements Deserializer {


    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public LoggingEvent deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        LoggingEvent request = null;
        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            request = mapper.readValue(bytes, LoggingEvent.class);
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return request;
    }

    @Override
    public void close() {

    }
}

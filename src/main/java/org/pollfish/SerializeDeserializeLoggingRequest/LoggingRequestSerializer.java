package org.pollfish.SerializeDeserializeLoggingRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.pollfish.logging.service.*;
import java.util.Map;

/**
 * Created by fmpasios on 14/9/2018.
 */
public class LoggingRequestSerializer implements Serializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        byte[] request = null;
        LoggingEvent lg = (LoggingEvent)o;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            request = objectMapper.writeValueAsString(lg).getBytes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return request;
    }

    @Override
    public void close() {

    }
}

package org.pollfish.LoggingEventServiceImplementation;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;
import org.pollfish.Common.CommonUtilities;
import org.pollfish.KafkaProducer.KafkaProducerInit;
import org.pollfish.Properties.KafkaProperties;
import org.pollfish.logging.service.*;
import org.apache.thrift.TException;

/**
 * Created by fmpasios on 13/9/2018.
 */
public class LoggingEventServiceImpl implements LoggingEventService.Iface {

    final static Logger logger = Logger.getLogger(LoggingEventServiceImpl.class);



    @Override
    public LoggingResponse saveEvent(LoggingEvent request) throws InvalidOperationException, TException {


        LoggingResponse response=new LoggingResponse();
        try
         (Producer<String, LoggingEvent> producer = KafkaProducerInit.createProducer()){


            logger.error("Start sending message to kafka server !!!");

            logger.error(CommonUtilities.loggingRequestToString(request));


            producer.send(new ProducerRecord(KafkaProperties.TOPIC, request));
            response.setStatus(ResponseStatus.SUCCESS);
            response.setDescription("Logging Event is sent to kafka server successfully!!!");

        }
        catch(Exception e)
        {
            logger.error("Sent Logging Event to kafka failed : "+e.getMessage());
            response.setStatus(ResponseStatus.FAILED);
            response.setDescription(e.getMessage());

        }


        return response;
    }
}

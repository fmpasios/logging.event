package org.pollfish.KafkaConsumer;

import com.datastax.driver.core.utils.UUIDs;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.log4j.Logger;
import org.pollfish.Common.CommonUtilities;
import org.pollfish.DatabaseConnector.CassandraConnector;
import org.pollfish.Model.DBEvent;
import org.pollfish.Model.DBLogMessage;
import org.pollfish.logging.service.LoggingEvent;

import java.util.Date;


/**
 * Created by fmpasios on 15/9/2018.
 */
public class KafkaConsumerImpl {

    final static Logger logger = Logger.getLogger(KafkaConsumerImpl.class);


    public static void start() throws  Exception
    {

        try (Consumer<String, LoggingEvent> consumer = KafkaConsumerInit.createConsumer()) {

            CassandraConnector.createDatabaseConnector();
            while (true) {
                ConsumerRecords<String, LoggingEvent> messages = consumer.poll(100);
                messages.forEach(message ->
                        saveLoggingEvent(message.value())
                );
                consumer.commitAsync();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new Exception();
        }






    }


    private static void saveLoggingEvent(LoggingEvent event)
    {
        //Creating database object

            try {
                logger.error("Saving Message to Cassandra DB : " + CommonUtilities.loggingRequestToString(event));

                DBLogMessage dbLogMessage = new DBLogMessage();

                dbLogMessage.setLevel(event.getMessage().getLevel());
                dbLogMessage.setMessage(event.getMessage().getMessage());
                dbLogMessage.setTimestamp(CommonUtilities.convertToDate(event.getMessage().getLogTimestamp()));


                DBEvent dbEvent = new DBEvent();

                dbEvent.setTimestamp(CommonUtilities.convertToDate(event.getEventTimestamp()));
                dbEvent.setDeviceId(event.getDeviceID());
                dbEvent.setIp(event.getIpAddress());
                dbEvent.setLogMessage(dbLogMessage);
                dbEvent.setVersion(event.getVersion());
                dbEvent.setId(UUIDs.timeBased());


                MappingManager manager = new MappingManager(CassandraConnector.getSession());
                Mapper<DBEvent> mapper = manager.mapper(DBEvent.class);

                mapper.save(dbEvent);

                logger.error("Message has been saved successfully to database!!!");


            }
            catch(Exception e)
            {

                logger.error("Database insertion error : "+e.getMessage());
                logger.error(e);
            }




    }






}

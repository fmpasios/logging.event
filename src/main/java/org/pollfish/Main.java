package org.pollfish;

import org.apache.log4j.Logger;
import org.apache.thrift.transport.TTransportException;
import org.pollfish.KafkaConsumer.KafkaConsumerImpl;
import org.pollfish.ThriftClient.LoggingEventServiceClient;
import org.pollfish.ThriftServer.LoggingEventServiceServer;

import java.util.concurrent.CompletableFuture;

/**
 * Created by fmpasios on 16/9/2018.
 */
public class Main {

    final static Logger logger = Logger.getLogger(Main.class);


    public static void main(String [] args)
    {


        try {


            CompletableFuture<Void> startThriftServer = CompletableFuture.runAsync(() -> {

                try {
                    LoggingEventServiceServer.start();
                } catch (TTransportException e) {
                    logger.error(e);
                }

            });


            CompletableFuture<Void> startKafkaConsumer = CompletableFuture.runAsync(() -> {

                try {
                    KafkaConsumerImpl.start();
                } catch (Exception e) {
                    logger.error(e);
                }

            });


            LoggingEventServiceClient.start();



        }
        catch(Exception e)
        {

            logger.error("The logging event process has been terminated. Error : "+e.getMessage());
            logger.error(e);

        }
    }


}

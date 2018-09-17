package org.pollfish.ThriftClient;

import org.apache.log4j.Logger;
import org.pollfish.Common.CommonUtilities;
import org.pollfish.logging.service.*;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by fmpasios on 13/9/2018.
 */
public class LoggingEventServiceClient {


    final static Logger logger = Logger.getLogger(LoggingEventServiceClient.class);

    public static void start() throws Exception
    {
        try (TTransport transport = new TSocket("localhost", 9090))
        {
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            LoggingEventService.Client client = new LoggingEventService.Client(protocol);


            while(true)
            {


                LoggingEvent request=CommonUtilities.generateRequest();
                logger.error("Start sending message to thrift server !!!");
                logger.error(CommonUtilities.loggingRequestToString(request));
                LoggingResponse response =client.saveEvent(request);

                logger.error("Response status : " + response.getStatus());
                logger.error("Response description : " + response.getDescription());
                Thread.sleep(1000);


            }


        }
        catch(Exception e)
        {

            logger.error("Exception message : "+ e.getMessage());
            throw new Exception(e);

        }



    }

}

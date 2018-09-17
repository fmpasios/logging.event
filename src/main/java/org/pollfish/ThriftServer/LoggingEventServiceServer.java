package org.pollfish.ThriftServer;

import org.apache.log4j.Logger;
import org.pollfish.logging.service.*;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;
import org.pollfish.LoggingEventServiceImplementation.LoggingEventServiceImpl;

/**
 * Created by fmpasios on 13/9/2018.
 */
public class LoggingEventServiceServer {

    private static TSimpleServer server;

    final static Logger logger = Logger.getLogger(LoggingEventServiceServer.class);


    public static void start() throws TTransportException {

                try {
                    logger.error("Starting thrift server ....");
                    TServerTransport serverTransport = new TServerSocket(9090);
                    server = new TSimpleServer(new TServer.Args(serverTransport).processor(new LoggingEventService.Processor<>(new LoggingEventServiceImpl())));
                    server.serve();
                    logger.error("Thrift server is started successfully!!");
                } catch (TTransportException e) {
                    logger.error("Start thrift server error message : " + e.getMessage());
                    throw new TTransportException();
                }



    }



}

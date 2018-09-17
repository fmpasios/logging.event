# Logging Event Project

In the src folder you can find the source code of the project. The pom.xml is responsible for the building of the project as well as a shell script (buildRun.sh) which will generate and execute a jar file when it is executed.

## Preparing the localhost environment

You have to install the following : 

1. Thrift version 0.11.0
2. Apache maven 3.3.9
3. ZooKeeper  3.4.8-1 
4. kafka server 2.12-1.0.0
5. Cassandra database 3.11.3
6. Java JDK 8

The following modules listen to the default ports , more specifically :
The thrift server must listen to the port 9090 , the Kafka server must listen to the port 9092 , the Zookeeper service must listen to the port 2181 , and the Cassandra database server must listen to the port 9042.

## Preparing the kafka topic

The kafka producer sends messages to the "logging" topic and the kafka consumer receives from it. Thus , a new topic named "logging" must be created on the kafka server.

## Preparing the Cassandra database

The keyspace and the tables that belong to it are created during the execution of the jar file , thus , the database scripts don't need to be executed manually. Nevertheless, I cite them in order to know the database schema :

CREATE KEYSPACE logging
  WITH REPLICATION = { 
   'class' : 'SimpleStrategy', 
   'replication_factor' : 1 
  };

CREATE TYPE logging.log_message (
  level text,
  log_time timestamp, 
  message text
);

  
CREATE TABLE logging.event ( 
  id UUID PRIMARY KEY, 
  version smallint,
  event_time timestamp,
  device_id text,
  ip_address text,
  message FROZEN<log_message>
 );
  
## Structure of the Logging Event

In the src/main/java/thrift you can find the thrift file which is responsible for the creation of the API for the client-server interaction as well as the structure of the Logging Event. I cite the structure of Logging Event :


struct LoggingEvent{
1: i16 version,
2: DateTime eventTimestamp,
3: LoggingMessage message,
4: string deviceID,
5: string ipAddress
}


struct DateTime{
1: i32 year,
2: i32 month,
3: i32 dayOfMonth,
4: i32 hour,
5: i32 minute,
6: i32 second
}

For the time of the Logging Event I chose the above structure as it expresses the accuracy of the creation of the event and it is easily transferred from one system to another. Furthermore , I added two more fields in the Logging Event structure (deviceID and ip address) which express the uniqueness of the device the Logging Event comes from. Also , I cite the structure of the log message : 


struct LoggingMessage{
1: string level,
2: DateTime logTimestamp,
3: string message
}


## Running the project

Execute the buildRun.sh shell script file. The buildRun.sh cleans and packages the source code with maven , creating the executable jar (logging-event-service-1.0.0.jar) in the target folder. Then , execute the above jar file , starting the Logging Event procedure. Finally , the log file (loggingEvent.log) is created in the ${user.home}/logging.event.


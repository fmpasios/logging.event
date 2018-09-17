package org.pollfish.DatabaseConnector;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

/**
 * Created by fmpasios on 16/9/2018.
 */
public class CassandraConnector {

    private static Cluster cluster;

    final static String DB_SERVER_IP="127.0.0.1";

    final static String KEY_SPACE="logging";

    final static int DB_SERVER_PORT=9042;

    final static String CREATING_KEY_SPACE="CREATE KEYSPACE IF NOT EXISTS logging" +
            "    WITH replication = {'class': 'SimpleStrategy'," +
            "                        'replication_factor' : 1};";


    final static String CREATING_LOG_MESSAGE_TYPE="CREATE TYPE IF NOT EXISTS logging.log_message (" +
            "  level text," +
            "  log_time timestamp," +
            "  message text" +
            ");";

    final static String CREATING_EVENT_TABLE="CREATE TABLE IF NOT EXISTS logging.event (" +
            "  id UUID PRIMARY KEY," +
            "  version smallint," +
            "  event_time timestamp," +
            "  device_id text," +
            "  ip_address text," +
            "  message FROZEN<log_message>" +
            " );";


    public static void createDatabaseConnector()
    {

        cluster =  Cluster.builder().withPort(DB_SERVER_PORT)
                .addContactPoints(DB_SERVER_IP)
                .build();
        Session session=cluster.connect();

        session.execute(CREATING_KEY_SPACE);
        session.execute(CREATING_LOG_MESSAGE_TYPE);
        session.execute(CREATING_EVENT_TABLE);


        session.close();


    }



    public static Session getSession()
    {

        return cluster.connect(KEY_SPACE);


    }






}

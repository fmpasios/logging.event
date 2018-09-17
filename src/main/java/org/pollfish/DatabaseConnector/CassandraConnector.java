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


    public static void createDatabaseConnector()
    {

        cluster =  Cluster.builder().withPort(DB_SERVER_PORT)
                .addContactPoints(DB_SERVER_IP)
                .build();

    }



    public static Session getSession()
    {

        return cluster.connect(KEY_SPACE);


    }






}

package org.pollfish.Model;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by fmpasios on 16/9/2018.
 */


@UDT(keyspace = "logging", name = "log_message")
public class DBLogMessage {

    @Field(name = "level")
    private String level;


    @Field(name = "log_time")
    private Date timestamp;



    @Field(name = "message")
    private String message;


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}





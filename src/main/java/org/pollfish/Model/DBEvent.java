package org.pollfish.Model;

import com.datastax.driver.mapping.annotations.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * Created by fmpasios on 16/9/2018.
 */

@Table(keyspace = "logging", name = "event")
public class DBEvent {

    @PartitionKey
    @Column(name = "id")
    private UUID id;

    @Column(name = "version")
    private short version;

    @Column(name = "event_time")
    private Date timestamp;

    @Column(name = "device_id")
    private String deviceId;


    @Column(name = "ip_address")
    private String ip;

    @Frozen
    @Column(name = "message")
    private DBLogMessage logMessage;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public String getDeviceId() {
        return deviceId;
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public DBLogMessage getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(DBLogMessage logMessage) {
        this.logMessage = logMessage;
    }
}

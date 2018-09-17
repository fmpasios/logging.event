package org.pollfish.Common;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.log4j.Logger;
import org.pollfish.logging.service.DateTime;
import org.pollfish.logging.service.LoggingEvent;
import org.pollfish.logging.service.LoggingMessage;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by fmpasios on 14/9/2018.
 */
public class CommonUtilities {

    final static Logger logger = Logger.getLogger(CommonUtilities.class);


    private static Random random=new Random();
    private static List<String> levels= Arrays.asList("ALL","TRACE","DEBUG","DEBUG","INFO","WARN","ERROR","FATAL");
    private static RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('a', 'z').build();

    public static String loggingRequestToString(LoggingEvent request)
    {

        String message = "Device Information | DeviceID : "+request.getDeviceID()+" , IP Address : "+request.getIpAddress()+" , Version : "+request.getVersion()+", TimeStamp : "+ DateFormatUtils.format(convertToDate(request.eventTimestamp), "yyyy-MM-dd HH:mm:SS")
        +", Log Information | Log level : "+request.getMessage().getLevel()+", Log Timestamp :"
                +DateFormatUtils.format(convertToDate(request.getMessage().getLogTimestamp()), "yyyy-MM-dd HH:mm:SS") + ", Log Message :" + request.getMessage().getMessage();

        return message;

    }


    public static Date convertToDate(DateTime dt)
    {


        LocalDateTime time=  LocalDateTime.of(dt.getYear(),dt.getMonth(),dt.getDayOfMonth(),dt.getHour(),dt.getMinute(),dt.getSecond());

        Instant instant = time.atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);



    }

    public static LoggingEvent generateRequest()
    {

        DateTime logdt=new DateTime();

        logdt.setYear(2018);
        logdt.setMonth(random.nextInt(12) + 1);

        if(logdt.getMonth()==2)
        {

            logdt.setDayOfMonth(random.nextInt(28) + 1);

        }
        else
        {
            logdt.setDayOfMonth(random.nextInt(30) + 1);

        }

        logdt.setHour(random.nextInt(24));
        logdt.setMinute(random.nextInt(60));
        logdt.setSecond(random.nextInt(60));


        LoggingMessage event =new LoggingMessage();
        event.setLogTimestamp(logdt);

        event.setLevel(levels.get(random.nextInt(levels.size())));

        event.setMessage(generator.generate(20));

        DateTime eventdt=new DateTime();

        eventdt.setYear(2018);
        eventdt.setMonth(random.nextInt(12) + 1);

        if(eventdt.getMonth()==2)
        {

            eventdt.setDayOfMonth(random.nextInt(28) + 1);

        }
        else
        {
            eventdt.setDayOfMonth(random.nextInt(30) + 1);

        }
        eventdt.setHour(random.nextInt(24));
        eventdt.setMinute(random.nextInt(60));
        eventdt.setSecond(random.nextInt(60));

        LoggingEvent request = new LoggingEvent();

        request.setDeviceID(UUID.randomUUID().toString());

        request.setEventTimestamp(eventdt);

        request.setVersion((short)1);
        request.setIpAddress(generateIp());


        request.setMessage(event);




        return request;



    }

    private static String generateIp()
    {


        return random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256) + "." + random.nextInt(256);


    }

}

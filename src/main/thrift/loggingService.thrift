

namespace java org.pollfish.logging.service


exception InvalidOperationException{
1: i16 codeError,
2: string descriptionError
}

struct LoggingMessage{
1: string level,
2: DateTime logTimestamp,
3: string message
}

struct LoggingResponse{
1: ResponseStatus status,
2: optional string description
}



struct DateTime{
1: i32 year,
2: i32 month,
3: i32 dayOfMonth,
4: i32 hour,
5: i32 minute,
6: i32 second
}



enum ResponseStatus{
SUCCESS = 0,
FAILED = 1
}

struct LoggingEvent{
1: i16 version,
2: DateTime eventTimestamp,
3: LoggingMessage message,
4: string deviceID,
5: string ipAddress
}



service LoggingEventService{

LoggingResponse saveEvent(1: LoggingEvent request) throws (1: InvalidOperationException ex)

}










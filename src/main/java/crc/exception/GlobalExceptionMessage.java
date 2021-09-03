package crc.exception;

public class GlobalExceptionMessage extends Exception {
    public GlobalExceptionMessage(){
        super();
    };

    public GlobalExceptionMessage(String message){
        super(message);
    }
}

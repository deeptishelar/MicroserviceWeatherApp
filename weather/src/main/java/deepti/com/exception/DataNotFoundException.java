package deepti.com.exception;

public class DataNotFoundException extends WeatherAppGlobalException {
    public DataNotFoundException() {
        super("Requested entity not present in the DB.", GlobalErrorCode.ERROR_ENTITY_NOT_FOUND);
    }

    public DataNotFoundException (String message) {
        super(GlobalErrorCode.NO_DATA, message);
    }

}

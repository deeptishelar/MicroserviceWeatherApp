package deepti.com.exception;

public class WeatherAppGlobalException extends RuntimeException {

    private String code;
    private String message;

    public WeatherAppGlobalException(String code) {
        this.code = code;
    }

    public WeatherAppGlobalException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}

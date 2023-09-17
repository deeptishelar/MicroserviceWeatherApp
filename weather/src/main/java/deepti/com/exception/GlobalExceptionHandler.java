package deepti.com.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WeatherAppGlobalException.class)
    protected ResponseEntity handleGlobalException(WeatherAppGlobalException globalException, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(globalException.getCode(),
                        (globalException.getMessage())));
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity handleException(Exception e, Locale locale) {
        return ResponseEntity
                .badRequest()
                .body("Exception occur inside API " + e);
    }
}

package br.eng.jonathan.ntoerp.exception_handler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    /**
     * Use a simple {@link NotFoundException}.
     * @author Jonathan Nascimento
     * @since 1.0
     * @serialData 2024-11-28
     * @return {@link ResponseEntityExceptionHandler}
     */
    public NotFoundException() {
        super();
    }

    /**
     * Use {@link NotFoundException} with a message in your body and cause of error.
     * @author Jonathan Nascimento
     * @since 1.0
     * @serialData 2024-11-28
     * @return {@link ResponseEntityExceptionHandler}
     */
    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Use {@link NotFoundException} with a message in your body.
     * @author Jonathan Nascimento
     * @since 1.0
     * @serialData 2024-11-28
     * @return {@link ResponseEntityExceptionHandler}
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * Use {@link NotFoundException} with a error in your body.
     * @author Jonathan Nascimento
     * @since 1.0
     * @serialData 2024-11-28
     * @return {@link ResponseEntityExceptionHandler}
     */
    public NotFoundException(Throwable cause) {
        super(cause);
    }


}

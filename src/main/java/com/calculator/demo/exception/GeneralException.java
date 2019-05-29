package com.calculator.demo.exception;

import java.text.MessageFormat;

public class GeneralException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GeneralException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor de excepcion con mensaje simple
     * 
     * @param message
     *            mensaje basico de la excepcion
     */
    public GeneralException(String message) {
        super(message);
    }

    /**
     * Constructor de la excepcion con mensaje de estilo 
     * 
     * @param message
     *            mensaje formateado
     * @param params
     *            parametros del mensaje
     */
    public GeneralException(String message, Object... params) {
        super(MessageFormat.format(String.valueOf(message), params));
    }

    /**
     * Constructor con mensaje adicional a la informacion de excepcion causante
     * 
     * @param message
     *            mensaje adicional
     * @param cause
     *            causa real de la excepcion
     */
    public GeneralException(String message, Throwable cause) {
        super(message, cause);
    }
}

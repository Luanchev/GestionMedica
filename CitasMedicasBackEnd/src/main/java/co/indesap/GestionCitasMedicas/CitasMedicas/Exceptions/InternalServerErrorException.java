package co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException  extends CitaException{
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message, String code) {
        super(code, HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
    }
}

package co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions;

import co.indesap.GestionCitasMedicas.CitasMedicas.DTOs.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFoundException extends CitaException{

    private static final long serialVersionUID = 1L;

    public NotFoundException(String code, String message){
        super(code, HttpStatus.NOT_FOUND.value(),message);
    }
    public NotFoundException(String code, String message, ErrorDto data){
        super(code, HttpStatus.NOT_FOUND.value(),message, Arrays.asList(data));
    }
}

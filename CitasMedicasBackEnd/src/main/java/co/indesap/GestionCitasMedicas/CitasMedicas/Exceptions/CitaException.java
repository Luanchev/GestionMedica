package co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions;

import co.indesap.GestionCitasMedicas.CitasMedicas.DTOs.ErrorDto;
import java.util.ArrayList;
import java.util.List;

public class CitaException extends Exception{
    private final String code;
    private final int responseCode;
    private final List<ErrorDto> errorList = new ArrayList<>();



    public CitaException(String code, int responseCode, String message) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
    }

    public CitaException(String code,int responseCode, String message,  List<ErrorDto> errorList) {
        super(message);
        this.code = code;
        this.responseCode = responseCode;
        this.errorList.addAll(errorList);
    }

    public String getCode() {
        return code;
    }

    public List<ErrorDto> getErrorList() {
        return errorList;
    }

    public int getResponseCode() {
        return responseCode;
    }
}

package co.indesap.GestionCitasMedicas.CitasMedicas.Responses;

import java.io.Serial;
import java.io.Serializable;

public class CitaResponse<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String status;
    private String code;
    private String message;
    private T data;

    public CitaResponse(String code, String message, String status, T data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

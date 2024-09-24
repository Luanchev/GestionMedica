package co.indesap.GestionCitasMedicas.CitasMedicas.DTOs;

import java.io.Serial;
import java.io.Serializable;

public class ErrorDto implements Serializable {
    @Serial
    private static final long serialVersionUID =1L;

    private String name;
    private String value;

    public ErrorDto(String name, String value){
        this.name=name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

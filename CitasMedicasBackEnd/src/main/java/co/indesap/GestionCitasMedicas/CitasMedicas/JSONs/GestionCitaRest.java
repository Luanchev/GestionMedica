package co.indesap.GestionCitasMedicas.CitasMedicas.JSONs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GestionCitaRest {
    @JsonProperty("CITA_ID")
    private Long id;

    @JsonProperty("FECHA_CITA")
    private Date fechaCita;

    @JsonProperty("PACIENTE_ID")
    private Long pacienteId;

    @JsonProperty("CEDULA")
    private String cedula;

    @JsonProperty("PACIENTE_NOMBRE")
    private String pacienteNombre;

    @JsonProperty("PACIENTE_APELLIDO")
    private String pacienteApellido;

    @JsonProperty("PACIENTE_EMAIL")
    private String pacienteEmail;

    @JsonProperty("PACIENTE_CELULAR")
    private String pacienteCelular;

    @JsonProperty("MEDICO_ID")
    private Long medicoId;

    @JsonProperty("DISPONIBILIDAD_ID")
    private Long disponibilidadId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPacienteNombre() {
        return pacienteNombre;
    }

    public void setPacienteNombre(String pacienteNombre) {
        this.pacienteNombre = pacienteNombre;
    }

    public String getPacienteApellido() {
        return pacienteApellido;
    }

    public void setPacienteApellido(String pacienteApellido) {
        this.pacienteApellido = pacienteApellido;
    }

    public String getPacienteEmail() {
        return pacienteEmail;
    }

    public void setPacienteEmail(String pacienteEmail) {
        this.pacienteEmail = pacienteEmail;
    }

    public String getPacienteCelular() {
        return pacienteCelular;
    }

    public void setPacienteCelular(String pacienteCelular) {
        this.pacienteCelular = pacienteCelular;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public Long getDisponibilidadId() {
        return disponibilidadId;
    }

    public void setDisponibilidadId(Long disponibilidadId) {
        this.disponibilidadId = disponibilidadId;
    }

}

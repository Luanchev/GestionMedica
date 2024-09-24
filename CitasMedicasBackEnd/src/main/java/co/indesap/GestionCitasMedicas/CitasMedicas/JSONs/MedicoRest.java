package co.indesap.GestionCitasMedicas.CitasMedicas.JSONs;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.DisponibilidadEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicoRest {
    @JsonProperty("MEDICO_ID")
    private Long medicoId;

    @JsonProperty("NOMBRE")
    private String nombre;

    @JsonProperty("APELLIDO")
    private String apellido;

    @JsonProperty("ESPECIALIDAD")
    private String especialidad;

    @JsonProperty("CITAS")
    private List<CitaEntity> turns;

    @JsonProperty("DISPONIBILIDAD")
    private List<DisponibilidadEntity> disponibilidad;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<DisponibilidadEntity> getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(List<DisponibilidadEntity> disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CitaEntity> getTurns() {
        return turns;
    }

    public void setTurns(List<CitaEntity> turns) {
        this.turns = turns;
    }
}

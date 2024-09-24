package co.indesap.GestionCitasMedicas.CitasMedicas.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="CITAS")
public class CitaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CITA_ID", unique = true, nullable = false)
    private Long citaId;

    @Column(name = "CODIGO_UNICO_CITA")
    private String codigoUnico;

    @Column(name = "FECHA_CITA")
    private Date fechaCita;

    @Column(name = "DISPONIBILIDAD")
    private String disponibilidad;

    //CREAMOS LAS RELACIONES DE LAS DOS TABLAS
    @ManyToOne
    @JoinColumn(name = "PACIENTE_ID")
    private PacienteEntity pacientes;

    @ManyToOne
    @JoinColumn(name = "MEDICO_ID")
    private MedicoEntity medicos;

    public Long getCitaId() {
        return citaId;
    }

    public void setCitaId(Long citaId) {
        this.citaId = citaId;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public PacienteEntity getPacientes() {
        return pacientes;
    }

    public void setPacientes(PacienteEntity pacientes) {
        this.pacientes = pacientes;
    }

    public MedicoEntity getMedicos() {
        return medicos;
    }

    public void setMedicos(MedicoEntity medicos) {
        this.medicos = medicos;
    }
}

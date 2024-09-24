package co.indesap.GestionCitasMedicas.CitasMedicas.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name ="DISPONIBILIDAD")
public class DisponibilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DISPONIBILIDAD_ID")
    private Long id;

    @Column(name = "FECHA_CITA")
    private Date fechaCita;

    @Column(name = "HORA_INICIAL")
    private String horaInicial;

    @Column(name = "HORA_FINAL")
    private String horafinal;

    @ManyToOne
    @JoinColumn(name = "MEDICO_ID")
    @JsonBackReference //Esto evita la serializacion para que en postman no se hagan las respuestas infinitas --debe de ser en la relacion manytoone
    private MedicoEntity medicos; //esta variable debe llamarse igual que en la entidad a la cual se va a relacionar

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

    public String getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(String horafinal) {
        this.horafinal = horafinal;
    }

    public MedicoEntity getMedicos() {
        return medicos;
    }

    public void setMedicos(MedicoEntity medicos) {
        this.medicos = medicos;
    }
}

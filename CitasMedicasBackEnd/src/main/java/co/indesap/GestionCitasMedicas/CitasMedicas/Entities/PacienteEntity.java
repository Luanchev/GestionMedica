package co.indesap.GestionCitasMedicas.CitasMedicas.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name ="PACIENTES")
public class PacienteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACIENTE_ID", unique = true, nullable = false)
    private Long pacienteId;

    @Column(name = "CEDULA")
    private String cedula;

    @Column(name = "NOMBRE")
    private String nombre;


    @Column(name = "EMAIL")
    private String email;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "CELULAR")
    private String celular;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "pacientes")
    private List<CitaEntity> citas;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<CitaEntity> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaEntity> citas) {
        this.citas = citas;
    }
}

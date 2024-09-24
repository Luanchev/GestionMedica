package co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.PacienteRepository;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity,Long> {
    Optional<PacienteEntity> findById(Long id);
    Optional<PacienteEntity> findByCedula(String cedula);

}

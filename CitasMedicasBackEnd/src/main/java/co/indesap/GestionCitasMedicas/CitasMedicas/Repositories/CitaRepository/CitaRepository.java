package co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.CitaRepository;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CitaRepository extends JpaRepository<CitaEntity,Long> {
    Optional<CitaEntity> findById(Long id);
    List<CitaEntity> findByPacientesCedula(String cedula);
    Optional<CitaEntity> findByCodigoUnico(String codigoUnico);

    @Modifying
    @Transactional
    Optional<CitaEntity> deleteByCodigoUnico(String codigoUnico);


}

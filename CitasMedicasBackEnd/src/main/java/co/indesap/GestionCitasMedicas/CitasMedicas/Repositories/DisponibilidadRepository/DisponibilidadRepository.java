package co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.DisponibilidadRepository;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.DisponibilidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DisponibilidadRepository extends JpaRepository<DisponibilidadEntity,Long> {
    Optional<DisponibilidadEntity> findById(Long id);

}

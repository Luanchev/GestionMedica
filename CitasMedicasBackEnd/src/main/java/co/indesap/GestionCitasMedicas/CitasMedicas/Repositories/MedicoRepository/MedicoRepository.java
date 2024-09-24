package co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.MedicoRepository;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Optional<MedicoEntity> findById(Long id);
    List<MedicoEntity> findByEspecialidad(String especialidad);

    //cuando se hagan queries se debe poner FROM - el nombre de la entidad no de la tabla
    @Query("SELECT DISTINCT m.especialidad FROM MedicoEntity m")
    List<String> findDistinctEspecialidades();


}

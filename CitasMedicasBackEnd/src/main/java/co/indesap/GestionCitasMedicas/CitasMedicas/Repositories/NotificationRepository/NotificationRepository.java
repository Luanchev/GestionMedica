package co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.NotificationRepository;


import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    Optional<NotificationEntity> findByTemplateCode(String templateCode);
    boolean existsByTemplateCode(String templateCode);
    Optional<NotificationEntity> findByTemplateCodeIgnoreCase(String templateCode);

    Optional<NotificationEntity> findById(Integer id);
}

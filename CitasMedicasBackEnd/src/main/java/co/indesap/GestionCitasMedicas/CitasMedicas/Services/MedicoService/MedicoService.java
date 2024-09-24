package co.indesap.GestionCitasMedicas.CitasMedicas.Services.MedicoService;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.MedicoEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.MedicoRest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicoService {
    List<MedicoRest> getMedicos() throws CitaException;
    MedicoRest getMedicoById(Long id) throws CitaException;
    List<MedicoRest> getMedicosByEspecialidad(String especialidad) throws CitaException;
    List<String>getEspecialidades() throws CitaException;


}

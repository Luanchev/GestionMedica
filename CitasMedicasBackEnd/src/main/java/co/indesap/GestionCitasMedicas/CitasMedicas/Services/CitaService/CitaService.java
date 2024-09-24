package co.indesap.GestionCitasMedicas.CitasMedicas.Services.CitaService;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.GestionCitaRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.MedicoRest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CitaService {

    List<GestionCitaRest> getCitas() throws CitaException;
    GestionCitaRest getCitaById(Long citaId) throws CitaException;
    List<CitaEntity> getCitasByPacienteCedula(String cedula);
    String createCitaMedica(GestionCitaRest gestionCitaRest) throws  CitaException;
    String deleteCita(String codigoUnico) throws CitaException;
}

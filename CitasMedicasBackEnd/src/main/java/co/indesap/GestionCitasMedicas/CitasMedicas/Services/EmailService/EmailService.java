package co.indesap.GestionCitasMedicas.CitasMedicas.Services.EmailService;


import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;

public interface EmailService {
    public String processSendEmail(final String receiver, String templateCode, String CurrentName, String currentTime, String medicoName, String especialidad) throws CitaException;
}

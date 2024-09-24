package co.indesap.GestionCitasMedicas.CitasMedicas.Services.ServiceImpl.CitaServiceImpl;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.CitaEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.DisponibilidadEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.MedicoEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.PacienteEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.InternalServerErrorException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.NotFoundException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.GestionCitaRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.CitaRepository.CitaRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.DisponibilidadRepository.DisponibilidadRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.MedicoRepository.MedicoRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.PacienteRepository.PacienteRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.CitaService.CitaService;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.EmailService.EmailService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CitaServiceImpl implements CitaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitaServiceImpl.class);

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private DisponibilidadRepository disponibilidadRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private EmailService emailService;



    public static final ModelMapper modelMapper = new ModelMapper();


    //buscamos una cita por su Id y la convertimos de entity a dto que seria el rest, para que la respuesta sea correcta
    @Override
    public GestionCitaRest getCitaById(Long citaId) throws CitaException {
        return modelMapper.map(getCitaEntity(citaId), GestionCitaRest.class); // aqui traemos la funcion getCita
    }

    @Override
    public List<GestionCitaRest> getCitas() throws CitaException {
        //mediante JPA buscamos todos los medicos
        final List<CitaEntity>citaEntities = citaRepository.findAll();
        return citaEntities.stream().map(service -> modelMapper.map(service, GestionCitaRest.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<CitaEntity> getCitasByPacienteCedula(String cedula) {
        return citaRepository.findByPacientesCedula(cedula);
    }

    @Override
    public String createCitaMedica(GestionCitaRest gestionCitaRest) throws CitaException {

        //validamos si existe el medico seleccionado
        final MedicoEntity medico = medicoRepository.findById(gestionCitaRest.getMedicoId())
                .orElseThrow(()-> new NotFoundException("MEDICO_NOT_FOUND", "El medico no fue encontrado"));

        // Validar cédula antes de proceder
        if (!isCedulaValida(gestionCitaRest.getCedula())) {
            throw new NotFoundException("CÉDULA_INVALIDA", "La cédula proporcionada es inválida.");
        }

        //Validamos si existe el paciente, si el paciente no existe se crea
        PacienteEntity paciente = pacienteRepository.findByCedula(gestionCitaRest.getCedula())
                .orElseGet(() -> {
                    // Validar cédula antes de crear un nuevo paciente


                    System.out.println("El paciente no existe, creando un nuevo paciente.");
                    // Si el paciente no existe, lo creamos
                    PacienteEntity nuevoPaciente = new PacienteEntity();

                    nuevoPaciente.setNombre(gestionCitaRest.getPacienteNombre());
                    nuevoPaciente.setCedula(gestionCitaRest.getCedula());
                    nuevoPaciente.setApellido(gestionCitaRest.getPacienteApellido());
                    nuevoPaciente.setEmail(gestionCitaRest.getPacienteEmail());
                    nuevoPaciente.setCelular(gestionCitaRest.getPacienteCelular());

                    // Guardar el nuevo paciente en la base de datos
                    return pacienteRepository.save(nuevoPaciente);
                });



        // Verificamos si el paciente ya tiene una cita
        List<CitaEntity> citasExistentes = citaRepository.findByPacientesCedula(gestionCitaRest.getCedula());
        if (!citasExistentes.isEmpty()) {
            throw new NotFoundException("CITA_EXISTENTE", "El paciente ya tiene una cita programada");
        }

        // Validamos si la disponibilidad existe para la fecha seleccionada
        final DisponibilidadEntity disponibilidad = disponibilidadRepository.findById(gestionCitaRest.getDisponibilidadId())
                .orElseThrow(() -> new NotFoundException("DISPONIBILIDAD_NOT_FOUND", "La disponibilidad para el medico " + medico.getNombre() + " no fue encontrada"));


        String codigoUnico = generateCodigoUnico(paciente);
        final CitaEntity cita = new CitaEntity();
        cita.setCodigoUnico(codigoUnico); //este codigo va a ser la cedula para que el cliente cancele su unica cita
        cita.setFechaCita(gestionCitaRest.getFechaCita());
        cita.setDisponibilidad(disponibilidad.getHoraInicial());
        cita.setMedicos(medico);
        cita.setPacientes(paciente);

        try {
            citaRepository.save(cita);

        }catch (final Exception e){
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");

        }
        //para poder enviar el email y que aparezca el nombre y la hora de la cita traemos los datos de paciente y de cita
        this.emailService.processSendEmail(paciente.getEmail(), "CITA", cita.getPacientes().getNombre(), cita.getDisponibilidad(), cita.getMedicos().getNombre(), cita.getMedicos().getEspecialidad());

        return codigoUnico; //Este codigo es para que el cliente elimine su cita


    }

    @Transactional
    public String deleteCita(String codigoUnico) throws CitaException {

        CitaEntity cita = citaRepository.findByCodigoUnico(codigoUnico)
                .orElseThrow(() -> new NotFoundException("LOCATOR_NOT_FOUND", "LOCATOR_NOT_FOUND"));


        try {
            citaRepository.deleteByCodigoUnico(codigoUnico);
        } catch (Exception e) {
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }

       this.emailService.processSendEmail(cita.getPacientes().getEmail(), "CANCEL", cita.getPacientes().getNombre(),cita.getDisponibilidad(), cita.getMedicos().getNombre(), cita.getMedicos().getEspecialidad());

        return "CODIGO_DELETE";
    }

    private String generateCodigoUnico(PacienteEntity paciente)//El codigo unico para que el usuario cancele la cita va a ser la cedula ya que solo permitira crear una sola cita por cliente
            throws CitaException{
        return paciente.getCedula();
    }

    private CitaEntity getCitaEntity(Long citaId) throws CitaException {
        return citaRepository.findById(citaId)
                .orElseThrow(() -> new NotFoundException("SNOT-404-1", "CITA_NOT_FOUND"));
    }
    //con este servicio validamos si la cedula existe
    public boolean isCedulaValida(String cedula) {
        if (cedula == null || cedula.trim().isEmpty() || cedula.length() <= 5) {
            return false; // Cédula vacía o nula no es válida.
        }
        return true;
    }


}

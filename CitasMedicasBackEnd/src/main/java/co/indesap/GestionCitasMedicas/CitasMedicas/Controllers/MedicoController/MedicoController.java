package co.indesap.GestionCitasMedicas.CitasMedicas.Controllers.MedicoController;

import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.MedicoRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.Responses.CitaResponse;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.MedicoService.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gestionCitas/medicos")
@CrossOrigin(origins = "http://localhost:4200")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getMedicos", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<List<MedicoRest>> getMedicos() throws CitaException{
        return new CitaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", medicoService.getMedicos());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getById/{medicoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<MedicoRest> getMedicoByMedicoId(@PathVariable Long medicoId) throws CitaException{
        return new CitaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", medicoService.getMedicoById(medicoId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getByEspecialidad/{especialidad}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<List<MedicoRest>> getMedicosByEspecialidad(@PathVariable String especialidad) throws CitaException {
        List<MedicoRest> medicos = medicoService.getMedicosByEspecialidad(especialidad);
       return new CitaResponse<List<MedicoRest>>("Success",String.valueOf(HttpStatus.OK), "OK", medicos);
    }
    @GetMapping(value ="/especialidades", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<List<String>> getEspecialidades() throws CitaException{
        List<String> especialidades = medicoService.getEspecialidades();
        return new CitaResponse<List<String>>("Success",String.valueOf(HttpStatus.OK), "OK",especialidades);
    }




}

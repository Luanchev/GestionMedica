package co.indesap.GestionCitasMedicas.CitasMedicas.Controllers.CitaController;

import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.GestionCitaRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.MedicoRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.Responses.CitaResponse;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.CitaService.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/gestionCitas/citas")
@CrossOrigin(origins = "http://localhost:4200")
public class CitaController {

    @Autowired
    CitaService citaService;

    //buscamos todas las citas
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getCitas", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<List<GestionCitaRest>> getCitas() throws CitaException {
        return new CitaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK", citaService.getCitas());
    }

    //buscar una cita por id
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getCitaById/{citaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<GestionCitaRest> getCitaById(@PathVariable Long citaId) throws CitaException {
        return new CitaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                citaService.getCitaById(citaId));
    }

    //crear una reservacion
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/postCita", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<String> createCita(@RequestBody @Validated GestionCitaRest gestionCitaRest) throws CitaException {
        return new CitaResponse<>("Success", String.valueOf(HttpStatus.OK), "OK",
                citaService.createCitaMedica(gestionCitaRest));
    }

    //METODO PARA ELIMINAR  --POSTMAN = deleteCita?codigoUnico=24354025
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/deleteCita", produces = MediaType.APPLICATION_JSON_VALUE)
    public CitaResponse<String> deleteCita(@RequestParam String codigoUnico) throws CitaException {
        return new CitaResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                citaService.deleteCita(codigoUnico));
    }


}

package co.indesap.GestionCitasMedicas.CitasMedicas.Services.ServiceImpl.MedicoServiceImpl;

import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.MedicoEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.NotFoundException;
import co.indesap.GestionCitasMedicas.CitasMedicas.JSONs.MedicoRest;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.MedicoRepository.MedicoRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.MedicoService.MedicoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoServiceImpl  implements MedicoService {

    @Autowired
    MedicoRepository medicoRepository;
    public static final ModelMapper modelMapper = new ModelMapper();

    public MedicoServiceImpl(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    //servicio para traer todos los medicos
    @Override
    public List<MedicoRest> getMedicos() throws CitaException {
        //mediante JPA buscamos todos los medicos
        final List<MedicoEntity>medicoEntities = medicoRepository.findAll();
        return medicoEntities.stream().map(service -> modelMapper.map(service, MedicoRest.class))
                .collect(Collectors.toList());
    }

    //Este servicio se encarga de mopear o convertir de medicoEntity a medicoRest
    @Override
    public MedicoRest getMedicoById(Long medicoId) throws CitaException {
        return modelMapper.map(getMedicoEntity(medicoId),MedicoRest.class);
    }

    @Override
    public List<MedicoRest> getMedicosByEspecialidad(String especialidad) throws CitaException {
        List<MedicoEntity>medicoEntities = medicoRepository.findByEspecialidad(especialidad);
        return medicoEntities.stream()
                .map(medicoEntity -> modelMapper.map(medicoEntity, MedicoRest.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getEspecialidades() throws CitaException {
        return medicoRepository.findDistinctEspecialidades();
    }


    //Este servicio lo que hace es encontrar el medico mediante Id y devolverle la entidad medico para que luego getmedicoById lo mapee
    public MedicoEntity getMedicoEntity(Long medicoId) throws CitaException {
        return medicoRepository.findById(medicoId)
                .orElseThrow(()-> new NotFoundException("SNOT-404-1", "MEDICO_NOT_FOUND"));
    }
}

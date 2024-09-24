package co.indesap.GestionCitasMedicas.CitasMedicas.Services.ServiceImpl.EmailServiceImpl;


import co.indesap.GestionCitasMedicas.CitasMedicas.DTOs.EmailTemplateDto;
import co.indesap.GestionCitasMedicas.CitasMedicas.Entities.NotificationEntity;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.CitaException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.InternalServerErrorException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Exceptions.NotFoundException;
import co.indesap.GestionCitasMedicas.CitasMedicas.Repositories.NotificationRepository.NotificationRepository;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.EmailService.EmailService;
import co.indesap.GestionCitasMedicas.CitasMedicas.Services.ServiceImpl.CitaServiceImpl.CitaServiceImpl;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitaServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public String processSendEmail(String receiver, String templateCode, String currentName, String currentTime, String medicoName,String especialidad)
            throws CitaException {

        final EmailTemplateDto emailTemplateDto = findTemplateAndReplace(templateCode, currentName, currentTime, medicoName,especialidad );

        this.sendEmail(receiver, emailTemplateDto.getSubject(), emailTemplateDto.getTemplate());

        return "EMAIL_SENT";
    }
    private void sendEmail(final String receiver, final String subject, final String template) throws CitaException {
        final MimeMessage email = javaMailSender.createMimeMessage();
        final MimeMessageHelper content;

        try {
            content = new MimeMessageHelper(email, true);
            content.setSubject(subject);
            content.setTo(receiver);
            content.setText(template, true);
        } catch (Exception e) {
            LOGGER.error("INTERNAL_SERVER_ERROR", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        javaMailSender.send(email);

    }

    //BUSCAMOS EN LA BASE DE DATOS Y REEMPLAZAMOS, cuando vayamos a usar la funcion se deben traer la información en este caso de la tabla paciente que seria el currentname y currentTime de la tabla cita
    private EmailTemplateDto findTemplateAndReplace(final String templateCode, final String currentName,final String currentTime, final String medicoName, final String especialidad) throws CitaException {

        if (!notificationRepository.existsByTemplateCode(templateCode)) {
            throw new NotFoundException("TEMPLATE_NOT_FOUND", "No se encontró una plantilla con el código: " + templateCode);
        }
        final NotificationEntity notificationTemplate = notificationRepository
                .findByTemplateCodeIgnoreCase(templateCode.trim())  // Elimina espacios en blanco
                .orElseThrow(() -> new NotFoundException("TEMPLATE_NOT_FOUND", "CODE_TEMPLATE_NOT_FOUND"));

       /* final NotificationEntity notificationTemplate = notificationRepository.findByTemplateCode(templateCode)
                .orElseThrow(() -> new NotFoundException("TEMPLATE_NOT_FOUND", "CODE_TEMPLATE_NOT_FOUND"));*/

        final EmailTemplateDto emailTemplateDto = new EmailTemplateDto();
        emailTemplateDto.setSubject(notificationTemplate.getTemplateCode());
        emailTemplateDto.setTemplate(notificationTemplate.getTemplate().replaceAll("\\{"+"name"+"\\}", currentName )
        .replaceAll("\\{"+"time"+"\\}", currentTime ).replaceAll("\\{"+"doc"+"\\}",medicoName).replaceAll("\\{"+"especialidad"+"\\}",especialidad));

        return emailTemplateDto;
    }


}

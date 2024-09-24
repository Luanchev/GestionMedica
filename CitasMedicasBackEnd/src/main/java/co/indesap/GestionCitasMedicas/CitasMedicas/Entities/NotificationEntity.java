package co.indesap.GestionCitasMedicas.CitasMedicas.Entities;

import jakarta.persistence.*;

@Entity
@Table(name ="NOTIFICATION")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Lob
    @Column(name = "TEMPLATE")
    private String template;

    @Column(name = "TEMPLATE_CODE")
    private String templateCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}

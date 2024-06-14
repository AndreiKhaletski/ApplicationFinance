package by.it_academy.jd2.finance.dao.entity;

import by.it_academy.jd2.finance.service.api.enums.EnumStatusSendEmail;
import jakarta.persistence.*;

@Entity
@Table(name = "verification", schema = "app")
public class UserVerificationEntity {
    @Id
    @Column(name = "mail")
    private String mail;
    @Column(name = "code")
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private EnumStatusSendEmail enumStatusSendEmail;

    public UserVerificationEntity() {
    }

    public UserVerificationEntity(String mail, String code, EnumStatusSendEmail enumStatusSendEmail) {
        this.mail = mail;
        this.code = code;
        this.enumStatusSendEmail = enumStatusSendEmail;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EnumStatusSendEmail getEnumStatusSendEmail() {
        return enumStatusSendEmail;
    }

    public void setEnumStatusSendEmail(EnumStatusSendEmail enumStatusSendEmail) {
        this.enumStatusSendEmail = enumStatusSendEmail;
    }
}

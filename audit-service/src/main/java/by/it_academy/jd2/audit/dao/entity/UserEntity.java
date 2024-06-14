package by.it_academy.jd2.audit.dao.entity;

import by.it_academy.jd2.audit.service.api.dto.EnumRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "user", schema = "app")
public class UserEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "mail")
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EnumRole role;

    public UserEntity() {
    }

    public UserEntity(UUID uuid,
                      String mail,
                      String fio,
                      EnumRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public EnumRole getRole() {
        return role;
    }

    public void setRole(EnumRole role) {
        this.role = role;
    }
}

package by.it_academy.jd2.audit.dao.entity;

import by.it_academy.jd2.audit.service.api.dto.EssenceType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit", schema = "app")
public class AuditEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "dt_create")
    @CreationTimestamp
    private LocalDateTime dtCreate;
    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserEntity userEntity;
    @Column(name = "text")
    private String text;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EssenceType type;
    @Column(name = "id_entity")
    private String id;

    public AuditEntity() {
    }

    public AuditEntity(UUID uuid,
                       LocalDateTime dtCreate,
                       UserEntity userEntity,
                       String text,
                       EssenceType type,
                       String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.userEntity = userEntity;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EssenceType getType() {
        return type;
    }

    public void setType(EssenceType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

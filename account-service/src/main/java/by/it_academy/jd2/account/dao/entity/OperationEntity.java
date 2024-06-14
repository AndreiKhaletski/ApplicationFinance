package by.it_academy.jd2.account.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "operation", schema = "app")
public class OperationEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "uuid", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private AccountEntity accountEntity;

    @Id
    @Column(name = "uuid_operation")
    private UUID uuidOperation;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dt_create;
    @Column(name = "dt_update")
    @Version
    @UpdateTimestamp
    private LocalDateTime dt_update;
    @Column(name = "date")
    private Instant date;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private UUID category;
    @Column(name = "value")
    private Integer value;
    @Column(name = "currency")
    private UUID currency;

    public OperationEntity() {
    }

    public OperationEntity(AccountEntity accountEntity,
                           UUID uuidOperation,
                           LocalDateTime dt_create,
                           LocalDateTime dt_update,
                           Instant date,
                           String description,
                           UUID category,
                           Integer value,
                           UUID currency) {
        this.accountEntity = accountEntity;
        this.uuidOperation = uuidOperation;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.date = date;
        this.description = description;
        this.category = category;
        this.value = value;
        this.currency = currency;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public UUID getUuidOperation() {
        return uuidOperation;
    }

    public void setUuidOperation(UUID uuidOperation) {
        this.uuidOperation = uuidOperation;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public LocalDateTime getDt_update() {
        return dt_update;
    }

    public void setDt_update(LocalDateTime dt_update) {
        this.dt_update = dt_update;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategory() {
        return category;
    }

    public void setCategory(UUID category) {
        this.category = category;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }
}

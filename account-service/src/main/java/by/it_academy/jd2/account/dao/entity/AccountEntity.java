package by.it_academy.jd2.account.dao.entity;

import by.it_academy.jd2.account.service.api.enums.EnumCurrency;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accouts", schema = "app")
public class AccountEntity {
    @Id
    @Column(name = "uuid")
    private UUID uuidAccount;

    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @UpdateTimestamp
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "balance")
    private double balance;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EnumCurrency type;
    @Column(name = "currency")
    private UUID currency;

    public AccountEntity() {
    }

    public AccountEntity(UUID uuidAccount,
                         LocalDateTime dtCreate,
                         LocalDateTime dtUpdate,
                         String title,
                         String description,
                         double balance,
                         EnumCurrency type,
                         UUID currency) {
        this.uuidAccount = uuidAccount;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.type = type;
        this.currency = currency;
    }

    public UUID getUuidAccount() {
        return uuidAccount;
    }

    public void setUuidAccount(UUID uuidAccount) {
        this.uuidAccount = uuidAccount;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public EnumCurrency getType() {
        return type;
    }

    public void setType(EnumCurrency type) {
        this.type = type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }
}

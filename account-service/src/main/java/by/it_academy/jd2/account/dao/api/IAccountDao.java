package by.it_academy.jd2.account.dao.api;

import by.it_academy.jd2.account.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountDao extends JpaRepository<AccountEntity, UUID> {
}

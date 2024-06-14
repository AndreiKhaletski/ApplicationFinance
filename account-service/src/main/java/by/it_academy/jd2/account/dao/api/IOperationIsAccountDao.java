package by.it_academy.jd2.account.dao.api;

import by.it_academy.jd2.account.dao.entity.OperationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IOperationIsAccountDao extends JpaRepository<OperationEntity, UUID> {
    Page<OperationEntity> findAllByAccountEntityUuidAccount(UUID accountId, PageRequest of);

    Optional<OperationEntity> findByUuidOperation(UUID uuidOperation);

    void deleteByUuidOperation(UUID uuidOperation);
}

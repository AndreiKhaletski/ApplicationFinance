package by.it_academy.jd2.audit.service.api;

import by.it_academy.jd2.audit.dao.entity.AuditEntity;
import by.it_academy.jd2.audit.dao.entity.UserEntity;
import by.it_academy.jd2.audit.service.api.dto.PageOfAudit;

import java.util.Optional;
import java.util.UUID;

public interface IAuditService {
    UserEntity saveUser(UserEntity userEntity);
    AuditEntity saveAudit(AuditEntity auditEntity);
    Optional<AuditEntity> getByUuid(UUID uuid);

//    void create(AuditEntity auditEntity);

    PageOfAudit get(Integer page, Integer size);

}

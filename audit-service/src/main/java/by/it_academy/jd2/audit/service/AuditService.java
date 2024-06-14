package by.it_academy.jd2.audit.service;

import by.it_academy.jd2.audit.dao.api.IAuditDao;
import by.it_academy.jd2.audit.dao.api.IUserDao;
import by.it_academy.jd2.audit.dao.entity.AuditEntity;
import by.it_academy.jd2.audit.dao.entity.UserEntity;
import by.it_academy.jd2.audit.service.api.IAuditService;
import by.it_academy.jd2.audit.service.api.dto.AuditDTO;
import by.it_academy.jd2.audit.service.api.dto.EssenceType;
import by.it_academy.jd2.audit.service.api.dto.PageOfAudit;
import by.it_academy.jd2.audit.service.api.dto.UserDTO;
import by.it_academy.jd2.audit.service.converter.audit.ConverterDTOToAuditEntity;
import by.it_academy.jd2.audit.service.converter.audit.ConverterEntityToAuditDTO;
import by.it_academy.jd2.audit.service.converter.user.ConverterDTOToUserEntity;
import by.it_academy.jd2.audit.service.converter.user.ConverterEntityToUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AuditService implements IAuditService {

    private final IAuditDao auditDao;
    private final IUserDao userDao;
    private final ConverterDTOToUserEntity userDtoToUserEntity;
    private final ConverterEntityToUserDTO entityUserToUserDto;

    private final ConverterEntityToAuditDTO entityAuditToDtoAudit;
    private final ConverterDTOToAuditEntity dtoAuditToEntityAudit;

    public AuditService(IAuditDao auditDao, IUserDao userDao,
                        ConverterDTOToUserEntity userDtoToUserEntity, ConverterEntityToUserDTO entityUserToUserDto,
                        ConverterEntityToAuditDTO EntityAuditToDtoAudit, ConverterDTOToAuditEntity dtoAuditToEntityAudit) {
        this.auditDao = auditDao;
        this.userDao = userDao;
        this.userDtoToUserEntity = userDtoToUserEntity;
        this.entityUserToUserDto = entityUserToUserDto;
        this.entityAuditToDtoAudit = EntityAuditToDtoAudit;
        this.dtoAuditToEntityAudit = dtoAuditToEntityAudit;
    }

    @Transactional
    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        return userDao.saveAndFlush(userEntity);
    }

    @Transactional
    @Override
    public AuditEntity saveAudit(AuditEntity auditEntity) {
        auditEntity.setUuid(UUID.randomUUID());

        return auditDao.save(auditEntity);
    }

    @Override
    public Optional<AuditEntity> getByUuid(UUID uuid) {
        Optional<AuditEntity> auditEntity = auditDao.findById(uuid);
        if(auditEntity.isEmpty()){
            throw new IllegalArgumentException("Действия по этому uuid не найдены");
        }
        return auditEntity;
    }

    @Override
    public PageOfAudit get(Integer page, Integer size) {
        Page<AuditEntity> category = auditDao.findAll(PageRequest.of(page, size));
        return getPageAudit(category);
    }


    private PageOfAudit getPageAudit(Page<AuditEntity> objects){
        PageOfAudit pageOfAudit = new PageOfAudit();
        pageOfAudit.setNumber(objects.getNumber());
        pageOfAudit.setSize(objects.getSize());
        pageOfAudit.setTotal_pages(objects.getTotalPages());
        pageOfAudit.setNumber_of_elements(objects.getNumberOfElements());
        pageOfAudit.setFirst(objects.isFirst());
        pageOfAudit.setTotal_elements(objects.getTotalElements());
        pageOfAudit.setLast(objects.isLast());

        List<AuditDTO> auditDTOList = objects
                .getContent()
                .stream()
                .map(entityAuditToDtoAudit::convert)
                .collect(Collectors.toList());

        pageOfAudit.setContent(auditDTOList);
        return pageOfAudit;
    }
}

package by.it_academy.jd2.account.service;

import by.it_academy.jd2.account.dao.api.IOperationIsAccountDao;
import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.dao.entity.OperationEntity;
import by.it_academy.jd2.account.service.api.IAccountService;
import by.it_academy.jd2.account.service.api.IOperationService;
import by.it_academy.jd2.account.service.api.dto.OperationDTO;
import by.it_academy.jd2.account.service.api.dto.PageOfOperations;
import by.it_academy.jd2.account.service.api.enums.EnumErrors;
import by.it_academy.jd2.account.service.converter.operation.ConverterDTOToEntityOperation;
import by.it_academy.jd2.account.service.converter.operation.ConverterEntityToDTOOperation;
import by.it_academy.jd2.account.exception.InternalServerExceptionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OperationService implements IOperationService {

    private final IOperationIsAccountDao operationDao;
    private final IAccountService accountService;
    private final ConverterEntityToDTOOperation convertEntity;
    private final ConverterDTOToEntityOperation converterDto;


    public OperationService(IOperationIsAccountDao operationDao,
                            IAccountService accountService,
                            ConverterEntityToDTOOperation convertEntity,
                            ConverterDTOToEntityOperation converterDto) {
        this.operationDao = operationDao;
        this.accountService = accountService;
        this.convertEntity = convertEntity;
        this.converterDto = converterDto;
    }

    @Transactional
    @Override
    public void create(UUID accountId, OperationDTO operationDTO) {
        Optional<AccountEntity> optional = accountService.get(accountId);
        if (optional.isPresent()) {
            OperationEntity entity = convertEntity.convert(operationDTO);
            entity.setUuidOperation(UUID.randomUUID());
            entity.setAccountEntity(optional.get());
            operationDao.saveAndFlush(entity);
        }
    }

    @Override
    public PageOfOperations get(UUID accountId, Integer page, Integer size) {
        Page<OperationEntity> operations = operationDao
                .findAllByAccountEntityUuidAccount(accountId, PageRequest.of(page, size));
        return getPageOperations(operations);
    }

    @Transactional
    @Override
    public void update(UUID accountId, UUID operationId, Long dtUpdate, OperationDTO operationDTO) {

        Optional<OperationEntity> optional = operationDao.findByUuidOperation(operationId);
        if (optional.isPresent()) {
            OperationEntity entity = optional.get();

            LocalDateTime updatedDateTime = Instant
                    .ofEpochMilli(dtUpdate)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            entity.setDt_update(updatedDateTime);
            updateProperties(operationDTO, entity);
            operationDao.save(entity);
        }
    }

    @Transactional
    @Override
    public void delete(UUID accountId, UUID operationId) {
        operationDao.deleteByUuidOperation(operationId);
    }

    private PageOfOperations getPageOperations(Page<OperationEntity> operations) {
        PageOfOperations pageOfOperations = new PageOfOperations();
        pageOfOperations.setNumber(operations.getNumber());
        pageOfOperations.setSize(operations.getSize());
        pageOfOperations.setTotal_pages(operations.getTotalPages());
        pageOfOperations.setNumber_of_elements(operations.getNumberOfElements());
        pageOfOperations.setFirst(operations.isFirst());
        pageOfOperations.setTotal_elements(operations.getTotalElements());
        pageOfOperations.setLast(operations.isLast());

        List<OperationDTO> operationDTOList = operations
                .getContent()
                .stream()
                .map(converterDto::convert)
                .collect(Collectors.toList());

        pageOfOperations.setContent(operationDTOList);
        return pageOfOperations;
    }

    private void updateProperties(OperationDTO operationDTO, OperationEntity entity) {
        entity.setDate(operationDTO.getDate());
        entity.setDescription(operationDTO.getDescription());
        entity.setCategory(operationDTO.getCategory());
        entity.setValue(operationDTO.getValue());
        entity.setCurrency(operationDTO.getCurrency());
    }
}

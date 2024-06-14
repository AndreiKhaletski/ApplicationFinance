package by.it_academy.jd2.account.service;

import by.it_academy.jd2.account.dao.api.IAccountDao;
import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.exception.InternalServerExceptionDto;
import by.it_academy.jd2.account.service.api.IAccountService;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import by.it_academy.jd2.account.service.api.dto.CreateAccountDto;
import by.it_academy.jd2.account.service.api.dto.PageOfCurrent;
import by.it_academy.jd2.account.service.api.enums.EnumErrors;
import by.it_academy.jd2.account.service.converter.account.ConverterDTOToEntityAccount;
import by.it_academy.jd2.account.service.converter.account.ConverterEntityToDTOAccount;
import org.springframework.data.crossstore.ChangeSetPersister;
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
public class AccountService implements IAccountService {

    private final IAccountDao accountDao;
    private final ConverterEntityToDTOAccount convertEntity;
    private final ConverterDTOToEntityAccount convertDto;

    public AccountService(IAccountDao accountDao,
                          ConverterEntityToDTOAccount convertEntity,
                          ConverterDTOToEntityAccount convertDto) {
        this.accountDao = accountDao;
        this.convertEntity = convertEntity;
        this.convertDto = convertDto;
    }

    @Transactional
    @Override
    public void create(CreateAccountDto accountDTO) {
        AccountEntity entity = convertEntity.convert(accountDTO);
        entity.setUuidAccount(UUID.randomUUID());
        accountDao.saveAndFlush(entity);
    }

    @Override
    public PageOfCurrent get(Integer page, Integer size) {
        Page<AccountEntity> accounts = accountDao.findAll(PageRequest.of(page, size));
        return getPageCurrency(accounts);
    }

    @Override
    public Optional<AccountEntity> get(UUID uuid) {
        Optional<AccountEntity> entity = accountDao.findById(uuid);
        return entity;
    }


    @Transactional
    @Override
    public void update(UUID uuid, Long dtUpdate, AccountDTO accountDTO) {
        Optional<AccountEntity> optional = accountDao.findById(uuid);
        if (optional.isPresent()) {
            AccountEntity entity = optional.get();
            updateEntity(entity, accountDTO);
            LocalDateTime updateDataTime = Instant.ofEpochMilli(dtUpdate).atZone(ZoneId.systemDefault()).toLocalDateTime();
            entity.setDtUpdate(updateDataTime);
            accountDao.saveAndFlush(entity);
        }
    }

    private PageOfCurrent getPageCurrency(Page<AccountEntity> accounts) {
        PageOfCurrent pageOfCurrent = new PageOfCurrent();
        pageOfCurrent.setNumber(accounts.getNumber());
        pageOfCurrent.setSize(accounts.getSize());
        pageOfCurrent.setTotal_pages(accounts.getTotalPages());
        pageOfCurrent.setNumber_of_elements(accounts.getNumberOfElements());
        pageOfCurrent.setFirst(accounts.isFirst());
        pageOfCurrent.setTotal_elements(accounts.getTotalElements());
        pageOfCurrent.setLast(accounts.isLast());

        List<AccountDTO> accountDTOList = accounts
                .getContent()
                .stream()
                .map(convertDto::convert)
                .collect(Collectors.toList());

        pageOfCurrent.setContent(accountDTOList);
        return pageOfCurrent;
    }

    private void updateEntity(AccountEntity entity, AccountDTO accountDTO) {
        entity.setTitle(accountDTO.getTitle());
        entity.setDescription(accountDTO.getDescription());
        entity.setType(accountDTO.getType());
        entity.setCurrency(accountDTO.getCurrency());
    }
}

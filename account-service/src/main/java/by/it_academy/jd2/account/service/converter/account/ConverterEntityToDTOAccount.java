package by.it_academy.jd2.account.service.converter.account;

import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import by.it_academy.jd2.account.service.api.dto.CreateAccountDto;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDTOAccount {
    public AccountEntity convert(AccountDTO accountDTO) {
        AccountEntity entity = new AccountEntity();
        entity.setUuidAccount(accountDTO.getUuid());
//        entity.setDtCreate(accountDTO.getDt_create());
//        entity.setDtUpdate(accountDTO.getDt_update());
        entity.setTitle(accountDTO.getTitle());
        entity.setDescription(accountDTO.getDescription());
        entity.setBalance(accountDTO.getBalance());
        entity.setType(accountDTO.getType());
        entity.setCurrency(accountDTO.getCurrency());
        return entity;
    }

    public AccountEntity convert(CreateAccountDto accountDTO) {
        AccountEntity entity = new AccountEntity();
        entity.setUuidAccount(accountDTO.getUuid());
        entity.setTitle(accountDTO.getTitle());
        entity.setDescription(accountDTO.getDescription());
        entity.setType(accountDTO.getType());
        entity.setCurrency(accountDTO.getCurrency());
        return entity;
    }
}

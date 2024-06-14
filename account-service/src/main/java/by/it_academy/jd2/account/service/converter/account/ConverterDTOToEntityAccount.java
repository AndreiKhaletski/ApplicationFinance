package by.it_academy.jd2.account.service.converter.account;

import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterDTOToEntityAccount {
    public AccountDTO convert (AccountEntity entity){
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUuid(entity.getUuidAccount());
        accountDTO.setDt_create(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        accountDTO.setDt_update(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        accountDTO.setTitle(entity.getTitle());
        accountDTO.setDescription(entity.getDescription());
        accountDTO.setBalance(entity.getBalance());
        accountDTO.setType(entity.getType());
        accountDTO.setCurrency(entity.getCurrency());
        return accountDTO;
    }
}

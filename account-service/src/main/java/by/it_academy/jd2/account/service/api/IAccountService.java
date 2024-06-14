package by.it_academy.jd2.account.service.api;

import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import by.it_academy.jd2.account.service.api.dto.CreateAccountDto;
import by.it_academy.jd2.account.service.api.dto.PageOfCurrent;

import java.util.Optional;
import java.util.UUID;

public interface IAccountService {

    void create(CreateAccountDto accountDTO);

    PageOfCurrent get(Integer page, Integer size);

    Optional<AccountEntity> get(UUID uuid);

    void update(UUID uuid, Long dtUpdate, AccountDTO accountDTO);

}

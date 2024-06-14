package by.it_academy.jd2.account.service.api;

import by.it_academy.jd2.account.service.api.dto.OperationDTO;
import by.it_academy.jd2.account.service.api.dto.PageOfOperations;

import java.util.UUID;

public interface IOperationService {

    void create(UUID accountId, OperationDTO operationDTO);

    PageOfOperations get(UUID accountId, Integer page, Integer size);

    void update(UUID accountId, UUID operationId, Long dtUpdate, OperationDTO operationDTO);

    void delete(UUID accountId, UUID operationId);
}

package by.it_academy.jd2.account.service.converter.operation;

import by.it_academy.jd2.account.dao.entity.OperationEntity;
import by.it_academy.jd2.account.service.api.dto.OperationDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterDTOToEntityOperation {
    public OperationDTO convert (OperationEntity entity){
        OperationDTO operationDTO = new OperationDTO();
        operationDTO.setUuid(entity.getUuidOperation());
        operationDTO.setDt_create(entity.getDt_create().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        operationDTO.setDt_update(entity.getDt_update().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        operationDTO.setDate(entity.getDate());
        operationDTO.setDescription(entity.getDescription());
        operationDTO.setCategory(entity.getCategory());
        operationDTO.setValue(entity.getValue());
        operationDTO.setCurrency(entity.getCurrency());
        return operationDTO;
    }
}

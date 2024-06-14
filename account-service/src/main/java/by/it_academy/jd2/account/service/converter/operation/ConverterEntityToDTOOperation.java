package by.it_academy.jd2.account.service.converter.operation;

import by.it_academy.jd2.account.dao.entity.OperationEntity;
import by.it_academy.jd2.account.service.api.dto.OperationDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDTOOperation {
    public OperationEntity convert (OperationDTO operationDTO){
        OperationEntity entity = new OperationEntity();
//        entity.setDt_create(operationDTO.getDt_create());
//        entity.setDt_update(operationDTO.getDt_update());
        entity.setDate(operationDTO.getDate());
        entity.setDescription(operationDTO.getDescription());
        entity.setCategory(operationDTO.getCategory());
        entity.setValue(operationDTO.getValue());
        entity.setCurrency(operationDTO.getCurrency());
        return entity;
    }
}

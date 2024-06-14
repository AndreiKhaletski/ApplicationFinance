package by.it_academy.jd2.audit.service.converter.audit;

import by.it_academy.jd2.audit.dao.entity.AuditEntity;
import by.it_academy.jd2.audit.service.api.dto.AuditDTO;
import by.it_academy.jd2.audit.service.converter.user.ConverterDTOToUserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ConverterDTOToAuditEntity {

    private final ConverterDTOToUserEntity convertToEntity;

    public ConverterDTOToAuditEntity(ConverterDTOToUserEntity convertToEntity) {
        this.convertToEntity = convertToEntity;
    }

    public AuditEntity convert(AuditDTO item){
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setUserEntity(convertToEntity.convert(item.getUserDto()));
        auditEntity.setText(item.getText());
        auditEntity.setType(item.getType());
        auditEntity.setId(item.getId());
        return auditEntity;
    }
}

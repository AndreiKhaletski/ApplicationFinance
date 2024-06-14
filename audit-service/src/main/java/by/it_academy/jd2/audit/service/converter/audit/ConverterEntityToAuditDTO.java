package by.it_academy.jd2.audit.service.converter.audit;

import by.it_academy.jd2.audit.dao.entity.AuditEntity;
import by.it_academy.jd2.audit.service.api.dto.AuditDTO;
import by.it_academy.jd2.audit.service.converter.user.ConverterEntityToUserDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterEntityToAuditDTO {

    private final ConverterEntityToUserDTO convertToEntity;

    public ConverterEntityToAuditDTO(ConverterEntityToUserDTO convertToEntity) {
        this.convertToEntity = convertToEntity;
    }

    public AuditDTO convert(AuditEntity item){
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setUuid(String.valueOf(item.getUuid()));
        auditDTO.setUserDto(convertToEntity.convert(item.getUserEntity()));
        auditDTO.setDt_create(item.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        auditDTO.setText(item.getText());
        auditDTO.setType(item.getType());
        auditDTO.setId(item.getId());
        return auditDTO;
    }
}

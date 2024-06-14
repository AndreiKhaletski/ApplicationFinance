package by.it_academy.jd2.classifier.service.converter.category;

import by.it_academy.jd2.classifier.dao.entity.CategoryEntity;
import by.it_academy.jd2.classifier.service.api.dto.category.CategoryDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterDTOToEntityCategory {
    public CategoryDTO convert (CategoryEntity entity){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setUuid(entity.getUuid());
        categoryDTO.setDt_create(entity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        categoryDTO.setDt_update(entity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        categoryDTO.setTitle(entity.getTitle());
        return categoryDTO;
    }
}

package by.it_academy.jd2.classifier.service.converter.category;

import by.it_academy.jd2.classifier.dao.entity.CategoryEntity;
import by.it_academy.jd2.classifier.service.api.dto.category.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDTOCategory {
    public CategoryEntity convert (CategoryDTO categoryDTO){
        CategoryEntity entity = new CategoryEntity();
        entity.setUuid(categoryDTO.getUuid());
//        entity.setDtCreate(categoryDTO.getDtCreate());
//        entity.setDtUpdate(categoryDTO.getDtUpdate());
        entity.setTitle(categoryDTO.getTitle());
        return entity;
    }
}

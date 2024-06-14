package by.it_academy.jd2.classifier.service.api;

import by.it_academy.jd2.classifier.service.api.dto.category.CategoryDTO;
import by.it_academy.jd2.classifier.service.api.dto.category.PageOfOperationCategory;

public interface ICategoryService {

    void create(CategoryDTO categoryDTO);
    PageOfOperationCategory get(Integer page, Integer size);
}

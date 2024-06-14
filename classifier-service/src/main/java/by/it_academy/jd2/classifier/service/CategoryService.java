package by.it_academy.jd2.classifier.service;

import by.it_academy.jd2.classifier.dao.api.ICategoryDao;
import by.it_academy.jd2.classifier.dao.entity.CategoryEntity;
import by.it_academy.jd2.classifier.service.api.ICategoryService;
import by.it_academy.jd2.classifier.service.api.dto.category.CategoryDTO;
import by.it_academy.jd2.classifier.service.api.dto.category.PageOfOperationCategory;
import by.it_academy.jd2.classifier.service.converter.category.ConverterDTOToEntityCategory;
import by.it_academy.jd2.classifier.service.converter.category.ConverterEntityToDTOCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;
    private final ConverterEntityToDTOCategory entityConvert;
    private final ConverterDTOToEntityCategory dtoConvert;

    public CategoryService(ICategoryDao categoryDao,
                           ConverterEntityToDTOCategory entityConvert,
                           ConverterDTOToEntityCategory dtoConvert) {
        this.categoryDao = categoryDao;
        this.entityConvert = entityConvert;
        this.dtoConvert = dtoConvert;
    }

    @Transactional
    @Override
    public void create(CategoryDTO categoryDTO) {
        CategoryEntity entity = entityConvert.convert(categoryDTO);
        entity.setUuid(UUID.randomUUID());
        categoryDao.saveAndFlush(entity);
    }

    @Override
    public PageOfOperationCategory get(Integer page, Integer size) {
        Page<CategoryEntity> category = categoryDao.findAll(PageRequest.of(page, size));
        return getPageCategory(category);
    }

    private PageOfOperationCategory getPageCategory(Page<CategoryEntity> objects){
        PageOfOperationCategory pageCategory = new PageOfOperationCategory();
        pageCategory.setNumber(objects.getNumber());
        pageCategory.setSize(objects.getSize());
        pageCategory.setTotal_pages(objects.getTotalPages());
        pageCategory.setNumber_of_elements(objects.getNumberOfElements());
        pageCategory.setFirst(objects.isFirst());
        pageCategory.setTotal_elements(objects.getTotalElements());
        pageCategory.setLast(objects.isLast());

        List<CategoryDTO> categoryDTOList = objects
                .getContent()
                .stream()
                .map(dtoConvert::convert)
                .collect(Collectors.toList());


        pageCategory.setContent(categoryDTOList);
        return pageCategory;
    }
}

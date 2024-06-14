package by.it_academy.jd2.classifier.service;


import by.it_academy.jd2.classifier.dao.api.ICurrencyDao;
import by.it_academy.jd2.classifier.dao.entity.CurrencyEntity;
import by.it_academy.jd2.classifier.service.api.ICurrencyService;
import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import by.it_academy.jd2.classifier.service.api.dto.currency.PageOfCurrency;
import by.it_academy.jd2.classifier.service.converter.currency.ConverterDTOToEntityCurrency;
import by.it_academy.jd2.classifier.service.converter.currency.ConverterEntityToDTOCurrency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
public class CurrencyService implements ICurrencyService {

    private final ConverterDTOToEntityCurrency dtoConverter;
    private final ConverterEntityToDTOCurrency entityConverter;
    private final ICurrencyDao currenciesDao;

    public CurrencyService(ConverterDTOToEntityCurrency dtoConverter,
                           ConverterEntityToDTOCurrency entityConverter,
                           ICurrencyDao currenciesDao) {
        this.dtoConverter = dtoConverter;
        this.entityConverter = entityConverter;
        this.currenciesDao = currenciesDao;
    }

    @Transactional
    @Override
    public void create(CurrencyDTO currencyDTO) {
        CurrencyEntity entity = entityConverter.convert(currencyDTO);
        entity.setUuid(UUID.randomUUID());
        currenciesDao.saveAndFlush(entity);
    }

    @Override
    public PageOfCurrency get(Integer page, Integer size) {
        Page<CurrencyEntity> currency = currenciesDao.findAll(PageRequest.of(page, size));
        return getPageCurrency(currency);
    }

    private PageOfCurrency getPageCurrency(Page<CurrencyEntity> objects) {
        PageOfCurrency pageCurrency = new PageOfCurrency();
        pageCurrency.setNumber(objects.getNumber());
        pageCurrency.setSize(objects.getSize());
        pageCurrency.setTotal_pages(objects.getTotalPages());
        pageCurrency.setNumber_of_elements(objects.getNumberOfElements());
        pageCurrency.setFirst(objects.isFirst());
        pageCurrency.setTotal_elements(objects.getTotalElements());
        pageCurrency.setLast(objects.isLast());

        List<CurrencyDTO> currencyDTOList = objects
                .getContent()
                .stream()
                .map(dtoConverter::convert)
                .collect(Collectors.toList());

        pageCurrency.setContent(currencyDTOList);
        return pageCurrency;
    }
}

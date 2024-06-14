package by.it_academy.jd2.classifier.service.converter.currency;

import by.it_academy.jd2.classifier.dao.entity.CurrencyEntity;
import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToDTOCurrency {
    public CurrencyEntity convert (CurrencyDTO currencyDTO){
        CurrencyEntity currencyEntity = new CurrencyEntity();
        currencyEntity.setUuid(currencyDTO.getUuid());
//        currencyEntity.setDtCreate(currencyDTO.getDtCreate());
//        currencyEntity.setDtUpdate(currencyDTO.getDtUpdate());
        currencyEntity.setTitle(currencyDTO.getTitle());
        currencyEntity.setDescription(currencyDTO.getDescription());
        return currencyEntity;
    }
}

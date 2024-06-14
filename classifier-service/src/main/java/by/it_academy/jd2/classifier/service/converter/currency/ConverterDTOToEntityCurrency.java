package by.it_academy.jd2.classifier.service.converter.currency;

import by.it_academy.jd2.classifier.dao.entity.CurrencyEntity;
import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterDTOToEntityCurrency {
    public CurrencyDTO convert (CurrencyEntity currencyEntity){
        CurrencyDTO currencyDTO = new CurrencyDTO();
        currencyDTO.setUuid(currencyEntity.getUuid());
        currencyDTO.setDt_create(currencyEntity.getDtCreate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        currencyDTO.setDt_update(currencyEntity.getDtUpdate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        currencyDTO.setTitle(currencyEntity.getTitle());
        currencyDTO.setDescription(currencyEntity.getDescription());
        return currencyDTO;
    }
}

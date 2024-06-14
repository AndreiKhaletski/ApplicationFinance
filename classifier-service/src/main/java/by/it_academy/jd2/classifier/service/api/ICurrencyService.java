package by.it_academy.jd2.classifier.service.api;


import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import by.it_academy.jd2.classifier.service.api.dto.currency.PageOfCurrency;

public interface ICurrencyService {

    void create(CurrencyDTO currencyDTO);
    PageOfCurrency get(Integer page, Integer size);

}

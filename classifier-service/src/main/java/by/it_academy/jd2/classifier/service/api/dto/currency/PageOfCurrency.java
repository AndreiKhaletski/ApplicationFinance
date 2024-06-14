package by.it_academy.jd2.classifier.service.api.dto.currency;

import by.it_academy.jd2.classifier.dao.entity.CurrencyEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageOfCurrency {

    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<CurrencyDTO> content;
}

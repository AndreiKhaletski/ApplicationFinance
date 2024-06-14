package by.it_academy.jd2.classifier.service.api.dto.category;

import by.it_academy.jd2.classifier.dao.entity.CategoryEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageOfOperationCategory {

    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<CategoryDTO> content;
}

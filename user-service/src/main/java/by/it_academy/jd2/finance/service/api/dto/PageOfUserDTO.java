package by.it_academy.jd2.finance.service.api.dto;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageOfUserDTO {

    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<AboutUserDTO> content;
}

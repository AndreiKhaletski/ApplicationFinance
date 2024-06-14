package by.it_academy.jd2.audit.service.api.dto;


import by.it_academy.jd2.audit.dao.entity.AuditEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageOfAudit {
    private Integer number;
    private Integer size;
    private Integer total_pages;
    private Long total_elements;
    private boolean first;
    private Integer number_of_elements;
    private boolean last;
    private List<AuditDTO> content;
}

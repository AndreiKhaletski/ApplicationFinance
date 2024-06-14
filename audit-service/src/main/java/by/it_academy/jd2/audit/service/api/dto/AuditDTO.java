package by.it_academy.jd2.audit.service.api.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String uuid;
    private Long dt_create;
    private UserDTO userDto;
    private String text;
    private EssenceType type;
    private String id;
}

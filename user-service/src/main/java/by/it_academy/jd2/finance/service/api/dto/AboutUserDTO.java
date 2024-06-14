package by.it_academy.jd2.finance.service.api.dto;

import by.it_academy.jd2.finance.service.api.enums.EnumRole;
import by.it_academy.jd2.finance.service.api.enums.EnumStatusRegistration;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AboutUserDTO {
    private UUID uuid;
    private Long dt_create;
    private Long dt_update;
    private String mail;
    private String fio;
    private EnumRole role;
    private EnumStatusRegistration status;
}

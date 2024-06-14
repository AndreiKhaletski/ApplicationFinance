package by.it_academy.jd2.audit.service.api.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID uuid;
    private String mail;
    private String fio;
    private EnumRole role;
}

package by.it_academy.jd2.finance.service.api.dto;


import by.it_academy.jd2.finance.service.api.enums.EnumRole;
import by.it_academy.jd2.finance.service.api.enums.EnumStatusRegistration;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID uuid;
    private Long dt_create;
    private Long dt_update;
    @NotBlank(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String mail;
    private String fio;
    private EnumRole role;
    private EnumStatusRegistration status;
    @NotBlank(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String password;
}

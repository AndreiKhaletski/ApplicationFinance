package by.it_academy.jd2.finance.service.api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizationDTO {
    @NotBlank(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String mail;
    @NotBlank(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String password;
}

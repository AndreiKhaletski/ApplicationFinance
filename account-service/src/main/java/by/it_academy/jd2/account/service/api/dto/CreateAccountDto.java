package by.it_academy.jd2.account.service.api.dto;

import by.it_academy.jd2.account.service.api.enums.EnumCurrency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountDto {
    private UUID uuid;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String title;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String description;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private EnumCurrency type;
    private UUID currency;
}

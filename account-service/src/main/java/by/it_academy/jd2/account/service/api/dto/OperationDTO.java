package by.it_academy.jd2.account.service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperationDTO {

    private UUID uuid;
    private Long dt_create;
    private Long dt_update;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private Instant date;
    @NotBlank(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private String description;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private UUID category;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private Integer value;
    @NotNull(message = "Запрос содержит некорректные данные. Измените запрос и отправьте его ещё раз")
    private UUID currency;
}

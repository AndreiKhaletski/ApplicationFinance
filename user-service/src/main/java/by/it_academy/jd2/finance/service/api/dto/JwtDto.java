package by.it_academy.jd2.finance.service.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class JwtDto {

    @NotBlank(message = "Значение токена не должно быть пустым")
    private final String token;
}

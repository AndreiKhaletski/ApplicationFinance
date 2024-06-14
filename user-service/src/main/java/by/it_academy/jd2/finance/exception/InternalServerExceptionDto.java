package by.it_academy.jd2.finance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InternalServerExceptionDto {
    private final String logref;
    private final String message;
}

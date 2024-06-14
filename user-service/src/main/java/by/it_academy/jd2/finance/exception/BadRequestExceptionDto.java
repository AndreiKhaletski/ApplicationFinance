package by.it_academy.jd2.finance.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BadRequestExceptionDto {

    private final String logref;
    private final List<FieldExceptionDescription> errors;

    @Getter
    @AllArgsConstructor
    public static class FieldExceptionDescription {
        private final String field;
        private final String message;
    }
}

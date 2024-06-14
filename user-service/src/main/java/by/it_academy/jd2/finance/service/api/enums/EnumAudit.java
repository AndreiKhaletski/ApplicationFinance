package by.it_academy.jd2.finance.service.api.enums;

public enum EnumAudit {
    REGISTRATION_COMPLETE("Пользователь успешно зарегистривовался"),
    UNAUTHGORISED("Пользователь успешно зарегисривовался"),
    USER_READ_ID("Пользователь просмотрел другого пользователя по id"),
    USER_READ_PAGES("Пользователь просмотреть список пользователей"),
    SUCCESS_VERIFICATION("Пользователь успешно прошел верификацию");

    private final String message;

    EnumAudit(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

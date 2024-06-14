package by.it_academy.jd2.account.service.api.dto.audit;

public class AuditActionText {

    public static final String CREATE_ACCOUNT = "Пользователь создал счёт";
    public static final String PAGE_ACCOUNT = "Пользователь просмотрел список счетов";
    public static final String UPDATE_ACCOUNT = "Пользователь обновил счёт";
    public static final String CREATE_OPERATION = "Пользователь создал новую операцию";
    public static final String PAGE_OPERATION = "Пользователь просмотрел список операций";
    public static final String UPDATE_OPERATION = "Пользователь обновил операцию";
    public static final String DELETE_OPERATION = "Пользователь обновил операцию";

    private AuditActionText() {
    }
}

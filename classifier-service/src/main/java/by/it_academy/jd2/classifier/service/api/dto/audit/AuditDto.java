package by.it_academy.jd2.classifier.service.api.dto.audit;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDto {
    private UserDto userDto;
    private String text;
    private EssenceType type;
    private String id;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private String uuid;
        private String mail;
        private String fio;
        private String role;
    }

    public enum EssenceType {
        USER, REPORT
    }
}

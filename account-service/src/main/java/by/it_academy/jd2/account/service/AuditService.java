package by.it_academy.jd2.account.service;


import by.it_academy.jd2.account.config.properites.ServiceHostProperty;
import by.it_academy.jd2.account.service.api.dto.audit.AuditDto;
import by.it_academy.jd2.account.service.detailesservice.CustomUserDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;


import static by.it_academy.jd2.account.config.JsonMapper.JSON_MAPPER;
import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
@Component
public class AuditService {

    private static final String AUDIT_SERVICE_URL = "/audit";

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.of(10, ChronoUnit.SECONDS))
            .build();

    private final UserHolder userHolder;
    private final ServiceHostProperty serviceHostProperty;

    public AuditService(UserHolder userHolder,
                        ServiceHostProperty serviceHostProperty) {
        this.userHolder = userHolder;
        this.serviceHostProperty = serviceHostProperty;
    }

    public void createAuditLog(String auditActionText, HttpServletRequest originalRequest) {
        AuditDto auditDto = mapToAuditDto(auditActionText);

        HttpRequest httpRequest = null;
        try {
            httpRequest = newBuilder()
                    .POST(BodyPublishers.ofString(JSON_MAPPER.writeValueAsString(auditDto)))
                    .uri(URI.create(serviceHostProperty.getAudit() + AUDIT_SERVICE_URL))
                    .header(AUTHORIZATION, originalRequest.getHeader(AUTHORIZATION))
                    .headers(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        } catch (JsonProcessingException e) {
            log.error("Не удалось спарсить dto", e);
            return;
        }

        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenAccept(it -> log.info("code {}", it.statusCode()));
      }

    private AuditDto mapToAuditDto(String auditActionText) {
        CustomUserDetails userDetails = userHolder.getUser();

        return AuditDto.builder()
                .text(auditActionText)
                .type(AuditDto.EssenceType.REPORT)
                .userDto(AuditDto.UserDto.builder()
                        .uuid(userDetails.getUuid())
                        .fio(userDetails.getFio())
                        .role(userDetails.getRole())
                        .mail(userDetails.getUsername())
                        .build())
                .build();
    }
}

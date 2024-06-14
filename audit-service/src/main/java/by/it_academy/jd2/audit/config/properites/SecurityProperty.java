package by.it_academy.jd2.audit.config.properites;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service.host")
public class SecurityProperty {

    @Valid
    @NotBlank
    private String security;
}

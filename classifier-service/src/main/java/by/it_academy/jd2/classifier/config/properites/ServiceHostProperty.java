package by.it_academy.jd2.classifier.config.properites;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "service.host")
public class ServiceHostProperty {

    @Valid
    @NotBlank
    private String security;

    @Valid
    @NotBlank
    private String audit;
}

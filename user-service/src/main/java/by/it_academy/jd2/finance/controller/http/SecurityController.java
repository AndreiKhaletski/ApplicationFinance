package by.it_academy.jd2.finance.controller.http;

import by.it_academy.jd2.finance.service.UserHolder;
import by.it_academy.jd2.finance.service.detailesservice.CustomUserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/security")
public class SecurityController {

    @GetMapping(value = "/token/verification")
    public Map<String, String> getUserDetails() {
        CustomUserDetails user = (CustomUserDetails) UserHolder.getUser();
        return Map.of(
                "uuid", user.getUserEntity().getUuid().toString(),
                "username", user.getUsername(),
                "fio", user.getUserEntity().getFio(),
                "role", user.getUserEntity().getRole().name()
        );
    }
}

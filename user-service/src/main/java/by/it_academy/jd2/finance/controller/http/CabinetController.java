package by.it_academy.jd2.finance.controller.http;

import by.it_academy.jd2.finance.service.api.ICabinetService;
import by.it_academy.jd2.finance.service.api.dto.AboutUserDTO;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.dto.AuthorizationDTO;
import by.it_academy.jd2.finance.service.converter.ConverterEntityToUserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/cabinet")
public class CabinetController {

    private final ICabinetService cabinetService;
    private final ConverterEntityToUserDTO dtoConvert;

    public CabinetController(ICabinetService cabinetService,
                             ConverterEntityToUserDTO dtoConvert) {
        this.cabinetService = cabinetService;
        this.dtoConvert = dtoConvert;
    }

    @PostMapping(value = "/registration")
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO) {
        cabinetService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/verification")
    public ResponseEntity<?> get(@RequestParam("code") String code,
                    @RequestParam("mail") String email) {
        cabinetService.verification(code, email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> authorization(@RequestBody AuthorizationDTO authorizationDTO) {

        String authorization = cabinetService.authorization(authorizationDTO);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .build();
    }

    @GetMapping(value = "/me")
    public ResponseEntity get() {
        AboutUserDTO aboutUserDTO = dtoConvert.convertAboutUser(cabinetService.getInfoMe());
        return ResponseEntity.status(HttpStatus.CREATED).body(aboutUserDTO);
    }
}

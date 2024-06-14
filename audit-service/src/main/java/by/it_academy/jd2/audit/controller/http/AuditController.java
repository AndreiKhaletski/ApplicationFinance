package by.it_academy.jd2.audit.controller.http;

import by.it_academy.jd2.audit.dao.entity.UserEntity;
import by.it_academy.jd2.audit.service.api.IAuditService;
import by.it_academy.jd2.audit.service.api.dto.AuditDTO;
import by.it_academy.jd2.audit.service.api.dto.UserDTO;
import by.it_academy.jd2.audit.service.converter.audit.ConverterDTOToAuditEntity;
import by.it_academy.jd2.audit.service.converter.user.ConverterDTOToUserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class AuditController {

    private final IAuditService auditService;
    private final ConverterDTOToAuditEntity convertToEntityAudit;
    private final ConverterDTOToUserEntity convertToEntityUser;

    public AuditController(IAuditService auditService,
                           ConverterDTOToAuditEntity convertToEntityAudit,
                           ConverterDTOToUserEntity convertToEntityUser) {
        this.auditService = auditService;
        this.convertToEntityAudit = convertToEntityAudit;
        this.convertToEntityUser = convertToEntityUser;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AuditDTO auditDTO) {
        UserDTO userDTO = auditDTO.getUserDto();
        userDTO.setRole(auditDTO.getUserDto().getRole());
        UserEntity userEntity = convertToEntityUser.convert(userDTO);


        auditService.saveUser(userEntity);
        auditService.saveAudit(convertToEntityAudit.convert(auditDTO));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<?> get(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return ResponseEntity.ok(auditService.get(page, size));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getByUuid(
            @PathVariable("uuid") UUID uuid) {
        auditService.getByUuid(uuid);
        return ResponseEntity.ok(auditService.getByUuid(uuid));
    }
}

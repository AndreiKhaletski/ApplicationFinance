package by.it_academy.jd2.account.controller.http;

import by.it_academy.jd2.account.service.AuditService;
import by.it_academy.jd2.account.service.api.IOperationService;
import by.it_academy.jd2.account.service.api.dto.OperationDTO;
import by.it_academy.jd2.account.service.api.dto.audit.AuditActionText;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;


@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final IOperationService operationService;
    private final AuditService auditService;

    public OperationController(IOperationService operationService,
                               AuditService auditService) {
        this.operationService = operationService;
        this.auditService = auditService;
    }


    @PostMapping
    public ResponseEntity<?> create(@PathVariable("uuid") UUID uuid,
                                    @RequestBody @Valid OperationDTO operationDTO,
                                    HttpServletRequest request) {
        operationService.create(uuid, operationDTO);
        auditService.createAuditLog(AuditActionText.CREATE_OPERATION, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> get(@PathVariable("uuid") UUID uuid,
                                 @RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "20") Integer size) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(operationService.get(uuid, page, size));
    }


    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("uuid_operation") UUID uuidOperation,
                                 @PathVariable("dt_update") Long dtUpdate,
                                 @RequestBody @Valid OperationDTO operationDTO, HttpServletRequest request) {

        operationService.update(uuid, uuidOperation, dtUpdate, operationDTO);
        auditService.createAuditLog(AuditActionText.UPDATE_OPERATION, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<?> delete(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("uuid_operation") UUID uuidOperation, HttpServletRequest request){

        operationService.delete(uuid, uuidOperation);
        auditService.createAuditLog(AuditActionText.DELETE_OPERATION, request);
        return ResponseEntity.ok().build();
    }
}

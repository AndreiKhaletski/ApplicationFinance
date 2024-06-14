package by.it_academy.jd2.account.controller.http;


import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.service.AuditService;
import by.it_academy.jd2.account.service.api.IAccountService;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import by.it_academy.jd2.account.service.api.dto.CreateAccountDto;
import by.it_academy.jd2.account.service.api.dto.audit.AuditActionText;
import by.it_academy.jd2.account.service.converter.account.ConverterDTOToEntityAccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;
    private final ConverterDTOToEntityAccount convertDto;
    private final AuditService auditService;

    public AccountController(IAccountService accountService,
                             ConverterDTOToEntityAccount convertDto,
                             AuditService auditService) {
        this.accountService = accountService;
        this.convertDto = convertDto;
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountDto accountDTO,
                                    HttpServletRequest request){
        accountService.create(accountDTO);
        auditService.createAuditLog(AuditActionText.CREATE_ACCOUNT, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(value = "page") Integer page,
                                 @RequestParam(value = "size") Integer size){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(accountService.get(page, size));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<?> get(@PathVariable("uuid") UUID uuid){
        Optional<AccountEntity> entity = accountService.get(uuid);
        if(entity.isPresent()){
            return ResponseEntity.ok(convertDto.convert(entity.get()));
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("dt_update") Long dtUpdate,
                                 @RequestBody @Valid AccountDTO accountDTO, HttpServletRequest request){
        accountService.update(uuid, dtUpdate, accountDTO);
        auditService.createAuditLog(AuditActionText.UPDATE_ACCOUNT, request);
        return ResponseEntity.ok().build();
    }
}

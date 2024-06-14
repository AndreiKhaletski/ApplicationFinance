package by.it_academy.jd2.account.controller.http;


import by.it_academy.jd2.account.dao.entity.AccountEntity;
import by.it_academy.jd2.account.service.api.IAccountService;
import by.it_academy.jd2.account.service.api.dto.AccountDTO;
import by.it_academy.jd2.account.service.api.dto.CreateAccountDto;
import by.it_academy.jd2.account.service.converter.account.ConverterDTOToEntityAccount;
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

    public AccountController(IAccountService accountService,
                             ConverterDTOToEntityAccount convertDto) {
        this.accountService = accountService;
        this.convertDto = convertDto;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CreateAccountDto accountDTO){
        accountService.create(accountDTO);
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
                                 @RequestBody @Valid AccountDTO accountDTO){
        accountService.update(uuid, dtUpdate, accountDTO);
        return ResponseEntity.ok().build();
    }
}

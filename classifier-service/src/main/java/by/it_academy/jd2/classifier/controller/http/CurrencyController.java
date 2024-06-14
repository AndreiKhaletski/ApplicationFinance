package by.it_academy.jd2.classifier.controller.http;

import by.it_academy.jd2.classifier.service.AuditService;
import by.it_academy.jd2.classifier.service.api.ICurrencyService;
import by.it_academy.jd2.classifier.service.api.dto.audit.AuditActionText;
import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import by.it_academy.jd2.classifier.service.api.dto.currency.PageOfCurrency;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CurrencyController.REQUEST_MAPPING)
public class CurrencyController {
    public static final String REQUEST_MAPPING = "/classifier/currency";

    private final ICurrencyService currencyService;
    private final AuditService auditService;

    public CurrencyController(ICurrencyService classifireService, AuditService auditService) {
        this.currencyService = classifireService;
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody @Valid CurrencyDTO currencyDTO,
                                     HttpServletRequest request){
        currencyService.create(currencyDTO);
        auditService.createAuditLog(AuditActionText.CREATE_CURRENCY, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageOfCurrency> get(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size){

        PageOfCurrency pageOfCurrency = currencyService.get(page, size);
        return ResponseEntity.ok(pageOfCurrency);
    }
}

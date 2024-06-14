package by.it_academy.jd2.classifier.controller.http;

import by.it_academy.jd2.classifier.service.api.ICurrencyService;
import by.it_academy.jd2.classifier.service.api.dto.currency.CurrencyDTO;
import by.it_academy.jd2.classifier.service.api.dto.currency.PageOfCurrency;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CurrencyController.REQUEST_MAPPING)
public class CurrencyController {
    public static final String REQUEST_MAPPING = "/classifier/currency";

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService classifireService) {
        this.currencyService = classifireService;
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody @Valid CurrencyDTO currencyDTO){
        currencyService.create(currencyDTO);
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

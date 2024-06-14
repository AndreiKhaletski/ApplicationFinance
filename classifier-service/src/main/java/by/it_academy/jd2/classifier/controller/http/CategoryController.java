package by.it_academy.jd2.classifier.controller.http;

import by.it_academy.jd2.classifier.service.AuditService;
import by.it_academy.jd2.classifier.service.api.ICategoryService;
import by.it_academy.jd2.classifier.service.api.dto.audit.AuditActionText;
import by.it_academy.jd2.classifier.service.api.dto.category.CategoryDTO;
import by.it_academy.jd2.classifier.service.api.dto.category.PageOfOperationCategory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.REQUEST_MAPPING)
public class CategoryController {
    public static final String REQUEST_MAPPING = "/classifier/operation/category";

    private final ICategoryService categoryService;
    private final AuditService auditService;

    public CategoryController(ICategoryService categoryService,
                              AuditService auditService) {
        this.categoryService = categoryService;
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDTO categoryDTO, HttpServletRequest request) {
        categoryService.create(categoryDTO);
        auditService.createAuditLog(AuditActionText.CREATE_CATEGORY, request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageOfOperationCategory> get(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        PageOfOperationCategory pageOfOperationCategory = categoryService.get(page, size);
        return ResponseEntity.ok(pageOfOperationCategory);
    }

}

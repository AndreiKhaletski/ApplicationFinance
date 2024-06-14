package by.it_academy.jd2.finance.controller.http;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.IUserService;
import by.it_academy.jd2.finance.service.api.dto.AboutUserDTO;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.dto.PageOfUserDTO;
import by.it_academy.jd2.finance.service.converter.ConverterEntityToUserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;
    private final ConverterEntityToUserDTO convertToDto;


    public UserController(IUserService userService,
                          ConverterEntityToUserDTO convertToDto) {
        this.userService = userService;
        this.convertToDto = convertToDto;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
        userService.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<PageOfUserDTO> get(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok(userService.findByMail(page, size));
    }

    @GetMapping(value = "/{uuid}")
    public ResponseEntity<AboutUserDTO> get(@PathVariable("uuid") UUID uuid){
        Optional<UserEntity> accountEntity = (userService.findByMail(uuid));
        if(accountEntity.isPresent()){
            return ResponseEntity.ok(convertToDto.convertAboutUser(accountEntity.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> put(@PathVariable("uuid") UUID uuid,
                                 @PathVariable("dt_update") Long dtUpdate,
                                 @RequestBody UserDTO userDTO){
        userService.update(uuid, dtUpdate, userDTO);
        return ResponseEntity.ok().build();
    }
}

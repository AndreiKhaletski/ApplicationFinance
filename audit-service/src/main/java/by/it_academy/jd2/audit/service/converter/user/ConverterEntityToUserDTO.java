package by.it_academy.jd2.audit.service.converter.user;

import by.it_academy.jd2.audit.dao.entity.UserEntity;
import by.it_academy.jd2.audit.service.api.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterEntityToUserDTO {

    public UserDTO convert (UserEntity item){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(item.getUuid());
        userDTO.setMail(item.getMail());
        userDTO.setFio(item.getFio());
        userDTO.setRole(item.getRole());
        return userDTO;
    }
}

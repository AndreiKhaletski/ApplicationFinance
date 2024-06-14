package by.it_academy.jd2.audit.service.converter.user;

import by.it_academy.jd2.audit.dao.entity.UserEntity;
import by.it_academy.jd2.audit.service.api.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterDTOToUserEntity {

    public UserEntity convert(UserDTO item) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(item.getUuid());
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(item.getRole());
        return userEntity;
    }
}

package by.it_academy.jd2.finance.service.converter;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class ConverterDTOToUserEntity {

    public UserEntity convert(UserDTO item){
        UserEntity userEntity = new UserEntity();
        userEntity.setUuid(item.getUuid());
        userEntity.setMail(item.getMail());
        userEntity.setFio(item.getFio());
        userEntity.setRole(item.getRole());
        userEntity.setStatus(item.getStatus());
        return userEntity;
    }
}

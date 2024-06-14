package by.it_academy.jd2.finance.service.converter;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.dto.AboutUserDTO;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ConverterEntityToUserDTO {

    public UserDTO convertUser(UserEntity item){
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(item.getUuid());
        userDTO.setDt_create(item.getDt_create().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        userDTO.setDt_update(item.getDt_update().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        userDTO.setMail(item.getMail());
        userDTO.setFio(item.getFio());
        userDTO.setRole(item.getRole());
        userDTO.setStatus(item.getStatus());
        return userDTO;
    }

    public AboutUserDTO convertAboutUser(UserEntity item){
        AboutUserDTO userAboutDTO = new AboutUserDTO();
        userAboutDTO.setUuid(item.getUuid());
        userAboutDTO.setDt_create(item.getDt_create().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        userAboutDTO.setDt_update(item.getDt_update().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
        userAboutDTO.setMail(item.getMail());
        userAboutDTO.setFio(item.getFio());
        userAboutDTO.setRole(item.getRole());
        userAboutDTO.setStatus(item.getStatus());
        return userAboutDTO;
    }


}

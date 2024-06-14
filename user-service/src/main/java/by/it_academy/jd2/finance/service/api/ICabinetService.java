package by.it_academy.jd2.finance.service.api;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.dto.AuthorizationDTO;

public interface ICabinetService {

    void create(UserDTO userDTO);
    void verification(String code, String mail);
    String authorization(AuthorizationDTO authorizationDTO);
    UserEntity getInfoMe();
}

package by.it_academy.jd2.finance.service.api;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.dto.PageOfUserDTO;

import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    void create(UserDTO userDTO);
    PageOfUserDTO findByMail(Integer page, Integer size);
    Optional<UserEntity> findByMail(UUID uuid);
    void update(UUID uuid, Long dtUpdate, UserDTO userDTO);
    public UserEntity findByMail(String mail);
    public void save (UserEntity userEntity);
}

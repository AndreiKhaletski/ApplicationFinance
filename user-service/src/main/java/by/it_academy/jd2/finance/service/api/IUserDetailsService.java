package by.it_academy.jd2.finance.service.api;

import by.it_academy.jd2.finance.dao.entity.UserEntity;

public interface IUserDetailsService {
    UserEntity get(String username);
}

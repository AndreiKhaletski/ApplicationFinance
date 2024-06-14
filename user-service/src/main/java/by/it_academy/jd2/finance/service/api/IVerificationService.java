package by.it_academy.jd2.finance.service.api;

import by.it_academy.jd2.finance.dao.entity.UserVerificationEntity;

public interface IVerificationService {

    public void create(String mail);

    String get(String mail);

}

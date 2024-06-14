package by.it_academy.jd2.finance.service.api;


import by.it_academy.jd2.finance.dao.entity.UserVerificationEntity;
import by.it_academy.jd2.finance.service.api.exceptions.FailMailSendException;

public interface IEmailService {

    void sendEmailMessage(UserVerificationEntity userVerificationEntity) throws FailMailSendException;

}

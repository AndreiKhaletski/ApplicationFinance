package by.it_academy.jd2.finance.dao.api;

import by.it_academy.jd2.finance.dao.entity.UserVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserVerificationDao extends JpaRepository<UserVerificationEntity, String> {

    UserVerificationEntity findByMail(String mail);
}

package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.IUserService;
import by.it_academy.jd2.finance.service.api.IVerificationService;
import by.it_academy.jd2.finance.service.api.enums.EnumRole;
import by.it_academy.jd2.finance.service.api.enums.EnumStatusRegistration;
import by.it_academy.jd2.finance.service.jwt.JwtTokenHandler;
import by.it_academy.jd2.finance.service.api.ICabinetService;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.dto.AuthorizationDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class CabinetService implements ICabinetService {

    private final IVerificationService verificationService;
    private final JwtTokenHandler jwtTokenHandler;
    private final IUserService userService;
    private final PasswordEncoder encoder;

    public CabinetService(IVerificationService verificationService,
                          JwtTokenHandler jwtTokenHandler,
                          IUserService userService,
                          PasswordEncoder encoder) {
        this.verificationService = verificationService;
        this.jwtTokenHandler = jwtTokenHandler;
        this.userService = userService;
        this.encoder = encoder;
    }

    @Override
    @Transactional
    public void create(UserDTO userDTO) {
        userDTO.setStatus(EnumStatusRegistration.WAITING_ACTIVATION);
        userDTO.setRole(EnumRole.ROLE_USER);
        userService.create(userDTO);
    }

    @Override
    @Transactional
    public void verification(String code, String mail) {
        UserEntity userEntity = userService.findByMail(mail);

        if (Objects.equals(code, verificationService.get(mail))) {
            userEntity.setStatus(EnumStatusRegistration.ACTIVATED);
            userService.save(userEntity);
        } else {
            throw new IllegalArgumentException("Неверный код верификации");
        }
    }

    @Override
    @Transactional
    public String authorization(AuthorizationDTO authorizationDTO) {
        UserEntity userEntity = userService.findByMail(authorizationDTO.getMail());

        if (!encoder.matches(authorizationDTO.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("Неверный пользователь или пароль");
        }

        if(userEntity.getStatus() == EnumStatusRegistration.ACTIVATED){

            String token = jwtTokenHandler.generateAccessToken(
                    authorizationDTO.getMail(),
                    String.valueOf(userEntity.getRole()));
            return token;
        }else{
            throw new IllegalArgumentException("Необходимо подтвердить аккаунт для входа в систему");
        }
    }

    @Override
    public UserEntity getInfoMe() {
        return userService.findByMail(UserHolder.getUser().getUsername());
    }
}

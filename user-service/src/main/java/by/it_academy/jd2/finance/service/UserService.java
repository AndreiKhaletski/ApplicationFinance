package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.dao.api.IUserDao;
import by.it_academy.jd2.finance.dao.entity.UserEntity;
import by.it_academy.jd2.finance.service.api.IUserService;
import by.it_academy.jd2.finance.service.api.IVerificationService;
import by.it_academy.jd2.finance.service.api.dto.AboutUserDTO;
import by.it_academy.jd2.finance.service.api.dto.UserDTO;
import by.it_academy.jd2.finance.service.api.enums.EnumStatusRegistration;
import by.it_academy.jd2.finance.service.api.dto.PageOfUserDTO;
import by.it_academy.jd2.finance.service.converter.ConverterDTOToUserEntity;
import by.it_academy.jd2.finance.service.converter.ConverterEntityToUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserService implements IUserService {

    private final IUserDao userDao;
    private final ConverterDTOToUserEntity entityConvert;
    private final ConverterEntityToUserDTO dtoConvert;
    private final PasswordEncoder encoder;
    private final IVerificationService verificationService;

    public UserService(IUserDao userDao,
                       ConverterDTOToUserEntity entityConvert, ConverterEntityToUserDTO dtoConvert,
                       PasswordEncoder encoder,
                       IVerificationService verificationService) {
        this.userDao = userDao;
        this.entityConvert = entityConvert;
        this.dtoConvert = dtoConvert;
        this.encoder = encoder;
        this.verificationService = verificationService;
    }

    @Override
    @Transactional
    public void create(UserDTO userDTO) {
        UserEntity userEntity = entityConvert.convert(userDTO);

        if(userDao.findAllByMail(userEntity.getMail()) == null){
            userEntity.setUuid(UUID.randomUUID());
            userEntity.setPassword(encoder.encode(userDTO.getPassword()));

            if (userEntity.getStatus() == EnumStatusRegistration.WAITING_ACTIVATION) {
                verificationService.create(userEntity.getMail());
            }

            userDao.save(userEntity);
        }else {
            throw new IllegalArgumentException("Такой пользователь уже зарегистрирован!");
        }
    }


    @Override
    public Optional<UserEntity> findByMail(UUID uuid) {
        return userDao.findByUuid(uuid);
    }

    @Override
    @Transactional
    public void update(UUID uuid, Long dtUpdate, UserDTO userDTO) {

        Optional<UserEntity> optional = userDao.findByUuid(uuid);
        UserEntity entity = optional.get();
        updateProperties(entity, userDTO);

        LocalDateTime updatedDateTime = Instant.ofEpochMilli(dtUpdate).atZone(ZoneId.systemDefault()).toLocalDateTime();
        entity.setDt_update(updatedDateTime);
        if(!encoder.matches(userDTO.getPassword(), entity.getPassword())){
            entity.setPassword(encoder.encode(userDTO.getPassword()));
        }
        userDao.saveAndFlush(entity);

    }

    @Override
    @Transactional
    public UserEntity findByMail(String mail) {
        return userDao.findAllByMail(mail);
    }

    @Override
    public void save (UserEntity userEntity){
        userDao.saveAndFlush(userEntity);
    }

    @Override
    public PageOfUserDTO findByMail(Integer page, Integer size) {
        Page<UserEntity> users = userDao.findAll(PageRequest.of(page, size));
        return getPageUser(users);
    }

    private PageOfUserDTO getPageUser(Page<UserEntity> objects) {
        PageOfUserDTO pageOfUser = new PageOfUserDTO();
        pageOfUser.setNumber(objects.getNumber());
        pageOfUser.setSize(objects.getSize());
        pageOfUser.setTotal_pages(objects.getTotalPages());
        pageOfUser.setNumber_of_elements(objects.getNumberOfElements());
        pageOfUser.setFirst(objects.isFirst());
        pageOfUser.setTotal_elements(objects.getTotalElements());
        pageOfUser.setLast(objects.isLast());

        List<AboutUserDTO> userDTOList = objects
                .getContent()
                .stream()
                .map(dtoConvert::convertAboutUser)
                .collect(Collectors.toList());

        pageOfUser.setContent(userDTOList);
        return pageOfUser;
    }

    private void updateProperties(UserEntity entity, UserDTO dto) {
        entity.setMail(dto.getMail());
        entity.setFio(dto.getFio());
        entity.setRole(dto.getRole());
        entity.setStatus(dto.getStatus());
        entity.setPassword(dto.getPassword());
    }
}

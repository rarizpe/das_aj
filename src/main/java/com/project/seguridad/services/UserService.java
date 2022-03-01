package com.project.seguridad.services;

import com.project.base.services.BaseService;
import com.project.seguridad.dto.user.UserDto;
import com.project.seguridad.dto.user.UserInDto;
import com.project.seguridad.dto.user.UserResponse;
import com.project.seguridad.entity.UserEntity;
import com.project.seguridad.repositorie.UserRepository;
import com.project.utils.converter.ConvertUtils;
import com.project.utils.enums.ResponseCode;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by Elí.Giacomelli on 01/04/2020.
 */

@Service
public class UserService extends BaseService<UserRepository, UserEntity> {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ConvertUtils convertUtils;

    public UserResponse createUser(UserInDto dto) throws NotFoundException{

        if(StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getLastName()) || StringUtils.isEmpty(dto.getPassword())){
            throw new NotFoundException(ResponseCode.NOT_FOUND.getMessage());
        }
        UserEntity user = create(
                UserEntity.builder()
                        .name(dto.getName())
                        .lastName(dto.getLastName())
                        .email(dto.getEmail())
                        .password(dto.getPassword())
                        .build());

        return UserResponse.builder().uuid(user.getUuid()).build();
    }

    public UserResponse updateUser(UserDto dto) throws NotFoundException{
        UserEntity user = repository.findByUuid(dto.getUserUuid());
        if(null == user) {
            throw new NotFoundException(ResponseCode.NOT_FOUND.getMessage());
        }

        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        update(user);

        return UserResponse.builder().uuid(user.getUuid()).build();
    }

    public UserDto getUserByUuid(String uuid) throws NotFoundException {
        UserEntity user = findByUuId(uuid);
        if(user == null){
            throw new NotFoundException(ResponseCode.NOT_FOUND.getMessage());
        }
        return convertUtils.convert(findByUuId(uuid),UserDto.class);
    }

    public UserResponse deleteUser(String uuid) throws NotFoundException {
        UserEntity user = findByUuId(uuid);
        if(user == null){
            throw new NotFoundException(ResponseCode.NOT_FOUND.getMessage());
        }
        delete(user);
        return UserResponse.builder().uuid(user.getUuid()).build();
    }

}
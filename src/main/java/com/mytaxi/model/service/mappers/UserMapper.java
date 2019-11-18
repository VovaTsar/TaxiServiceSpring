package com.mytaxi.model.service.mappers;

import com.mytaxi.model.domain.User;
import com.mytaxi.model.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userEntityToUser (UserEntity userEntity);
    UserEntity userToUserEntity(User user);
}

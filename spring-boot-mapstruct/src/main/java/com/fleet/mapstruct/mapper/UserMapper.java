package com.fleet.mapstruct.mapper;

import com.fleet.mapstruct.entity.UserPo;
import com.fleet.mapstruct.entity.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "name", target = "username")
    })
    UserVo po2Vo(UserPo userPo);

    @Mappings({
            @Mapping(source = "userId", target = "id"),
            @Mapping(source = "username", target = "name")
    })
    UserPo vo2Po(UserVo userVo);
}

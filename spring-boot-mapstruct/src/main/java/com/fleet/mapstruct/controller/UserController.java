package com.fleet.mapstruct.controller;

import com.fleet.mapstruct.entity.UserPo;
import com.fleet.mapstruct.entity.UserVo;
import com.fleet.mapstruct.mapper.UserMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/po2Vo")
    public UserVo po2Vo() {
        UserPo userPo = new UserPo();
        userPo.setId(1L);
        userPo.setName("fleet");
        return UserMapper.INSTANCE.po2Vo(userPo);
    }

    @RequestMapping("/vo2Po")
    public UserPo vo2Po() {
        UserVo userVo = new UserVo();
        userVo.setUserId(1L);
        userVo.setUsername("fleet");
        return UserMapper.INSTANCE.vo2Po(userVo);
    }
}

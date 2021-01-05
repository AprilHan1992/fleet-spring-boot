package com.fleet.mso.controller.user;

import com.fleet.mso.config.handler.BaseException;
import com.fleet.mso.controller.BaseController;
import com.fleet.mso.entity.User;
import com.fleet.mso.enums.ResultState;
import com.fleet.mso.json.R;
import com.fleet.mso.service.UserService;
import com.fleet.mso.util.MD5Util;
import com.fleet.mso.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/pwd")
public class PwdController extends BaseController {

    @Resource
    UserService userService;

    /**
     * 修改密码
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     */
    @RequestMapping("/change")
    public R login(@RequestParam("oldPwd") String oldPwd, @RequestParam("newPwd") String newPwd) {
        if (StringUtils.isEmpty(oldPwd)) {
            throw new BaseException(ResultState.ERROR, "旧密码为空");
        }
        if (StringUtils.isEmpty(newPwd)) {
            throw new BaseException(ResultState.ERROR, "新密码为空");
        }

        // 当前用户
        User cur = getUser();
        if (cur == null) {
            throw new BaseException(ResultState.ERROR);
        }

        String pwd = MD5Util.encrypt(oldPwd, cur.getPwdSalt());
        if (!pwd.equals(cur.getPwd())) {
            throw new BaseException(ResultState.ERROR, "旧密码错误");
        }

        User user = new User();
        user.setId(cur.getId());
        // 生成新密码
        String salt = UUIDUtil.getUUID();
        user.setPwdSalt(salt);
        pwd = MD5Util.encrypt(newPwd, salt);
        user.setPwd(pwd);
        if (userService.update(user)) {
            return R.ok();
        }
        return R.error();
    }
}

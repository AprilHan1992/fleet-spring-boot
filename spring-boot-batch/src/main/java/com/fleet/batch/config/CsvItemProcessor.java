package com.fleet.batch.config;

import com.fleet.batch.entity.User;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;

public class CsvItemProcessor extends ValidatingItemProcessor<User> {

    @Override
    public User process(User user) throws ValidationException {
        // 执行 super.process() 才能调用自定义的校验器
        super.process(user);
        if (user.getGender().equals("男")) {
            user.setGender("M");
        } else {
            user.setGender("F");
        }
        return user;
    }
}

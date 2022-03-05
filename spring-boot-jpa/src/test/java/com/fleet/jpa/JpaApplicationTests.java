package com.fleet.jpa;

import com.fleet.jpa.entity.User;
import com.fleet.jpa.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTests {

    @Resource
    private UserRepository userRepository;

    @Test
    public void select() {
        Optional<User> user = userRepository.findById(1L);
        System.out.println(user);
    }
}

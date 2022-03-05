package com.fleet.qywx;

import com.fleet.qywx.service.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTests {

    @Resource
    TokenService tokenService;

    @Test
    public void getToken() throws IOException {
        System.out.println(tokenService.getToken());
    }
}

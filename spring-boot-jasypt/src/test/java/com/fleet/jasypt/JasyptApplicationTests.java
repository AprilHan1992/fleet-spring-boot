package com.fleet.jasypt;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JasyptApplicationTests {

    @Resource
    private StringEncryptor stringEncryptor;

    @Test
    public void encrypt() {
        String encrypt = stringEncryptor.encrypt("");
        System.out.println(encrypt);
    }

    @Test
    public void decrypt() {
        String encrypt = "ZPuwhwfeVTiCHBXqX3tRRgfetivI/wtYAgiBday2VOkK50ixRVnwSHxPyrLLXNLr";
        String decrypt = stringEncryptor.decrypt(encrypt);
        System.out.println(decrypt);
    }
}

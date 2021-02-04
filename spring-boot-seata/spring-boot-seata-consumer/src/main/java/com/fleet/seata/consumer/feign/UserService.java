package com.fleet.seata.consumer.feign;

import com.fleet.seata.consumer.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-user", url = "http://127.0.0.1:8002")
public interface UserService {

    @RequestMapping("/user/get")
    User get(@RequestParam Integer id);

    /**
     * 支付
     *
     * @param id
     * @param money
     * @return
     */
    @RequestMapping("/user/pay")
    boolean pay(@RequestParam("id") Integer id, @RequestParam("money") Integer money);
}

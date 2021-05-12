package com.fleet.flowable.handler;

import com.fleet.flowable.json.R;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author April Han
 */
@RestController
public class NotFoundException implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(ERROR_PATH)
    public R error(HttpServletRequest request, HttpServletResponse response) {
        int code = response.getStatus();
        if (code == 400) {
            return R.error(400, "Bad Request");
        } else if (code == 401) {
            return R.error(401, "Unauthorized");
        } else if (code == 403) {
            return R.error(403, "Forbidden");
        } else if (code == 404) {
            return R.error(404, "Not Found");
        } else {
            return R.error();
        }
    }
}

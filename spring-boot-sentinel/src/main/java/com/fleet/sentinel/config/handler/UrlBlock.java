package com.fleet.sentinel.config.handler;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.fleet.sentinel.json.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author April Han
 */
@Component
public class UrlBlock implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws IOException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        R r;
        if (e instanceof FlowException) {
            r = R.error("流控规则限制访问");
        } else if (e instanceof DegradeException) {
            r = R.error("降级规则限制访问");
        } else if (e instanceof ParamFlowException) {
            r = R.error("热点规则限制访问");
        } else if (e instanceof SystemBlockException) {
            r = R.error("系统规则限制访问");
        } else if (e instanceof AuthorityException) {
            r = R.error("授权规则限制访问");
        } else {
            r = R.error("限制访问");
        }
        httpServletResponse.getWriter().write(JSON.toJSONString(r));
    }
}

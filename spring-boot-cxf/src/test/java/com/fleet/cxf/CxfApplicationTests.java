package com.fleet.cxf;

import com.alibaba.fastjson.JSON;
import com.fleet.cxf.entity.User;
import com.fleet.cxf.service.UserService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CxfApplicationTests {

    /**
     * 调用方式：动态调用
     */
    @Test
    public void test1() throws Exception {
        JaxWsDynamicClientFactory jaxWsDynamicClientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = jaxWsDynamicClientFactory.createClient("http://127.0.0.1:8000/services/userService?wsdl");
        Object[] objects = client.invoke("getName", 1L);
        System.out.println("查询到用户名:" + objects[0].toString());
        objects = client.invoke("get", 1L);
        System.out.println("查询到用户:" + JSON.toJSONString(objects[0]));
    }

    /**
     * 调用方式：通过接口协议获取数据类型
     */
    @Test
    public void test2() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:8000/services/userService?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        UserService userService = (UserService) jaxWsProxyFactoryBean.create();
        String name = userService.getName(1L);
        System.out.println("查询到用户名:" + name);

        User user = userService.get(1L);
        System.out.println("查询到用户:" + JSON.toJSONString(user));
    }

    /**
     * 调用方式：通过接口协议获取数据类型，设置链接超时和响应时间
     */
    @Test
    public void test3() {
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress("http://127.0.0.1:8000/services/userService?wsdl");
        jaxWsProxyFactoryBean.setServiceClass(UserService.class);
        UserService userService = (UserService) jaxWsProxyFactoryBean.create();

        Client client = ClientProxy.getClient(userService);
        HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout(1000);
        httpClientPolicy.setReceiveTimeout(1000);
        httpConduit.setClient(httpClientPolicy);

        String name = userService.getName(1L);
        System.out.println("查询到用户名:" + name);

        User user = userService.get(1L);
        System.out.println("查询到用户:" + JSON.toJSONString(user));

    }

    /**
     * 调用方式：HttpClient 调用
     */
    @Test
    public void test4() throws Exception {
        String address = "http://127.0.0.1:8000/services/userService";
        HttpPost request = new HttpPost(address);
        request.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
        String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                " xmlns:sam=\"http://services.cxf.fleet.com\" " +
                " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" +
                " xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                "<sam:getName>" +
                "<id>1</id>" +
                "</sam:getName>" +
                "</soap:Body>" +
                "</soap:Envelope>";
        HttpEntity entity = new StringEntity(requestXml, "UTF-8");
        request.setEntity(entity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(request);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("查询到用户名:" + jsonString);
        }
    }

    /**
     * 调用方式：HttpClient 调用
     */
    @Test
    public void test5() throws Exception {
        String address = "http://127.0.0.1:8000/services/userService";
        HttpPost request = new HttpPost(address);
        request.setHeader("Content-Type", "application/soap+xml; charset=utf-8");
        String requestXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
                " xmlns:sam=\"http://services.cxf.fleet.com\" " +
                " xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"" +
                " xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
                "<soap:Body>" +
                "<sam:get>" +
                "<id>1</id>" +
                "</sam:get>" +
                "</soap:Body>" +
                "</soap:Envelope>";
        HttpEntity entity = new StringEntity(requestXml, "UTF-8");
        request.setEntity(entity);
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse httpResponse = httpClient.execute(request);
        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            String jsonString = EntityUtils.toString(httpResponse.getEntity());
            System.out.println("查询到用户:" + jsonString);
        }
    }
}

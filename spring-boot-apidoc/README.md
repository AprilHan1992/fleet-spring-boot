# spring-boot-apidoc

> 使用 apidoc 自动生成接口文档

## 环境搭建

1. 安装 node.js
2. 使用 npm 安装 apidoc `npm install apidoc –g`
3. 配置 apidoc.json

```

{
  "name": "接口文档",
  "version": "1.0.0",
  "description": "接口文档",
  "title": "接口文档",
  "url": "http://localhost:8000/",
  "sampleUrl": "http://localhost:8000/"
}

```

4. 编写apidoc文档

```

/**
 * @api {get} user/get 根据用户id查询用户信息
 * @apiVersion 1.0.0
 * @apiGroup 用户信息
 * @apiName 1、根据用户id查询用户信息
 * @apiDescription 根据用户id查询用户信息
 * @apiParam {Long} id 用户id
 * @apiSuccess {Long} id 用户id
 * @apiSuccess {String} name 用户名
 * @apiSuccessExample Success-Response:
 * { "id": 1, "name": "fleet" }
 * @apiSampleRequest user/get
 */
@CrossOrigin
@RequestMapping(value = "/get")
public User get(@RequestParam Long id) {
    User user = new User();
    user.setId(1L);
    user.setName("fleet");
    return user;
}

```

5. 执行命令，生成文档 `apidoc -i fleet-spring-boot/spring-boot-apidoc -o   fleet-spring-boot/aipdoc`

## 修改apidoc

> apidoc 不支持 json 格式参数，可以通过修改 apidoc 工具代码实现

1. 修改 apidoc 工具，查找 apidoc\template\utils\send_sample_request.js 文件
2. 将

```

// send AJAX request, catch success or error callback
var ajaxRequest = {
    url: url,
    headers: header,
    data: param,
    type: type.toUpperCase(),
    success: displaySuccess,
    error: displayError
};
$.ajax(ajaxRequest);

```

修改为

```

// send AJAX request, catch success or error callback
if (param.json) {
	var ajaxRequest = {
		url: url,
        headers: header,
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(JSON.parse(param.json)),
        type: type.toUpperCase(),
        success: displaySuccess,
        error: displayError
    };
    $.ajax(ajaxRequest);
} else {
	var ajaxRequest = {
        url: url,
        headers: header,
        data: param,
        type: type.toUpperCase(),
        success: displaySuccess,
        error: displayError
    };
    $.ajax(ajaxRequest);
}

```

3. 在接口注释中使用

```

/**
 * @apiParam {String} json User对象
 */

```

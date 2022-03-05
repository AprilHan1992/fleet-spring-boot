package com.fleet.restdocs;

import com.alibaba.fastjson.JSON;
import com.fleet.restdocs.entity.User;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTests extends BaseTests {

    private static final ResponseFieldsSnippet responseFieldsSnippet = responseFields(
            fieldWithPath("id").description("用户id"),
            fieldWithPath("name").description("用户名")
    );

    @Test
    public void insertUser() throws Exception {
        User user = new User();
        user.setName("fleet");
        super.mockMvc.perform(post("/user/insert").contentType(MediaType.APPLICATION_JSON).content(JSON.toJSONString(user)))
                .andExpect(status().isOk())
                .andDo(document("insert"));
    }

    @Test
    public void deleteUser() throws Exception {
        super.mockMvc.perform(get("/user/delete?id=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("delete"));
    }

    @Test
    public void updateUser() throws Exception {
        User user = new User();
        user.setId(1L);
        super.mockMvc.perform(post("/user/update").contentType(MediaType.APPLICATION_JSON).param("name", "fleet").content(JSON.toJSONString(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("update"));
    }

    @Test
    public void getUser() throws Exception {
        super.mockMvc.perform(get("/user/get").param("id", "1"))
                .andExpect(status().isOk())
                .andDo(document("get", responseFieldsSnippet));
    }
}

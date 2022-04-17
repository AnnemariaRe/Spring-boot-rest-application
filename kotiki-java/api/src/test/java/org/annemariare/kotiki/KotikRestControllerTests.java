package org.annemariare.kotiki;

import org.annemariare.kotiki.api.KotikController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class KotikRestControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private KotikController controller;

    @Test
    public void test() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void ShouldReturnAllKotikiTest() throws Exception {
        this.mockMvc.
                perform(MockMvcRequestBuilders
                        .get("/kotiki/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Gosha"));
    }

    @Test
    public void ShouldCreateKotikTest() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/kotiki/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 3,\n" +
                                " \"name\": \"Boba\",\n" +
                                " \"birthdayDate\": \"2022-04-13\",\n" +
                                " \"breed\": \"american\",\n" +
                                " \"color\": \"CREAM\",\n" +
                                " \"owner\": {\n" +
                                " \"id\": 1,\n" +
                                " \"name\": \"Annemaria\",\n" +
                                " \"birthdayDate\": \"2001-06-04\"\n" +
                                " }}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

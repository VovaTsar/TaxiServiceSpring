package ua.company.taxi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ua.company.taxi.config.LoginSuccessHandler;
import ua.company.taxi.model.service.ClientService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ClientService clientService;

    @MockBean
    private LoginSuccessHandler handler;

    @Test
    public void indexShouldReturnMainPage() throws Exception {
        mvc.perform(get("/index"))
                .andExpect(view().name("index"))
                .andExpect(status().isOk());
    }

    @Test
    public void registrationShouldReturnRegistrationPage() throws Exception {
        mvc.perform(get("/register"))
                .andExpect(view().name("register"))
                .andExpect(status().isOk());
    }


}
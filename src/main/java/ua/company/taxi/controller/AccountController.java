package ua.company.taxi.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.service.ClientService;

import java.util.Map;

@Controller
@RequestMapping(path = "/")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final ClientService clientService;


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping("/login")
    public String getLogin(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        model.addAttribute("error", error != null);
        model.addAttribute("logout", logout != null);

        return "login";
    }


    @GetMapping("/register")
    public String registration(Map<String, Object> model) {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(Client client, Map<String, Object> model) {
        UserDetails user = clientService.loadUserByUsername(client.getUsername());

        if (user != null) {
            model.put("message", "User is exists!");
            return "register";
        }

        clientService.registerClient(client);

        model.put("success_reg", "Successful registration!");
        return "index";
    }

}

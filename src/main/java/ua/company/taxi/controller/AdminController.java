package ua.company.taxi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.company.taxi.model.service.ClientService;
import ua.company.taxi.model.service.UtilityService;

import java.util.Map;

@Controller
@RequestMapping(path = "/admin")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController {

    private final UtilityService utilityService;
    private final ClientService clientService;


    @ModelAttribute
    public void addAttributes(Map<String, Object> model) {
        model.put("role", "admin");
    }

    @GetMapping(path = "/home")
    String getAdminHome(Map<String, Object> model) {

        model.put("client", clientService.getCurrentClient());
        return "adminHome";
    }

    @GetMapping(path = "/cars")
    public String getCars(Map<String, Object> model,
                          @PageableDefault(size = 7) Pageable page) {

        model.put("page", utilityService.buildPageCars(page));

        model.put("url", "/admin/cars");
        return "cars";
    }

    @GetMapping(path = "/clients")
    public String getClients(Map<String, Object> model,
                             @PageableDefault(size = 7) Pageable page) {
        model.put("page", utilityService.buildPageClients(page));

        model.put("url", "/admin/clients");
        return "clients";
    }
}

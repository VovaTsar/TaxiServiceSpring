package ua.company.taxi.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.company.taxi.model.domain.Client;


public interface ClientService extends UserDetailsService {

    void registerClient(Client client);

    void addToSpentValue(Long value);

    Client getCurrentClient();

}

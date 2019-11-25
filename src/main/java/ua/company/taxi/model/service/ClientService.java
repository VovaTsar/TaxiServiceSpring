package ua.company.taxi.model.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Client;

@Service
public interface ClientService extends UserDetailsService {

    void registerClient(Client client);

    void addToSpentValue(Long value);

    Client getCurrentClient();

}

package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.entity.Role;
import ua.company.taxi.model.exception.UncorrectInputDataRuntimeExeption;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.service.ClientService;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;



    @Override
    public Client getCurrentClient() {
        log.info("ClientServiceImpl:getCurrentClient");
        return (Client) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }


    @Override
    public UserDetails loadUserByUsername(String login) {
        if (Objects.isNull(login)){
            log.error("ClientServiceImpl:loadUserByUsername");
            throw new UncorrectInputDataRuntimeExeption("login is empty");
        }
        log.info("ClientServiceImpl:loadUserByUsername");
        Optional<ClientEntity> byLogin = clientRepository
                .findByLogin(login);
        return byLogin.map(clientMapper::clientEntityToClient).orElse(null);
    }


    @Override
    public void registerClient(Client client) {
        if (Objects.isNull(client)){
            log.error("ClientServiceImpl:registerClient");
            throw new UncorrectInputDataRuntimeExeption("client is empty");
        }
        log.info("ClientServiceImpl:registerClient");
        client.setRole(Role.ROLE_USER);
        client.setTotalSpentValue(0L);
        client.setPassword((new BCryptPasswordEncoder().encode(client.getPassword())));
        clientRepository.save(clientMapper.clientToClientEntity(client));
    }

    @Override
    public void addToSpentValue(Long value) {
        log.info("ClientServiceImpl:addToSpentValue");
        clientRepository.updateSpentValue(getCurrentClient().getId(), value);

    }


}

package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.Role;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.service.ClientService;

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
        return (Client) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }


    @Override
    public UserDetails loadUserByUsername(String login) {
        if (Objects.isNull(login)) {
            log.warn("ClientServiceImpl:loadUserByUsername");
            throw new UnCorrectInputDataRuntimeException("Login is empty");
        }
        Optional<ClientEntity> byLogin = clientRepository
                .findByLogin(login);

        return byLogin.map(clientMapper::clientEntityToClient).orElse(null);
    }


    @Override
    public void registerClient(Client client) {
        if (Objects.isNull(client)) {
            log.warn("ClientServiceImpl:registerClient");
            throw new UnCorrectInputDataRuntimeException("Client is empty");
        }
        client.setRole(Role.ROLE_USER);
        client.setTotalSpentValue(0L);
        client.setPassword((new BCryptPasswordEncoder().encode(client.getPassword())));
        clientRepository.save(clientMapper.clientToClientEntity(client));
    }

    @Override
    public void addToSpentValue(Long value) {
        if (value < 0) {
            log.warn("ClientServiceImpl:addToSpentValue");
            throw new UnCorrectInputDataRuntimeException("Value must be positive");
        }
        clientRepository.updateSpentValue(getCurrentClient().getId(), value);
    }


}

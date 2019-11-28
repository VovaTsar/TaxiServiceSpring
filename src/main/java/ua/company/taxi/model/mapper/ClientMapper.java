package ua.company.taxi.model.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;

@Component
@Slf4j
public class ClientMapper {

    public Client clientEntityToClient(ClientEntity clientEntity) {
        if (clientEntity == null) {
            return null;
        }

        return Client.builder()
                .id(clientEntity.getId())
                .login(clientEntity.getLogin())
                .password(clientEntity.getPassword())
                .totalSpentValue(clientEntity.getTotalSpentValue())
                .role(clientEntity.getRole())
                .build();
    }

    public ClientEntity clientToClientEntity(Client client) {
        if (client == null) {
            return null;
        }

        return ClientEntity.builder()
                .id(client.getId())
                .login(client.getLogin())
                .password(client.getPassword())
                .totalSpentValue(client.getTotalSpentValue())
                .role(client.getRole())
                .build();
    }
}

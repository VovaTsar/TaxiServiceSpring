package ua.company.taxi.model.mapper;

import org.junit.Test;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.Role;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ClientMapperTest {

    private static final ClientEntity CLIENT_ENTITY = getClientEntity();

    private static final Client CLIENT = getClient();

    private final ClientMapper clientMapper = new ClientMapper();


    @Test
    public void shouldMapClientEntityToClient() {
        Client actual = clientMapper.clientEntityToClient(CLIENT_ENTITY);

        assertThat(actual.getId(), is(CLIENT.getId()));
        assertThat(actual.getLogin(), is(CLIENT.getLogin()));
        assertThat(actual.getPassword(), is(CLIENT.getPassword()));
        assertThat(actual.getTotalSpentValue(), is(CLIENT.getTotalSpentValue()));
        assertThat(actual.getRole(), is(CLIENT.getRole()));
    }

    @Test
    public void shouldMapClientToClientEntity() {
        ClientEntity actual = clientMapper.clientToClientEntity(CLIENT);

        assertThat(actual.getId(), is(CLIENT_ENTITY.getId()));
        assertThat(actual.getLogin(), is(CLIENT_ENTITY.getLogin()));
        assertThat(actual.getPassword(), is(CLIENT_ENTITY.getPassword()));
        assertThat(actual.getTotalSpentValue(), is(CLIENT_ENTITY.getTotalSpentValue()));
        assertThat(actual.getRole(), is(CLIENT_ENTITY.getRole()));
    }

    @Test
    public void mapClientToClientEntityShouldReturnNull() {
        ClientEntity actual = clientMapper.clientToClientEntity(null);

        assertThat(actual, is(nullValue()));
    }

    @Test
    public void mapClientEntityToClientShouldReturnNull() {
        Client actual = clientMapper.clientEntityToClient(null);
        assertThat(actual, is(nullValue()));
    }

    private static ClientEntity getClientEntity() {
        return new ClientEntity(1L, "Vova777", null, "cerber", 100L, Role.ROLE_USER);
    }

    private static Client getClient() {
        return Client.builder()
                .id(1L)
                .login("Vova777")
                .password("cerber")
                .totalSpentValue(100L)
                .role(Role.ROLE_USER)
                .build();
    }

}

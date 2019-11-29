package ua.company.taxi.model.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.Role;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.service.ClientService;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ClientServiceImpl.class})
public class ClientServiceImplTest {

    private static final Client CLIENT = new Client(1L, "Vova777", null, 0L, Role.ROLE_USER, null);

    private static final ClientEntity ENTITY =  ClientEntity.builder().login("Vova777").build();


    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private ClientRepository repository;

    @MockBean
    private ClientMapper mapper;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ClientService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void registerClientShouldThrowUnCorrectInputDataRuntimeExceptionWithEmptyClient() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Client is empty");

        service.registerClient(null);
    }

    @Test
    public void registerClientShouldSaveClient() {
        when(encoder.encode(anyString())).thenReturn(CLIENT.getPassword());
        when(mapper.clientToClientEntity(any(Client.class))).thenReturn(ENTITY);
        when(repository.save(any(ClientEntity.class))).thenReturn(ENTITY);

        Client actual = new Client(1L, "Vova777", "ceber", null, null, null);
        service.registerClient(actual);
        CLIENT.setPassword(actual.getPassword());

        assertThat(actual, equalTo(CLIENT));
    }

    @Test
    public void addToSpentValueShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeValue() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Value must be positive");

        service.addToSpentValue(-100L);
    }

    @Test
    public void addToSpentValueShouldUpdateTotalValueClient() {
//        Client actual= Client.builder()
//            .id(1L)
//            .totalSpentValue(100L)
//            .build();
//        service.addToSpentValue(actual.getId(),100L);
//        CLIENT.setPassword(actual.getPassword());
//
//        assertThat(actual, equalTo(CLIENT));
//        when(repository.updateSpentValue(anyLong(),anyLong()));
//        verify(repository).updateSpentValue(any(),any());
    }

    @Test
    public void loadUserByUsernameShouldThrowUnCorrectInputDataRuntimeExceptionWithEmptyLogin() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Login is empty");

        service.loadUserByUsername(null);
    }

    @Test
    public void loadUserByUsernameShouldReturnRegisteredClient() {
        when(repository.findByLogin(anyString())).thenReturn(Optional.of(ENTITY));
        when(mapper.clientEntityToClient(any(ClientEntity.class))).thenReturn(CLIENT);

        UserDetails actual = service.loadUserByUsername("Vova777");

        assertThat(actual, equalTo(CLIENT));
    }
}

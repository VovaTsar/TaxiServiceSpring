package ua.company.taxi.model.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.DiscountRepository;
import ua.company.taxi.model.service.DiscountService;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DiscountServiceImpl.class})
public class DiscountServiceImplTest {

    private static final Integer DISCOUNT = 10;

    private static final ClientEntity CLIENT_ENTITY = ClientEntity.builder().totalSpentValue(100L).build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private DiscountRepository repository;

    @MockBean
    private ClientMapper mapper;

    @Autowired
    private DiscountService service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }


    @Test
    public void getClientDiscountShouldThrowUnCorrectInputDataRuntimeExceptionWithEmptyClient() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Client is empty");

        service.getClientDiscount(null);
    }

    @Test
    public void getClientDiscountShouldReturnClientDiscount() {
        when(repository.getPersonalDiscount(anyLong())).thenReturn(DISCOUNT);
        when(mapper.clientToClientEntity(any(Client.class))).thenReturn(CLIENT_ENTITY);
        Client client = Client.builder().totalSpentValue(100L).build();
        Integer actual = service.getClientDiscount(client);
        assertThat(actual, equalTo(DISCOUNT));
    }

}

package ua.company.taxi.model.service.impl;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ua.company.taxi.model.entity.ClientEntity;
import ua.company.taxi.model.entity.Role;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.repository.AddressRepository;
import ua.company.taxi.model.repository.CarRepository;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.UtilityService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UtilityServiceImpl.class})
public class UtilityServiceImplTest {
    private static final ClientEntity ENTITY =  ClientEntity.builder().login("Vova777").build();
    private static final List<ClientEntity> CLIENT_ENTITIES = Arrays.asList(ENTITY, ENTITY);

    private static final Long COUNT_CAR = 2L;
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private AddressRepository addressRepository;

    @MockBean
    private CarRepository carRepository;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    private UtilityService service;

    @After
    public void resetMock() {
        reset(addressRepository, carRepository, orderRepository, clientRepository);
    }


    @Test
    public void countPriceShouldThrowUnCorrectInputDataRuntimeExceptionWithNegativeDiscountAndTime() {
        exception.expect(UnCorrectInputDataRuntimeException.class);
        exception.expectMessage("Discount or time must be positive");

        service.countPrice(-100, -30L);
    }

    @Test
    public void countPriceShouldReturnCorrectPrice() {
        Long expected = 60L;
        Long actual = service.countPrice(20, 30L);
        assertThat(actual, equalTo(expected));
    }
    @Test
    public void buildPageClientsShouldReturnCorrectPage() {
//       when(clientRepository.findByRole(Role.ROLE_USER)).thenReturn(CLIENT_ENTITIES);
//       when(orderRepository.countAllByClientEntity(any(ClientEntity.class))).thenReturn(COUNT_CAR);
//        Pageble pageble=new PageImpl<>();
//
//       service.buildPageClients();
    }
//    private static final ClientEntity ENTITY =  ClientEntity.builder().login("Vova777").build();
//    @Override
//    public Page<Client> buildPageClients(Pageable pageable) {
//        final List<Client> clients = new ArrayList<>();
//
//        clientRepository.findByRole(Role.ROLE_USER).forEach(v ->
//                clients.add(Client
//                        .builder()
//                        .login(v.getLogin())
//                        .numRides(orderRepository.countAllByClientEntity(v))
//                        .totalSpentValue(v.getTotalSpentValue())
//                        .build()));
//        List<Client> resList = buildSubList(clients, pageable);
//
//        return new PageImpl<>(resList, pageable, clients.size());
//    }

}

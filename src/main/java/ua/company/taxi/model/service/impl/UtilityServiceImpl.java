package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Car;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.AddressEntity;
import ua.company.taxi.model.entity.CarEntity;
import ua.company.taxi.model.entity.OrderEntity;
import ua.company.taxi.model.entity.Role;
import ua.company.taxi.model.repository.AddressRepository;
import ua.company.taxi.model.repository.CarRepository;
import ua.company.taxi.model.repository.ClientRepository;
import ua.company.taxi.model.repository.OrderRepository;
import ua.company.taxi.model.service.UtilityService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UtilityServiceImpl implements UtilityService {

    private final AddressRepository addressRepository;
    private final CarRepository carRepository;
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;


    @Override
    public Long countPrice(Integer discount, Long time) {
        return time * (100 - discount) / 40;
    }

    @Override
    public Page<Order> buildPageOrders(Pageable pageable, Client client) {
        final List<Order> orders = new ArrayList<>();

        for (OrderEntity v : orderRepository.findAllByClientEntityId(client.getId())) {
            final Optional<CarEntity> carEntity = carRepository.findById(v.getCarEntity().getId());
            final Optional<AddressEntity> addressEntity = addressRepository.findById(v.getAddressEntity().getId());
            orders.add(Order
                    .builder()
                    .price(v.getPrice())
                    .carMake(carEntity.map(CarEntity::getMake).orElse(null))
                    .carType(carEntity.map(CarEntity::getType).orElse(null))
                    .destPlace(addressEntity.map(AddressEntity::getDestinationPlace).orElse(null))
                    .initPlace(addressEntity.map(AddressEntity::getInitialPlace).orElse(null))
                    .time(addressEntity.map(AddressEntity::getTime).orElse(null))
                    .build());
        }
        List<Order> resList = buildSubList(orders, pageable);

        return new PageImpl<>(resList, pageable, orders.size());
    }

    @Override
    public Page<Car> buildPageCars(Pageable pageable) {
        final List<Car> cars = new ArrayList<>();

        carRepository.findAll().forEach(v -> cars.add(Car
                .builder()
                .carMake(v.getMake())
                .carType(v.getType())
                .id(v.getId())
                .numRides(orderRepository.countAllByCarEntity(v))
                .build()));
        List<Car> resList = buildSubList(cars, pageable);

        return new PageImpl<>(resList, pageable, cars.size());
    }


    @Override
    public Page<Client> buildPageClients(Pageable pageable) {
        final List<Client> clients = new ArrayList<>();

        clientRepository.findByRole(Role.ROLE_USER).forEach(v ->
                clients.add(Client
                        .builder()
                        .login(v.getLogin())
                        .numRides(orderRepository.countAllByClientEntity(v))
                        .totalSpentValue(v.getTotalSpentValue())
                        .build()));
        List<Client> resList = buildSubList(clients, pageable);

        return new PageImpl<>(resList, pageable, clients.size());
    }

    private <T> List<T> buildSubList(List<T> list, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int start = pageNumber * pageSize;
        if (start >= list.size()) {
            return emptyList();
        }
        int end = Math.min(pageNumber * pageSize + pageSize, list.size());

        return list.subList(start, end);
    }

}

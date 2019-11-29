package ua.company.taxi.model.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.company.taxi.model.domain.Client;
import ua.company.taxi.model.exception.UnCorrectInputDataRuntimeException;
import ua.company.taxi.model.mapper.ClientMapper;
import ua.company.taxi.model.repository.DiscountRepository;
import ua.company.taxi.model.service.DiscountService;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final ClientMapper clientMapper;


    @Override
    public Integer getClientDiscount(Client client) {
        if (Objects.isNull(client)) {
            log.warn("DiscountServiceImpl:getClientDiscount");
            throw new UnCorrectInputDataRuntimeException("Client is empty");
        }

        return discountRepository.getPersonalDiscount(clientMapper.clientToClientEntity(client)
                .getTotalSpentValue());
    }


}

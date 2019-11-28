package ua.company.taxi.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.company.taxi.model.domain.Order;
import ua.company.taxi.model.entity.CarType;
import ua.company.taxi.model.entity.Street;
import ua.company.taxi.model.service.*;

import java.util.Map;

@Controller

@RequestMapping(path = "/user")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final ClientService clientService;
    private final CarService carService;
    private final DiscountService discountService;
    private final AddressService addressService;
    private final OrderService orderService;
    private final UtilityService utilityService;


    @GetMapping(path = "/home")
    public String showHome(Model model,
                           @RequestParam(value = "cancel", required = false) String cancel) {


        model.addAttribute("cancel", cancel != null);
        model.addAttribute("client", clientService.getCurrentClient());
        model.addAttribute("numRides", orderService.getNumRides(clientService.getCurrentClient().getId()));
        return "userHome";
    }

    @GetMapping(path = "/order")
    public String showOrderPage(Map<String, Object> model) {

        return "order";
    }

    @PostMapping(path = "/order")
    public String createRide(Map<String, Object> model,
                             @RequestParam CarType carType,
                             @RequestParam Street initPlace,
                             @RequestParam Street destPlace,
                             @RequestParam(required = false) Long carId) {
        if (initPlace == destPlace) {
            model.put("error", "initPlace == destPlace");
            return "order";
        }
        model.put("cars", carService.getAvailableType(carType, initPlace));
        model.put("initPlace", initPlace);
        model.put("destPlace", destPlace);
        model.put("carType", carType);
        if (carId != null) {
            model.put("waitTime", addressService.findLongTime(carService.getCarById(carId).getPlace(), destPlace));
            model.put("rideTime", addressService.findLongTime(initPlace, destPlace));
            model.put("addressId", addressService.findAllByDestinationPlaceAndInitialPlace(initPlace, destPlace));
            model.put("price", utilityService.countPrice(
                    discountService.getClientDiscount(clientService.getCurrentClient()),
                    addressService.findLongTime(initPlace, destPlace)));
            model.put("car", carService.getCarById(carId));
        }
        return "order";
    }

    @ModelAttribute
    public void addAttributes(Map<String, Object> model) {
        model.put("role", "user");
        model.put("discount", discountService.getClientDiscount(clientService.getCurrentClient()));
        model.put("types", CarType.values());
        model.put("streets", Street.values());
    }

    @PostMapping(path = "/history")
    public String confirmOrder(Map<String, Object> model,
                               @RequestParam Long carId,
                               @RequestParam Long addressId,
                               @RequestParam Long price,
                               @RequestParam Long waitTime,
                               @PageableDefault(size = 7) Pageable pageable) {

        orderService.addOrder(Order.builder()
                .car(carService.getCarById(carId))
                .client(clientService.getCurrentClient())
                .price(price)
                .address(addressService.findById(addressId))
                .waitTime(waitTime)
                .build());
        clientService.addToSpentValue(price);


        model.put("success", "success");

        model.put("page", utilityService.buildPageOrders(pageable, clientService.getCurrentClient()));
        model.put("url", "/user/history");

        return "orderHistory";
    }

    @GetMapping(path = "/history")
    public String getOrderHistory(Map<String, Object> model,
                                  @PageableDefault(size = 7) Pageable page) {

        model.put("page", utilityService.buildPageOrders(page, clientService.getCurrentClient()));
        model.put("url", "/user/history");

        return "orderHistory";
    }


}

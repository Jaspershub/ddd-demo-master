package com.example.domaindriverdesign.order.order;

import com.example.domaindriverdesign.order.order.command.ChangeAddressDetailCommand;
import com.example.domaindriverdesign.order.order.command.ChangeProductCountCommand;
import com.example.domaindriverdesign.order.order.command.CreateOrderCommand;
import com.example.domaindriverdesign.order.order.command.PayOrderCommand;
import com.example.domaindriverdesign.order.order.model.Order;
import com.example.domaindriverdesign.order.order.representation.OrderRepresentation;
import com.example.domaindriverdesign.order.order.representation.OrderRepresentationService;
import com.example.domaindriverdesign.order.order.representation.OrderSummaryRepresentation;
import com.example.domaindriverdesign.shared.utils.Page4Navigator;
import com.example.domaindriverdesign.shared.utils.PagedResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static com.google.common.collect.ImmutableMap.of;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderApplicationService applicationService;
    private final OrderRepresentationService representationService;

    public OrderController(OrderApplicationService applicationService,
                           OrderRepresentationService representationService) {
        this.applicationService = applicationService;
        this.representationService = representationService;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Map<String, String> createOrder(@RequestBody @Valid CreateOrderCommand command) {
        return of("id", applicationService.createOrder(command));
    }

    @PostMapping("/{id}/products")
    public void changeProductCount(@PathVariable(name = "id") String id, @RequestBody @Valid ChangeProductCountCommand command) {
        applicationService.changeProductCount(id, command);
    }

    @PostMapping("/{id}/payment")
    public void pay(@PathVariable(name = "id") String id, @RequestBody @Valid PayOrderCommand command) {
        applicationService.pay(id, command);
    }

    @PostMapping("/{id}/address/detail")
    public void changeAddressDetail(@PathVariable(name = "id") String id, @RequestBody @Valid ChangeAddressDetailCommand command) {
        applicationService.changeAddressDetail(id, command.getDetail());
    }

    @GetMapping("/{id}")
    public OrderRepresentation byId(@PathVariable(name = "id") String id) {
        return representationService.byId(id);
    }

    @GetMapping
    public PagedResource<OrderSummaryRepresentation> pagedProducts(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                                                   @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return representationService.listOrders(pageIndex, pageSize);
    }

    @RequestMapping("/listOrder")
    public String listCategory(Model m, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
        start = start<0?0:start;
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page4Navigator<Order> page =OrderApplicationService.list(pageable);
        m.addAttribute("page", page);
        return "listCategory";
    }
}

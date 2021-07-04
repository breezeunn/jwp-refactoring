package kitchenpos.ui;

import io.swagger.annotations.ApiOperation;
import kitchenpos.application.OrderService;
import kitchenpos.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation("주문 생성")
    @PostMapping("/api/orders")
    public ResponseEntity<Order> create(@RequestBody final Order order) {
        final Order created = orderService.create(order);
        final URI uri = URI.create("/api/orders/" + created.getId());
        return ResponseEntity.created(uri).body(created);
    }

    @ApiOperation("전체 주문 조회")
    @GetMapping("/api/orders")
    public ResponseEntity<List<Order>> list() {
        return ResponseEntity.ok().body(orderService.list());
    }

    @ApiOperation("주문 상태 변경")
    @PutMapping("/api/orders/{orderId}/order-status")
    public ResponseEntity<Order> changeOrderStatus(@PathVariable final Long orderId,
                                                   @RequestBody final Order order) {
        return ResponseEntity.ok(orderService.changeOrderStatus(orderId, order));
    }
}

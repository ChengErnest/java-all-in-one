package cn.nicollcheng.seata.service.impl;

import cn.nicollcheng.seata.entity.Order;
import cn.nicollcheng.seata.mapper.OrderMapper;
import cn.nicollcheng.seata.rest.WarehouseService;
import cn.nicollcheng.seata.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b><code>OrderServiceImpl</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:21.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final long MONEY = 100L;
    private final OrderMapper orderMapper;
    private final WarehouseService warehouseService;

    public OrderServiceImpl(OrderMapper orderMapper, WarehouseService warehouseService) {
        this.orderMapper = orderMapper;
        this.warehouseService = warehouseService;
    }

    @GlobalTransactional
    @Override
    public ResponseEntity<String> createOrder(String id, String desc) {
        Order order = Order.builder().content(desc).build();
        if (StringUtils.isNotBlank(id)){
            order.setId(Long.parseLong(id));
        }
        warehouseService.reduceInventory(1L, 1L);
        orderMapper.insert(order);
        return ResponseEntity.ok("success");
    }
}

package cn.nicollcheng.seata.controller;

import cn.nicollcheng.seata.service.WarehouseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b><code>OrderController</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:23.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    /**
     * 减库存
     * @param id 商品id
     * @param inventory 库存量
     */
    @PostMapping
    public ResponseEntity<String> reduceInventory(Long id, Long inventory){
        return warehouseService.reduceInventory(id, inventory);
    }
}

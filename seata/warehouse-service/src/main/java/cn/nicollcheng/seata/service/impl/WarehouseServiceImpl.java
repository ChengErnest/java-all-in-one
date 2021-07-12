package cn.nicollcheng.seata.service.impl;

import cn.nicollcheng.seata.entity.Warehouse;
import cn.nicollcheng.seata.mapper.WarehouseMapper;
import cn.nicollcheng.seata.service.WarehouseService;
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
public class WarehouseServiceImpl implements WarehouseService {
    private final long QUANTITY = 8L;
    private final WarehouseMapper warehouseMapper;

    public WarehouseServiceImpl(WarehouseMapper warehouseMapper) {
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public ResponseEntity<String> reduceInventory(Long id, Long inventory) {
        Warehouse warehouse = warehouseMapper.selectById(id);
        if (warehouse.getQuantity() - inventory < 0) {
            throw new RuntimeException("仓储不足!");
        }
        warehouse.setQuantity(warehouse.getQuantity() - 1);
        warehouseMapper.updateById(warehouse);
        return ResponseEntity.ok("success");
    }
}

package cn.nicollcheng.seata.rest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <b><code>WarehouseService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:30.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
@Component
@FeignClient("warehouse-service")
public interface WarehouseService {
    @PostMapping("/warehouses")
    ResponseEntity<String> reduceInventory(@RequestParam("id") Long id, @RequestParam("inventory") Long inventory);
}

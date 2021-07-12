package cn.nicollcheng.seata.service;

import org.springframework.http.ResponseEntity;

/**
 * <b><code>OrderService</code></b>
 * <p/>
 * Description
 * <p/>
 * <b>Creation Time:</b> 2021/7/12 15:20.
 *
 * @author nicollcheng
 * @since java-all-in-one 2021
 */
public interface WarehouseService {
    ResponseEntity<String> reduceInventory(Long id, Long inventory);
}

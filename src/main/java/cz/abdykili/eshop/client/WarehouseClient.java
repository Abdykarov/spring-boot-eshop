package cz.abdykili.eshop.client;

import cz.abdykili.eshop.client.model.ProductStockResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("warehouse/api/v1/stocks")
public interface WarehouseClient {

    @GetMapping("products/{id}")
    ProductStockResponse getStock(@PathVariable("id") Long id);
}

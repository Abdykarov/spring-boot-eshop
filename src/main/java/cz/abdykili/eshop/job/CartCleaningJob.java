package cz.abdykili.eshop.job;

import cz.abdykili.eshop.domain.CartEntity;
import cz.abdykili.eshop.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class CartCleaningJob {

    @Autowired
    private CartRepository cartRepository;

    @Scheduled(cron = "0 */10 * ? * *")
    public void cleanCarts(){
        System.out.println("check");
        List<CartEntity> all = cartRepository.findAll();
        for(CartEntity cart: all){

            LocalDateTime modifiedAt = cart.getModifiedAt();
            LocalDateTime boundaryTime = null;
            if(modifiedAt != null){
               boundaryTime = modifiedAt.plusMinutes(1L);
            }
            if(boundaryTime != null && LocalDateTime.now().isAfter(boundaryTime)){
                cartRepository.deleteById(cart.getId());
                log.info("Cart with id {} deleted, because was unused", cart.getId());
            }
        }
    }
}

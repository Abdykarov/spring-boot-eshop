package cz.abdykili.eshop.service;

import cz.abdykili.eshop.service.impl.ProductServiceImpl;
import org.apache.catalina.Server;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Server.class)
public class ProductServiceProcessTest implements WithAssertions {

    @Autowired
    private final ProductServiceImpl productService;

    public ProductServiceProcessTest(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Test
    public void saveSeveralTimesTest(){
        //arrange
        int supposedSize = 2;

        //act
        int actualFilmsCount = productService.findAll().size();
        productService.saveSeveralTimes();

        //assert
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(supposedSize).isEqualTo(actualFilmsCount);
        });

    }
}

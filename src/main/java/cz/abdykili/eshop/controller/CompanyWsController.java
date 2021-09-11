package cz.abdykili.eshop.controller;

import cz.abdykili.eshop.model.CompanyResponseDto;
import cz.abdykili.eshop.ws.AresClient;
import cz.abdykili.eshop.ws.AresClientImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/company")
@RestController
@RequiredArgsConstructor
public class CompanyWsController {

    private final AresClient aresClient;

    @GetMapping("{vatNumber}")
    public CompanyResponseDto getCompanyInfo(@PathVariable String vatNumber){
        return aresClient.getCompanyInfo(vatNumber);
    }

}


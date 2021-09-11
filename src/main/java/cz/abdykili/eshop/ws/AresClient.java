package cz.abdykili.eshop.ws;

import cz.abdykili.eshop.model.CompanyResponseDto;

public interface AresClient {
    CompanyResponseDto getCompanyInfo(String ico);
}

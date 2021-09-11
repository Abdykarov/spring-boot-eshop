package cz.abdykili.eshop.ws;

import cz.abdykili.eshop.model.CompanyResponseDto;
import cz.ares.request.AresDotazTyp;
import cz.ares.request.AresDotazy;
import cz.ares.request.Dotaz;
import cz.ares.response.AresOdpovedi;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class AresClientImpl extends WebServiceGatewaySupport implements AresClient {
    @Override
    public CompanyResponseDto getCompanyInfo(String ico) {
        final AresDotazy aresDotazy = new AresDotazy();
        aresDotazy.setDotazPocet(1);
        aresDotazy.setDotazTyp(AresDotazTyp.VYPIS_RZP);

        final Dotaz dotaz = new Dotaz();
        dotaz.setICO(ico);
        dotaz.setPomocneID(1);
        aresDotazy.getDotaz().add(dotaz);

        final AresOdpovedi response = (AresOdpovedi) getWebServiceTemplate()
                .marshalSendAndReceive(aresDotazy);

        String city = response.getOdpoved().get(0).getVypisRZP().get(0).getAdresy().getA().get(0).getN();
        String street = response.getOdpoved().get(0).getVypisRZP().get(0).getAdresy().getA().get(0).getNU();
        String psc = response.getOdpoved().get(0).getVypisRZP().get(0).getAdresy().getA().get(0).getPSC();

        return new CompanyResponseDto()
                .setCompanyName(response.getOdpoved().get(0).getVypisRZP().get(0).getZAU().getOF())
                .setCompanyAddress(String.format("%s %s, %s",street,psc,city));
    }
}

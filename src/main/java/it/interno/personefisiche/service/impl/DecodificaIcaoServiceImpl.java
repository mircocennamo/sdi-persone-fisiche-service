package it.interno.personefisiche.service.impl;

import it.interno.personefisiche.client.TabelleComuniClient;
import it.interno.personefisiche.dto.ResponseDto;
import it.interno.personefisiche.service.DecodificaIcaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DecodificaIcaoServiceImpl implements DecodificaIcaoService {

    @Autowired
    private TabelleComuniClient tabelleComuniClient ;

    @Override
    public String traslittera(String valore) {
        if(Objects.isNull(valore))
            return null;
        ResponseEntity<ResponseDto<String>> responseEntity = tabelleComuniClient.traslittera(valore);
        return responseEntity.getBody().getBody();
    }

}

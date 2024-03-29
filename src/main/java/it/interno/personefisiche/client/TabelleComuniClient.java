package it.interno.personefisiche.client;

import it.interno.personefisiche.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@FeignClient(name = "tabelle-comuni-service", path = "/tabelle-comuni")
public interface TabelleComuniClient {

    @GetMapping(path = "/traslittera")
    ResponseEntity<ResponseDto<String>> traslittera(
            @RequestParam @NotNull(message = "Il campo {errore.campo.obbligatorio}") String valore);

    @GetMapping(path = "/verifica-traslitterato")
    ResponseEntity<ResponseDto<List<String>>> verificaTraslitterato(
            @RequestParam @NotNull(message = "Il campo {errore.campo.obbligatorio}") String valore);

}

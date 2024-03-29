package it.interno.personefisiche.client;

import it.interno.personefisiche.dto.LuogoDto;
import it.interno.personefisiche.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@FeignClient(name = "luoghi-service", path = "/luoghi")
public interface LuoghiClient {

    @GetMapping(path = "/{codiceLuogo}/")
    ResponseEntity<ResponseDto<LuogoDto>> getLuogoByCodiceLuogo(@PathVariable Integer codiceLuogo,
                                                                @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") @NotNull(message = "Il campo 'Data Riferimento' è obbligatorio") LocalDate dataRif);
}

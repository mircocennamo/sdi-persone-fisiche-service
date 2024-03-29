package it.interno.personefisiche.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.personefisiche.dto.PersonaFisicaCFDto;
import it.interno.personefisiche.dto.PersonaFisicaDto;
import it.interno.personefisiche.dto.ResponseDto;
import it.interno.personefisiche.service.CodiceFiscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/codice-fiscale")
public class CodiceFiscaleController {

    @Autowired
    private CodiceFiscaleService codiceFiscaleService;

    @Operation(description = "API per calcolare il codice fiscale ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Codice fiscale generato correttamente"),
            @ApiResponse(responseCode = "400", description = "Request formattata in maniera errata"),
            @ApiResponse(responseCode = "500", description = "Errore generico nell'applicazione Persona Fisica")
    })
    @PostMapping(path = "/calcolatore")
    public ResponseEntity<ResponseDto<String>> calcolaCodiceFiscale(@RequestBody @Validated PersonaFisicaCFDto personaFisicaCFDto) {
        String codiceFiscale = codiceFiscaleService.calcolaCodiceFiscale(personaFisicaCFDto);
        return ResponseEntity.ok(ResponseDto.<String>builder().code(200).body(codiceFiscale).build());
    }

    @Operation(description = "API per verificare il codice fiscale ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Codice fiscale verifciato correttamente"),
            @ApiResponse(responseCode = "400", description = "Request formattata in maniera errata"),
            @ApiResponse(responseCode = "500", description = "Errore generico nell'applicazione Persona Fisica")
    })
    @PostMapping(path = "/verifica")
    public ResponseEntity<ResponseDto<Boolean>> verificaCodiceFiscale(@RequestBody PersonaFisicaDto personaFisicaDto) {
        boolean codiceFiscale = codiceFiscaleService.controlloCodiceFiscale(personaFisicaDto);
        return ResponseEntity.ok(ResponseDto.<Boolean>builder().code(200).body(codiceFiscale).build());
    }
}

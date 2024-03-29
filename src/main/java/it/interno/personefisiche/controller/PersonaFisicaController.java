package it.interno.personefisiche.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.personefisiche.dto.PersonaFisicaDto;
import it.interno.personefisiche.dto.ResponseDto;
import it.interno.personefisiche.service.DPersonaFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDate;

@RestController
public class PersonaFisicaController {

    @Autowired
    private DPersonaFisicaService dPersonaFisicaService;

    @Operation(description = "API per prendere la persona fisica dato l'id")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })
    @GetMapping("/{idPersonaFisica}")
    public ResponseEntity<ResponseDto<PersonaFisicaDto>> getPersonaById(@PathVariable Timestamp idPersonaFisica) {

        PersonaFisicaDto personaFisica = dPersonaFisicaService.getPersonaFisicaById(idPersonaFisica);
        ResponseDto<PersonaFisicaDto> responseDto = ResponseDto.<PersonaFisicaDto>builder().code(HttpStatus.OK.value()).body(personaFisica).build();

        return ResponseEntity.ok(responseDto);
    }

    @Operation(description = "API per ricercare la persona fisica in base ai parametri")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })

    @GetMapping
    public  ResponseEntity<ResponseDto<PersonaFisicaDto>> ricercaPersona(@RequestParam(required = false) String nome,@RequestParam String cognome,
                                                                         @RequestParam Integer codiceLuogoNascita, @RequestParam String indicatoreDataNascita,
                                                                         @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataNascita) {

        PersonaFisicaDto personaFisica = dPersonaFisicaService.ricercaPersona(nome, cognome, codiceLuogoNascita, indicatoreDataNascita, dataNascita);
        ResponseDto<PersonaFisicaDto> responseDto = ResponseDto.<PersonaFisicaDto>builder().code(HttpStatus.OK.value()).body(personaFisica).build();
        return ResponseEntity.ok(responseDto);
    }
}

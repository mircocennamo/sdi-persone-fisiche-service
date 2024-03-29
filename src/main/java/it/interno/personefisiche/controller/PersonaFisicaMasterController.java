package it.interno.personefisiche.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import it.interno.personefisiche.dto.AnagraficaPersonaFisicaDto;
import it.interno.personefisiche.dto.PersonaFisicaMasterDto;
import it.interno.personefisiche.dto.ResponseDto;
import it.interno.personefisiche.service.PersonaFisicaMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class PersonaFisicaMasterController {

    @Autowired
    PersonaFisicaMasterService personaFisicaService ;

    @Operation(description = "API per fare la precedentazione di un soggetto persona fisica in SDI")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })
    @PostMapping(path = "/precedentazione")
    public ResponseEntity<ResponseDto<AnagraficaPersonaFisicaDto>> precedentazionePersonaFisica(@RequestBody AnagraficaPersonaFisicaDto anagraficaPersonaFisicaDto) {

        AnagraficaPersonaFisicaDto personaFisicaDto = personaFisicaService.precedentazioneSDI(anagraficaPersonaFisicaDto);
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.OK.value()).body(personaFisicaDto).build() ;
        return ResponseEntity.ok(responseDto) ;
    }

    @Operation(description = "API per verificare presenza di un soggetto persona fisica in SDI")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Dati recuperati correttamente"),
            @ApiResponse(responseCode = "404", description = "Url non definita"),
            @ApiResponse(responseCode = "500", description = "Errore del sistema") })
    @GetMapping(path = "/verifica-esistenza")
    public ResponseEntity<ResponseDto<PersonaFisicaMasterDto>> getPersonaFisica(@RequestParam String cognome,
                                                                                @RequestParam(required = false) String nome,
                                                                                @RequestParam String indicatoreDataNascita,
                                                                                @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam LocalDate dataNascita,
                                                                                @RequestParam Long codiceLuogoNascita) {
        AnagraficaPersonaFisicaDto anagraficaPersonaFisicaDto = new AnagraficaPersonaFisicaDto(cognome, nome, indicatoreDataNascita, dataNascita, codiceLuogoNascita);
        PersonaFisicaMasterDto personaFisicaDto = personaFisicaService.getPersonaFisica(anagraficaPersonaFisicaDto);
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.OK.value()).body(personaFisicaDto).build() ;
        return ResponseEntity.ok(responseDto) ;
    }

}

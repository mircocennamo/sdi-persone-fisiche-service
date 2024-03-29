package it.interno.personefisiche.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.personefisiche.serializer.LocalDateDeserializer;
import it.interno.personefisiche.serializer.LocalDateSerializer;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaFisicaMasterDto {

    String idPersonaFisica ;
    String tsInserimento ;
    String codiceFiscale ;
    String cognome ;
    String nome ;
    String inCertificazioneDataNascita ;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate dataNascita ;
    String dataNascitaVis ;
    Integer codiceLuogoNascita ;
    String descrizioneLuogoNascita ;
    String siglaProvinciaNascita ;
    String idUtenteInserimento ;
    String idUfficioInserimento ;
    String tsCancellazione ;
    String idUtenteCancellaizone ;
    String idUfficioCancellaizone ;
    String pesoAzioneSoggetto ;
    String positivoInterrogazioneOperativa ;

}

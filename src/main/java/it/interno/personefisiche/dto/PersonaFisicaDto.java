package it.interno.personefisiche.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.personefisiche.serializer.LocalDateDeserializer;
import it.interno.personefisiche.serializer.LocalDateSerializer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonaFisicaDto {

    String idPersonaFisica;
    String tsInserimento;
    String codiceFiscale;
    String cognome;
    String nome;
    String sesso;
    Integer codiceLuogoNascita;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate dataNascita;
    String inCertificazioneDataNascita;
    String idUtenteInserimento;
    String idUfficioInserimento;
    String tsCancellazione;
    String idUtenteCancellazione;
    String idUfficioCancellazione;
}

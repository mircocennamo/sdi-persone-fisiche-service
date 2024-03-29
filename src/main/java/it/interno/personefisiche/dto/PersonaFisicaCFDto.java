package it.interno.personefisiche.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import it.interno.personefisiche.serializer.LocalDateDeserializer;
import it.interno.personefisiche.serializer.LocalDateSerializer;
import it.interno.personefisiche.validator.annotations.FieldSize;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PersonaFisicaCFDto {

    static final String REGEX_VALID_COGN = "^[^\\!-\\@^\\[-\\_^\\{-\\~^\\¡-\\¿\\s]+[^\\!-\\&^\\(-\\,^\\.-\\@^\\[-\\_^\\{-\\~^\\¡-\\¿\\t]*$";
    static final String REGEX_VALID_NOME = "^$|^[^\\!-\\@^\\[-\\_^\\{-\\~^\\¡-\\¿\\s]+[^\\!-\\&^\\(-\\,^\\.-\\@^\\[-\\_^\\{-\\~^\\¡-\\¿\\t]*$";

    @FieldSize(max = 30, message = "Il campo 'Nome' {errore.campo.troppolungo}")
    @Pattern(regexp = REGEX_VALID_NOME, message = "Il campo 'Nome' {errore.campo.caratteriNonValidi}")
    String nome;

    @NotNull(message = "Il campo 'Cognome' {errore.campo.obbligatorio}")
    @FieldSize(max = 50, message = "Il campo 'Cognome' {errore.campo.troppolungo}")
    @Pattern(regexp = REGEX_VALID_COGN, message = "Il campo 'Cognome' {errore.campo.caratteriNonValidi}")
    String cognome;

    @NotNull(message = "Il campo 'Data di Nascita' {errore.campo.obbligatorio}")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate dataNascita;

    @Pattern(regexp = "^[MF]", message = "Il campo 'Sesso' {errore.campo.caratteriNonValidi}")
    String sesso;

    @NotNull(message = "Il campo 'Codice Luogo di Nascita' {errore.campo.obbligatorio}")
    @Size(max = 9, message = "Il campo 'Codice Luogo di Nascita' {errore.campo.troppolungo}")
    String codiceLuogoNascita;
}

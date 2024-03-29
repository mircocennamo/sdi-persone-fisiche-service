package it.interno.personefisiche.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LuogoDto {

    Integer codiceLuogo;
    LocalDate dataInizioValidita;
    String inLuogo;
    String descrizioneLuogo;
    LocalDate dataFineValidita;
    String codiceRegione;
    String codiceProvincia;
    String codiceComune;
    String codiceCatastale;
    String siglaProvincia;
    Timestamp tsInserimento;
    Timestamp tsCancellazione;
    Integer codiceLuogoArea;
    Integer codiceLuogoZona;
    Integer codiceLuogoRiferimento ;
    String siglaLuogoAsf ;
    String codiceIstat;
    String codiceArea;
    String codiceContinente;
    String denominazioneLuogoLinguaItaliaNomaiuscolo;
    String denominazioneLuogoLinguaItalianoMinuscolo;
    String denominazioneLuogoLinguaIngleseoMaiuscolo;
    String denominazioneLuogoLinguaIngleseMinuscolo;
    String codiceMinisteroInterno;
    String codiceUnsdm49;
    String codiceISO3166Caratteri2;
    String codiceISO3166Caratteri3;
    String codiceIstatStatoPadreCaratteri3;
    String codiceISO3166StatoPadreCaratteri3;

}

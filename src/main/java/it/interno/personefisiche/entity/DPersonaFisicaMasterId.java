package it.interno.personefisiche.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Embeddable
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DPersonaFisicaMasterId implements Serializable {

    private static final long serialVersionUID = 1135359123565334728L;
    @Column(name = "IDPERSONAFISICA", nullable = false, scale = 6)
    Timestamp idPersonaFisica ;

    @Column(name = "TSINSERIMENTO", nullable = false, scale = 6)
    Timestamp tsInserimento ;

    @Column(name = "COGNOME", nullable = false)
    String cognome ;

    @Column(name = "NOME", nullable = false)
    String nome ;

    @Column(name = "INCERTIFICAZIONEDATANASCITA", nullable = false)
    String inCertificazioneDataNascita ;

    @Column(name = "DATANASCITA", nullable = false)
    LocalDate dataNascita ;

    @Column(name = "CODICELUOGONASCITA", nullable = false)
    Integer codiceLuogoNascita ;

}

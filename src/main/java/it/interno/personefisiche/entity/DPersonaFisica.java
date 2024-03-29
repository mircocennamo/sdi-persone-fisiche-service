package it.interno.personefisiche.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "DPERSONAFISICA", schema = "DBANAGPF")
@Data
@IdClass(DPersonaFisicaKey.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DPersonaFisica implements Serializable {
    static final long serialVersionUID = 2842663492826779828L;
    @Id
    @Column(name = "IDPERSONAFISICA")
    Timestamp idPersonaFisica;
    @Id
    @Column(name = "TSINSERIMENTO")
    Timestamp tsInserimento;

    @Column(name = "CODICEFISCALE")
    String codiceFiscale;
    @Column(name = "COGNOME")
    String cognome;
    @Column(name = "NOME")
    String nome;
    @Column(name = "CODICELUOGONASCITA")
    Integer codiceLuogoNascita;
    @Column(name = "DATANASCITA")
    LocalDate dataNascita;
    @Column(name = "INCERTIFICAZIONEDATANASCITA")
    String inCertificazioneDataNascita;
    @Column(name = "IDUTENTEINSERIMENTO")
    String idUtenteInserimento;
    @Column(name = "IDUFFICIOINSERIMENTO")
    String idUfficioInserimento;
    @Column(name = "TSCANCELLAZIONE")
    Timestamp tsCancellazione;
    @Column(name = "IDUTENTECANCELLAZIONE")
    String idUtenteCancellazione;
    @Column(name = "IDUFFICIOCANCELLAZIONE")
    String idUfficioCancellazione;

}

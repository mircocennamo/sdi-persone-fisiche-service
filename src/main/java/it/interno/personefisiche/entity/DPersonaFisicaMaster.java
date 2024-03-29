package it.interno.personefisiche.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "DPERSONAFISICAMASTER", schema = "DBANAGPF")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DPersonaFisicaMaster implements Serializable {

    @EmbeddedId
    DPersonaFisicaMasterId dpersonaFisicaMasterId ;

    @Column(name = "CODICEFISCALE", nullable = false)
    String codiceFiscale ;

    @Column(name = "DATANASCITAVIS", nullable = false)
    String dataNascitaVis ;

    @Column(name = "DESCRIZIONELUOGONASCITA", nullable = false)
    String descrizioneLuogoNascita ;

    @Column(name = "SIGLAPROVINCIANASCITA", nullable = false)
    String siglaProvinciaNascita ;

    @Column(name = "IDUTENTEINSERIMENTO", nullable = false)
    String idUtenteInserimento ;

    @Column(name = "IDUFFICIOINSERIMENTO", nullable = false)
    String idUfficioInserimento ;

    @Column(name = "TSCANCELLAZIONE", scale = 6)
    Timestamp tsCancellazione ;

    @Column(name = "IDUTENTECANCELLAZIONE")
    String idUtenteCancellaizone ;

    @Column(name = "IDUFFICIOCANCELLAZIONE")
    String idUfficioCancellaizone ;

    @Column(name = "PESOAZIONESOGGETTO", nullable = false)
    String pesoAzioneSoggetto ;

    @Column(name = "POSITIVOINTERROGAZIONEOPERATIVA", nullable = false)
    String positivoInterrogazioneOperativa ;

}

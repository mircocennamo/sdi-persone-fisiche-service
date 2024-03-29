package it.interno.personefisiche.repository;

import it.interno.personefisiche.entity.DPersonaFisica;
import it.interno.personefisiche.entity.DPersonaFisicaKey;
import it.interno.personefisiche.entity.DPersonaFisicaMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface DPersonaFisicaRepository extends JpaRepository<DPersonaFisica, DPersonaFisicaKey>, JpaSpecificationExecutor<DPersonaFisica> {

    DPersonaFisica findDPersonaFisicaByIdPersonaFisicaAndTsCancellazioneIsNull(Timestamp idPersonaFisica);

    DPersonaFisica findDPersonaFisicaByNomeAndCognomeAndCodiceLuogoNascitaAndDataNascitaAndInCertificazioneDataNascitaAndTsCancellazioneIsNull(
            String nome, String cognome, Integer codiceLuogoNascita, LocalDate dataNascita, String inCertificazioneDataNascita);
}

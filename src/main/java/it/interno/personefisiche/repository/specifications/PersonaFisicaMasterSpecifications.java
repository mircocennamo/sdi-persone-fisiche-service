package it.interno.personefisiche.repository.specifications;

import it.interno.personefisiche.entity.DPersonaFisicaMaster;
import it.interno.personefisiche.entity.DPersonaFisicaMaster_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonaFisicaMasterSpecifications {

    private static final String DPersonaFisicaMasterId_ = "dpersonaFisicaMasterId" ;

    public static Specification<DPersonaFisicaMaster> cognomeEqual(String cognome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.trim(root.get(DPersonaFisicaMasterId_).get("cognome")), cognome);
    }

    public static Specification<DPersonaFisicaMaster> nomeEqual(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisicaMasterId_).get("nome"), nome);
    }

    public static Specification<DPersonaFisicaMaster> indicatoreCertificazioneDataNascitaEqual(String inCertDNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisicaMasterId_).get("inCertificazioneDataNascita"), inCertDNas);
    }

    public static Specification<DPersonaFisicaMaster> dataNascitaEqual(LocalDate dNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisicaMasterId_).get("dataNascita"), dNas);
    }

    public static Specification<DPersonaFisicaMaster> codiceLuogoNascitaEqual(Long cLuoNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisicaMasterId_).get("codiceLuogoNascita"), cLuoNas);
    }

    public static Specification<DPersonaFisicaMaster> tsCancellazioneIsNullPF() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(DPersonaFisicaMaster_.TS_CANCELLAZIONE)));
    }

    public static Specification<DPersonaFisicaMaster> pesoGreatherThanBlank() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get(DPersonaFisicaMaster_.PESO_AZIONE_SOGGETTO), "  ");
    }

    public static Specification<DPersonaFisicaMaster> positivoInterrogazioniOperativeEqual() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisicaMaster_.POSITIVO_INTERROGAZIONE_OPERATIVA), "S");
    }

}

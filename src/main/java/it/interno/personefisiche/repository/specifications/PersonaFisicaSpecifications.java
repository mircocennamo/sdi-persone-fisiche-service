package it.interno.personefisiche.repository.specifications;

import it.interno.personefisiche.entity.DPersonaFisica;
import it.interno.personefisiche.entity.DPersonaFisica_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class PersonaFisicaSpecifications {

    public static Specification<DPersonaFisica> cognomeEqual(String cognome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(criteriaBuilder.trim(root.get(DPersonaFisica_.COGNOME)), cognome);
    }

    public static Specification<DPersonaFisica> nomeEqual(String nome) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisica_.NOME), nome);
    }

    public static Specification<DPersonaFisica> indicatoreCertificazioneDataNascitaEqual(String inCertDNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisica_.IN_CERTIFICAZIONE_DATA_NASCITA), inCertDNas);
    }

    public static Specification<DPersonaFisica> dataNascitaEqual(LocalDate dNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisica_.DATA_NASCITA), dNas);
    }

    public static Specification<DPersonaFisica> codiceLuogoNascitaEqual(Integer cLuoNas) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(DPersonaFisica_.CODICE_LUOGO_NASCITA), cLuoNas);
    }

    public static Specification<DPersonaFisica> tsCancellazioneIsNullPF() {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isNull(root.get(DPersonaFisica_.TS_CANCELLAZIONE)));
    }

}

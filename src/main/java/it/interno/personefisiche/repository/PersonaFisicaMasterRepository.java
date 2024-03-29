package it.interno.personefisiche.repository;

import it.interno.personefisiche.entity.DPersonaFisicaMaster;
import it.interno.personefisiche.entity.DPersonaFisicaMasterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonaFisicaMasterRepository extends JpaRepository<DPersonaFisicaMaster, DPersonaFisicaMasterId>, JpaSpecificationExecutor<DPersonaFisicaMaster> {
}

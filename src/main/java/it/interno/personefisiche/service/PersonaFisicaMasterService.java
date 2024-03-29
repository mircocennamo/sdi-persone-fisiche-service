package it.interno.personefisiche.service;

import it.interno.personefisiche.dto.AnagraficaPersonaFisicaDto;
import it.interno.personefisiche.dto.PersonaFisicaMasterDto;

public interface PersonaFisicaMasterService {

    AnagraficaPersonaFisicaDto precedentazioneSDI(AnagraficaPersonaFisicaDto persomFisicaDto) ;

    PersonaFisicaMasterDto getPersonaFisica(AnagraficaPersonaFisicaDto persomFisicaDto) ;

}

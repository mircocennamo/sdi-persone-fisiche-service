package it.interno.personefisiche.service;

import it.interno.personefisiche.dto.PersonaFisicaCFDto;
import it.interno.personefisiche.dto.PersonaFisicaDto;

public interface CodiceFiscaleService {

    String calcolaCodiceFiscale(PersonaFisicaCFDto personaFisicaCFDto);

    boolean controlloCodiceFiscale(PersonaFisicaDto personaFisicaDto) ;
}

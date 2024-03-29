package it.interno.personefisiche.service;

import it.interno.personefisiche.dto.PersonaFisicaDto;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface DPersonaFisicaService {

    PersonaFisicaDto getPersonaFisicaById(Timestamp idPersonaFisica);

    PersonaFisicaDto ricercaPersona(String nome, String cognome, Integer codiceLuogo, String indicatoreDataNascita, LocalDate dataNascita);
}

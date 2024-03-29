package it.interno.personefisiche.service.impl;

import it.interno.personefisiche.dto.PersonaFisicaDto;
import it.interno.personefisiche.entity.DPersonaFisica;
import it.interno.personefisiche.mapper.PersonaFisicaMapper;
import it.interno.personefisiche.repository.DPersonaFisicaRepository;
import it.interno.personefisiche.service.DPersonaFisicaService;
import it.interno.personefisiche.service.DecodificaIcaoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static it.interno.personefisiche.repository.specifications.PersonaFisicaSpecifications.*;

@Service
public class DPersonaFisicaServiceImpl implements DPersonaFisicaService {

    @Autowired
    private DPersonaFisicaRepository dPersonaFisicaRepository;
    @Autowired
    private PersonaFisicaMapper personaFisicaMapper;
    @Autowired
    private DecodificaIcaoService decodificaIcaoService ;

    @Override
    public PersonaFisicaDto getPersonaFisicaById(Timestamp idPersonaFisica) {
        DPersonaFisica dPersonaFisica = dPersonaFisicaRepository.findDPersonaFisicaByIdPersonaFisicaAndTsCancellazioneIsNull(idPersonaFisica);
        return personaFisicaMapper.toDto(dPersonaFisica);
        }

    @Override
    public PersonaFisicaDto ricercaPersona(String nome, String cognome, Integer codiceLuogo, String indicatoreDataNascita, LocalDate dataNascita) {

        if(StringUtils.isBlank(nome)) nome = " ";

           DPersonaFisica dPersonaFisica = dPersonaFisicaRepository.findDPersonaFisicaByNomeAndCognomeAndCodiceLuogoNascitaAndDataNascitaAndInCertificazioneDataNascitaAndTsCancellazioneIsNull(
                        decodificaIcaoService.traslittera(nome), decodificaIcaoService.traslittera(cognome),
                        codiceLuogo, dataNascita, indicatoreDataNascita);

        return personaFisicaMapper.toDto(dPersonaFisica);
    }

}

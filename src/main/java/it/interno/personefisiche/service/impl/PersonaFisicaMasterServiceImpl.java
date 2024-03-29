package it.interno.personefisiche.service.impl;

import it.interno.personefisiche.dto.AnagraficaPersonaFisicaDto;
import it.interno.personefisiche.dto.PersonaFisicaMasterDto;
import it.interno.personefisiche.entity.DPersonaFisicaMaster;
import it.interno.personefisiche.entity.DPersonaFisicaMasterId;
import it.interno.personefisiche.mapper.PersonaFisicaMasterMapper;
import it.interno.personefisiche.repository.PersonaFisicaMasterRepository;
import it.interno.personefisiche.service.DecodificaIcaoService;
import it.interno.personefisiche.service.PersonaFisicaMasterService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static it.interno.personefisiche.repository.specifications.PersonaFisicaMasterSpecifications.*;

@Service
public class PersonaFisicaMasterServiceImpl implements PersonaFisicaMasterService {

    @Autowired
    PersonaFisicaMasterRepository personaFisicaMasterRepository ;

    @Autowired
    PersonaFisicaMasterMapper personaFisicaMasterMapper ;

    @Autowired
    DecodificaIcaoService decodificaIcaoService ;

    @Override
    public AnagraficaPersonaFisicaDto precedentazioneSDI(AnagraficaPersonaFisicaDto personaFisicaDto) {

        if(StringUtils.isBlank(personaFisicaDto.getNome())) personaFisicaDto.setNome(" ");

        Optional<DPersonaFisicaMaster> personaFisica = personaFisicaMasterRepository.findOne(
                cognomeEqual(decodificaIcaoService.traslittera(personaFisicaDto.getCognome()))
                .and(nomeEqual(decodificaIcaoService.traslittera(personaFisicaDto.getNome())))
                .and((indicatoreCertificazioneDataNascitaEqual(personaFisicaDto.getInCertificazioneDataNascita())))
                .and(dataNascitaEqual(personaFisicaDto.getDataNascita()))
                .and(codiceLuogoNascitaEqual(personaFisicaDto.getCodiceLuogoNascita()))
                .and(positivoInterrogazioniOperativeEqual())
                .and(tsCancellazioneIsNullPF()));
        AnagraficaPersonaFisicaDto pfDto = null ;
        if(personaFisica.isPresent()) {
            pfDto = new AnagraficaPersonaFisicaDto() ;

            DPersonaFisicaMasterId pf = personaFisica.get().getDpersonaFisicaMasterId();
            BeanUtils.copyProperties(pf, pfDto);
            pfDto.setDescrizioneLuogoNascita(personaFisica.get().getDescrizioneLuogoNascita());
            pfDto.setSiglaProvinciaNascita(personaFisica.get().getSiglaProvinciaNascita());
        }

        return pfDto ;
    }

    @Override
    public PersonaFisicaMasterDto getPersonaFisica(AnagraficaPersonaFisicaDto personaFisicaDto) {

        if(StringUtils.isBlank(personaFisicaDto.getNome())) personaFisicaDto.setNome(" ");

        Optional<DPersonaFisicaMaster> personaFisica = personaFisicaMasterRepository.findOne(
                cognomeEqual(decodificaIcaoService.traslittera(personaFisicaDto.getCognome()))
                .and(nomeEqual(decodificaIcaoService.traslittera(personaFisicaDto.getNome())))
                .and((indicatoreCertificazioneDataNascitaEqual(personaFisicaDto.getInCertificazioneDataNascita())))
                .and(dataNascitaEqual(personaFisicaDto.getDataNascita()))
                .and(codiceLuogoNascitaEqual(personaFisicaDto.getCodiceLuogoNascita()))
                .and(tsCancellazioneIsNullPF()));

        AtomicReference<PersonaFisicaMasterDto> personaFisicaMasterDto = new AtomicReference<>() ;
        personaFisica.ifPresent(dPersonaFisicaMaster -> {
            personaFisicaMasterDto.set(personaFisicaMasterMapper.dPersonaFisicaMasterToPersonaFisicaMasterDto(dPersonaFisicaMaster));
        });

        return personaFisicaMasterDto.get();
    }
}

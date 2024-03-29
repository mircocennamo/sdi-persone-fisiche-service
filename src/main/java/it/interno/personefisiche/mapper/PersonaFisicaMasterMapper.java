package it.interno.personefisiche.mapper;

import it.interno.personefisiche.dto.PersonaFisicaMasterDto;
import it.interno.personefisiche.entity.DPersonaFisicaMaster;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonaFisicaMasterMapper {

    @Mappings({
            @Mapping( target = "dpersonaFisicaMasterId.idPersonaFisica", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaMasterDto.getIdPersonaFisica()))"),
            @Mapping( target = "dpersonaFisicaMasterId.tsInserimento", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaMasterDto.getTsInserimento()))"),
            @Mapping( source = "personaFisicaMasterDto.cognome", target = "dpersonaFisicaMasterId.cognome"),
            @Mapping( source = "personaFisicaMasterDto.nome", target = "dpersonaFisicaMasterId.nome"),
            @Mapping( source = "personaFisicaMasterDto.dataNascita", target = "dpersonaFisicaMasterId.dataNascita"),
            @Mapping( source = "personaFisicaMasterDto.inCertificazioneDataNascita", target = "dpersonaFisicaMasterId.inCertificazioneDataNascita"),
            @Mapping( source = "personaFisicaMasterDto.codiceLuogoNascita", target = "dpersonaFisicaMasterId.codiceLuogoNascita"),
            @Mapping( target = "tsCancellazione", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaMasterDto.getTsCancellazione()))")
    })
    DPersonaFisicaMaster personaFisicaMasterDtoToDPersonaFisicaMaster(PersonaFisicaMasterDto personaFisicaMasterDto) ;

    @InheritInverseConfiguration()
    @Mappings({
            @Mapping(target = "idPersonaFisica", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisicaMaster.getDpersonaFisicaMasterId().getIdPersonaFisica()))"),
            @Mapping(target = "tsInserimento", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisicaMaster.getDpersonaFisicaMasterId().getTsInserimento()))"),
            @Mapping(target = "tsCancellazione", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisicaMaster.getTsCancellazione()))")
    })
    PersonaFisicaMasterDto dPersonaFisicaMasterToPersonaFisicaMasterDto(DPersonaFisicaMaster dPersonaFisicaMaster) ;
    
}

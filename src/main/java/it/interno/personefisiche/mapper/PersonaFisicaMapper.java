package it.interno.personefisiche.mapper;

import it.interno.personefisiche.dto.PersonaFisicaDto;
import it.interno.personefisiche.entity.DPersonaFisica;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonaFisicaMapper {

    @Mappings({
            @Mapping( target = "idPersonaFisica", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaDto.getIdPersonaFisica()))"),
            @Mapping( target = "tsInserimento", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaDto.getTsInserimento()))"),
            @Mapping( target = "tsCancellazione", expression = "java(it.interno.personefisiche.util.Utils.convertStringToTimestampByFormat(personaFisicaDto.getTsCancellazione()))")
    })
    DPersonaFisica toEntity(PersonaFisicaDto personaFisicaDto) ;

    @InheritInverseConfiguration()
    @Mappings({
            @Mapping(target = "idPersonaFisica", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisica.getIdPersonaFisica()))"),
            @Mapping(target = "tsInserimento", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisica.getTsInserimento()))"),
            @Mapping(target = "tsCancellazione", expression = "java(it.interno.personefisiche.util.Utils.convertTimestampToStringByFormat(dPersonaFisica.getTsCancellazione()))")
    })
    PersonaFisicaDto toDto(DPersonaFisica dPersonaFisica) ;
}

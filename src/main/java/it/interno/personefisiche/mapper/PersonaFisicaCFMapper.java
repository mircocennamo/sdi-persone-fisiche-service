package it.interno.personefisiche.mapper;

import it.interno.personefisiche.dto.PersonaFisicaCFDto;
import it.interno.personefisiche.dto.PersonaFisicaDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PersonaFisicaCFMapper {

    PersonaFisicaCFDto personaFisicaDtoToPersonaFisicaCFDto(PersonaFisicaDto personaFisicaDto);

    @InheritInverseConfiguration
    PersonaFisicaDto personaFisicaCFDtoToPersonaFisicaDto(PersonaFisicaCFDto personaFisica);
}

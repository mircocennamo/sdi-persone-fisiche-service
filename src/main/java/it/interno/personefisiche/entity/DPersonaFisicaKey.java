package it.interno.personefisiche.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DPersonaFisicaKey implements Serializable {
    private Timestamp idPersonaFisica;
    private Timestamp tsInserimento;
}

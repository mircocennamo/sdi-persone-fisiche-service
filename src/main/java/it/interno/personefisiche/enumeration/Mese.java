package it.interno.personefisiche.enumeration;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Mese {

    GENNAIO("01","A"),
    FEBBRAIO("02","B"),
    MARZO("03","C"),
    APRILE("04","D"),
    MAGGIO("05","E"),
    GIUGNO("06","H"),
    LUGLIO("07","L"),
    AGOSTO("08","M"),
    SETTEMBRE("09","P"),
    OTTOBRE("10","R"),
    NOVEMBRE("11","S"),
    DICEMBRE("12","T");

    private final String meseCodiceFiscale;
    private final String codiceMese;

    Mese(String codiceMese, String meseCodiceFiscale) {
        this.codiceMese = codiceMese;
        this.meseCodiceFiscale = meseCodiceFiscale;
    }

    public static String getMeseCodiceFiscaleByCodiceMese(String codiceMese) {
       return Arrays.stream(Mese.values())
                .filter(mese -> mese.codiceMese.equals(codiceMese))
                .map(Mese::getMeseCodiceFiscale)
                .collect(Collectors.joining());
    }

    public String getMeseCodiceFiscale() {
        return this.meseCodiceFiscale;
    }
}

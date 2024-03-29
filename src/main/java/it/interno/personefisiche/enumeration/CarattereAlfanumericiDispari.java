package it.interno.personefisiche.enumeration;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CarattereAlfanumericiDispari {

    ZERO("0",1),
    UNO("1", 0),
    DUE("2", 5),
    TRE("3", 7),
    QUATTRO("4", 9),
    CINQUE("5", 13),
    SEI("6", 15),
    SETTE("7", 17),
    OTTO("8", 19),
    NOVE("9", 21),
    A("A", 1),
    B("B", 0),
    C("C", 5),
    D("D", 7),
    E("E", 9),
    F("F", 13),
    G("G", 15),
    H("H", 17),
    I("I", 19),
    J("J", 21),
    K("K", 2),
    L("L", 4),
    M("M", 18),
    N("N", 20),
    O("O", 11),
    P("P", 3),
    Q("Q", 6),
    R("R", 8),
    S("S", 12),
    T("T", 14),
    U("U", 16),
    V("V", 10),
    W("W", 22),
    X("X", 25),
    Y("Y", 24),
    Z("Z", 23);

    private final String carattere;
    private final int valore;

    CarattereAlfanumericiDispari(String carattere, int valore) {
        this.carattere = carattere;
        this.valore = valore;
    }

    public static long getValoreByCarattere(String caratteriDispari) {

      return Arrays.stream(CarattereAlfanumericiDispari.values())
                .filter(cad -> cad.carattere.equalsIgnoreCase(caratteriDispari))
                .collect(Collectors.summarizingInt(CarattereAlfanumericiDispari::getValore))
                .getSum();
    }

    public int getValore() {
        return this.valore;
    }
}

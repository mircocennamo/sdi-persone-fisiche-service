package it.interno.personefisiche.enumeration;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum CarattereAlfanumericiPari {

    ZERO("0",0),
    UNO("1",1),
    DUE("2",2),
    TRE("3",3),
    QUATTRO("4",4),
    CINQUE("5",5),
    SEI("6",6),
    SETTE("7",7),
    OTTO("8",8),
    NOVE("9",9),
    A("A",0),
    B("B",1),
    C("C",2),
    D("D",3),
    E("E",4),
    F("F",5),
    G("G",6),
    H("H",7),
    I("I",8),
    J("J",9),
    K("K",10),
    L("L",11),
    M("M",12),
    N("N",13),
    O("O",14),
    P("P",15),
    Q("Q",16),
    R("R",17),
    S("S",18),
    T("T",19),
    U("U",20),
    V("V",21),
    W("W",22),
    X("X",23),
    Y("Y",24),
    Z("Z",25);

    private final String carattere;
    private final int valore;

    CarattereAlfanumericiPari(String carattere, int valore) {
        this.carattere = carattere;
        this.valore = valore;
    }

    public static long getValoreByCarattere(String caratteriPari) {

        return Arrays.stream(CarattereAlfanumericiPari.values())
                .filter(cad -> cad.carattere.equalsIgnoreCase(caratteriPari))
                .collect(Collectors.summarizingInt(CarattereAlfanumericiPari::getValore))
                .getSum();
    }

    public int getValore() {
        return this.valore;
    }


}

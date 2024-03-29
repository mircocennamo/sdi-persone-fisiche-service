package it.interno.personefisiche.service.impl;

import it.interno.personefisiche.client.LuoghiClient;
import it.interno.personefisiche.client.TabelleComuniClient;
import it.interno.personefisiche.dto.PersonaFisicaCFDto;
import it.interno.personefisiche.dto.PersonaFisicaDto;
import it.interno.personefisiche.enumeration.CarattereAlfanumericiDispari;
import it.interno.personefisiche.enumeration.CarattereAlfanumericiPari;
import it.interno.personefisiche.enumeration.Mese;
import it.interno.personefisiche.mapper.PersonaFisicaCFMapper;
import it.interno.personefisiche.service.CodiceFiscaleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CodiceFiscaleServiceImpl implements CodiceFiscaleService {

    @Autowired
    private TabelleComuniClient tabelleComuniClient;

    @Autowired
    private LuoghiClient luoghiClient;

    @Autowired
    private PersonaFisicaCFMapper personaFisicaCFMapper;


    @Resource
    private Map<Integer, String> carattereControlloCodiceFiscaleMap;


    @Override
    public String calcolaCodiceFiscale(PersonaFisicaCFDto personaFisicaCFDto) {

        String cognome = tabelleComuniClient.traslittera(personaFisicaCFDto.getCognome()).getBody().getBody().replace("'", "").replaceAll("\\s", "").toUpperCase();
        String nome = tabelleComuniClient.traslittera(personaFisicaCFDto.getNome()).getBody().getBody().replace("'", "").replaceAll("\\s", "").toUpperCase();
        LocalDate dataNascita = personaFisicaCFDto.getDataNascita();
        StringBuilder sb = new StringBuilder();
        StringBuilder codiceFiscale = sb
                .append(generaCognome(cognome))
                .append(generaNome(nome))
                .append(annoMeseGiorno(dataNascita, personaFisicaCFDto.getSesso()))
                .append(luoghiClient.getLuogoByCodiceLuogo(Integer.parseInt(personaFisicaCFDto.getCodiceLuogoNascita()), dataNascita).getBody().getBody().getCodiceCatastale());
        codiceFiscale.append(carattereDiControllo(codiceFiscale.toString()));

        return codiceFiscale.toString();
    }

    @Override
    public boolean controlloCodiceFiscale(PersonaFisicaDto personaFisicaDto) {

        if(StringUtils.isBlank(personaFisicaDto.getCodiceFiscale()))
            return true ;

        if(personaFisicaDto.getCognome().length() < 2 || personaFisicaDto.getNome().length() < 2)
            return false;

        if(personaFisicaDto.getCodiceFiscale().length() != 16)
            return false;

        PersonaFisicaCFDto personaFisicaCFDto = personaFisicaCFMapper.personaFisicaDtoToPersonaFisicaCFDto(personaFisicaDto);
        String codiceFiscaleCalcolato = this.calcolaCodiceFiscale(personaFisicaCFDto) ;

        if(personaFisicaDto.getCodiceFiscale().equals(codiceFiscaleCalcolato))
            return true;

        if(!personaFisicaDto.getSesso().equals(this.ricavaSessoSoggetto(personaFisicaDto.getCodiceFiscale())))
            return false;

        if(this.checkCodiceFiscaleOmocode(codiceFiscaleCalcolato, personaFisicaDto.getCodiceFiscale())) {
            if(codiceFiscaleCalcolato.substring(0,6).equals(personaFisicaDto.getCodiceFiscale().substring(0,6))) {
                return true;
            }
        }

        return false;
    }


    private String generaCognome(String cognome) {

        if(cognome.length() == 3 && vowelsCount(cognome) == 3)
            return cognome;

        if(cognome.length() < 3) {
            while (cognome.length() < 3){
                cognome += "X";
            }
            return cognome;
        }

        int consonantsCount = this.consonantsCount(cognome);
        if ( consonantsCount == 1 || consonantsCount == 2) {
            String consonants = getConsonants(cognome).limit(consonantsCount).collect(Collectors.joining());
            while (consonants.length() < 3){
                consonants += this.addVowels(cognome, consonants);
            }
            return consonants;
        }

        return getConsonants(cognome).limit(3).collect(Collectors.joining());
    }

    private String generaNome(String nome) {

        int consonantsCount = consonantsCount(nome);
        if ((consonantsCount == 1 && vowelsCount(nome) == 1) || nome.length() == 2 && vowelsCount(nome) == 2) {
            nome += "X";
            return nome;
        }

        if (consonantsCount < 3) {
            String consonants = getConsonants(nome).collect(Collectors.joining());
            while (consonants.length() < 3){
                consonants += this.addVowels(nome, consonants);
            }
            return consonants;
        }

        String collect = getConsonants(nome).collect(Collectors.joining());

        return consonantsCount > 3 ? collect.charAt(0) + "" + collect.charAt(2) + "" + collect.charAt(3) : getConsonants(nome).limit(3).collect(Collectors.joining());
    }

    private String annoMeseGiorno(LocalDate dataNascita, String sesso) {

        int day = "M".equalsIgnoreCase(sesso) ? dataNascita.getDayOfMonth() : (dataNascita.getDayOfMonth()+40);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM");
        String month = dataNascita.format(dateTimeFormatter);
        String meseCodiceFiscale  = Mese.getMeseCodiceFiscaleByCodiceMese(month);
        String year = String.valueOf(dataNascita.getYear()).substring(2);

        return year + meseCodiceFiscale + String.format("%02d", day);
    }


    private String carattereDiControllo(String codFiscale) {
        char[] chars = codFiscale.toCharArray();
        long carattere = 0L;
        int resto = 0;
        int j = 0;
        while (j<2) {
            for (int i = j; i < chars.length; i = i + 2) {
                carattere += (j == 0 ? CarattereAlfanumericiDispari.getValoreByCarattere(String.valueOf(chars[i])) : CarattereAlfanumericiPari.getValoreByCarattere(String.valueOf(chars[i])));
            }
            j++;
        }
        resto = (int)carattere % 26;
        return carattereControlloCodiceFiscaleMap.get(resto);
    }

    private String addVowels(String input, String consonanti) {
        return getVocali(input).chars().limit(3 - consonanti.length()).mapToObj(i -> "" + (char) i).collect(Collectors.joining());
    }

    private Stream<String> getConsonants(String input) {

        Set<Character> listaConsonanti = new HashSet<>(Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'));

        return input.chars()
                .filter(i -> listaConsonanti.contains((char) i))
                .mapToObj(i -> "" + (char) i);
    }

    private String getVocali(String input) {
        Set<Character> listaVocali = new HashSet<>(Arrays.asList('A','E','I', 'O', 'U'));
        return input.chars()
                .filter(i -> listaVocali.contains((char) i))
                .mapToObj(i -> "" + (char) i)
                .collect(Collectors.joining());
    }

    private int consonantsCount(String nameSurname) {

        String CONSONANT_PATTERN = "[B-DF-HJ-NP-TV-Z]";

        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile(CONSONANT_PATTERN);
        Matcher matcher = pattern.matcher(nameSurname);
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.length();
    }

    private int vowelsCount(String nameSurname) {

        String VOWEL_PATTERN = "[AEIOU]";

        StringBuilder sb = new StringBuilder();

        Pattern pattern = Pattern.compile(VOWEL_PATTERN);
        Matcher matcher = pattern.matcher(nameSurname);
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.length();
    }

    private String ricavaSessoSoggetto(String codiceFiscale) {
        String sex = "";
        if(StringUtils.isNotBlank(codiceFiscale)) {
            String str = codiceFiscale.substring(9, 10) ;
            if("4".equals(str) || "5".equals(str) || "6".equals(str) || "7".equals(str) ||
                    "Q".equals(str) || "R".equals(str) || "S".equals(str) || "T".equals(str)) {
                sex = "F" ;
            }else if("0".equals(str) || "1".equals(str) || "2".equals(str) || "3".equals(str) ||
                    "L".equals(str) || "M".equals(str) || "N".equals(str) || "P".equals(str)) {
                sex = "M" ;
            }
        }

        return sex;
    }

    private boolean checkCodiceFiscaleOmocode(String cFiscStandard, String codiceFiscale) {

        boolean result = false;

        final String omocodici = "LMNPQRSTUV";
        final String listaControllo = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int[] listaPari = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25};
        int[] listaDispari = {1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23};

        codiceFiscale = codiceFiscale.toUpperCase();
        char[] cCodice = codiceFiscale.toCharArray();

        for (int k = 6; k < 15; k++)
        {
            if ((k == 8) || (k == 11))
                continue;
            Integer x = (omocodici.indexOf(cCodice[k]));
            if (x != -1)
                cCodice[k] = x.toString().toCharArray()[0];
        }

        String cFiscOmo = new StringBuilder().append(cCodice).toString() ;
        if(!cFiscStandard.substring(6,15).equals(cFiscOmo.substring(6,15))) {
            return result;
        }

        result = true;

        int somma = 0;
        cCodice = codiceFiscale.toCharArray();
        for (int i = 0; i < 15; i++) {
            char c = cCodice[i];
            int x = "0123456789".indexOf(c);
            if (x != -1)
                c = listaControllo.substring(x,x+1).toCharArray()[0];
            x = listaControllo.indexOf(c);
            if ((i % 2) == 0)
                x = listaDispari[x];
            else
                x = listaPari[x];
            somma += x;
        }
        int ss = somma % 26;
        result = (listaControllo.substring(ss,ss+1).equals(codiceFiscale.substring(15,16)));

        return result;
    }
}

package it.interno.personefisiche.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PersonaFisicaConfig {


    @Bean
    public Map<Integer, String> carattereControlloCodiceFiscaleMap() {

        Map<Integer, String> map = new HashMap<>();

        map.put(0,"A");
        map.put(1,"B");
        map.put(2,"C");
        map.put(3,"D");
        map.put(4,"E");
        map.put(5,"F");
        map.put(6,"G");
        map.put(7,"H");
        map.put(8,"I");
        map.put(9,"J" );
        map.put(10,"K");
        map.put(11,"L");
        map.put(12,"M");
        map.put(13,"N");
        map.put(14,"O");
        map.put(15,"P");
        map.put(16,"Q");
        map.put(17,"R");
        map.put(18,"S");
        map.put(19,"T");
        map.put(20,"U");
        map.put(21,"V");
        map.put(22,"W");
        map.put(23,"X");
        map.put(24,"Y");
        map.put(25,"Z");

        return map;
    }
}



package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Questa si occupa di effettuare i test su Partita.
 */
public class PartitaTest {
    /**
     * Variabile partita che si occupa di gestire le informazioni della partita.
     */
    private Partita partita;
    /**
     * Variabile costante che rappresenta il numero massimo di tentativi.
     */
    private static final int MAXTENT = 6;


    @BeforeEach
    private void setUpBeforeClass() throws Exception {
        partita = new Partita(MAXTENT, "MAMMA");
     }
     /**
      * metodo che testa il set dei tentativi effettuati.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testSetTentativiEffettuati()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int tentativiEffettuati = MAXTENT - MAXTENT / 2;
        final Field field =
        partita.getClass().getDeclaredField("tentativiEffettuati");
        // assegno a field il campo tentativiEffettuati di Partita
        field.setAccessible(true);
        //campi di partita privati --> devono essere resi accessibili
        partita.setTentativiEffettuati(tentativiEffettuati);
        assertEquals(tentativiEffettuati, field.get(partita));
        /*usando getTentativiEffettuati test non attendibile
         perchè get potrebbe non funzionare*/
    }
    /**
      * metodo che testa il get dei tentativi effettuati.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testGetTentativiEffettuati()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        assertEquals(0, partita.getTentativiEffettuati());
    }
    /**
      * metodo che testa il set dei tentativi massimi.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testSetMaxtentativi()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        int maxTentativi = MAXTENT - MAXTENT / 2;
        final Field field = partita.getClass().getDeclaredField("maxTentativi");
        // assegno a field il campo maxTentativi di Partita
        field.setAccessible(true);
         //campi di partita privati --> devono essere resi accessibili
        partita.setMaxTentativi(maxTentativi);
        assertEquals(maxTentativi, field.get(partita));
        /*usando getMaxTentativi test non attendibile
         perchè get potrebbe non funzionare*/
    }
    /**
      * metodo che testa il get dei tentativi massimi.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testGetMaxtentativiClasse()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        assertEquals(MAXTENT, partita.getMaxTentativi());
    }
    /**
      * metodo che testa il set della parola.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testSetParola()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String parola = "MANGO";
        final Field field =
        partita.getClass().getDeclaredField("parolaSegreta");
        // assegno a field il campo parolaSegreta di Partita
        field.setAccessible(true);
        //campi di partita privati --> devono essere resi accessibili
        partita.setParola(parola);
        assertEquals(parola, field.get(partita));
        /*usando getSetParola test non attendibile
         perchè get potrebbe non funzionare*/
    }
    /**
      * metodo che testa il get della parola.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testGetParolaClasse()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        assertEquals("MAMMA", partita.getParola());
    }
    /**
      * metodo che testa il set della griglia tentativi.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testSetGrigliaTentativi()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String[] griglia = new String[partita.getMaxTentativi()];
        griglia[0] = "PAPPA";
        final Field field =
        partita.getClass().getDeclaredField("grigliaTentativi");
        // assegno a field il campo grigliaTentativi di Partita
        field.setAccessible(true);
        //campi di partita privati --> devono essere resi accessibili
        partita.setGrigliaTentativi(0, ((String) griglia[0]));
        assertEquals(griglia[0], ((String[]) field.get(partita))[0]);
        /*usando getGrigliaTentativi test non attendibile
         perchè get potrebbe non funzionare*/
    }
    /**
      * metodo che testa il get della griglia tentativi.
      * @throws NoSuchFieldException
      * @throws SecurityException
      * @throws IllegalArgumentException
      * @throws IllegalAccessException
      */
    @Test
    public void testGetGrigliaTentativi()
    throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        String[] griglia = new String[partita.getMaxTentativi()];
        griglia[0] = "PAPPA";
        final Field field =
        partita.getClass().getDeclaredField("grigliaTentativi");
        // assegno a field il campo grigliaTentativi di Partita
        field.setAccessible(true);
        //campi di partita privati --> devono essere resi accessibili
        field.set(partita, griglia);
        assertEquals(griglia[0], partita.getGrigliaTentativi(0));
    }
}


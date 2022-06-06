package it.uniba.app;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 * Questa si occupa di effettuare i test su Partita.
 */
@TestInstance(Lifecycle.PER_CLASS)
public class PartitaTest {
    
    private Partita partita;

    @BeforeAll
    private void setUpBeforeClass() throws Exception {
		partita= new Partita(6, "MAMMA");
	}

    @Test
    public void testSetTentativiEffettuatiValidi() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int tentativiEffettuati= 3;
        final Field field = partita.getClass().getDeclaredField("tentativiEffettuati"); // assegno a field il campo tentativiEffettuati di Partita
        field.setAccessible(true); //campi di partita privati --> devono essere resi accessibili
        partita.setTentativiEffettuati(tentativiEffettuati);
        assertEquals(tentativiEffettuati, field.get(partita)); //usando getTentativiEffettuati test non attendibile perchè get potrebbe non funzionare
    }

    @Test
    public void testGetTentativiEffettuatiValido() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        int tentativiEffettuati = 5;
        final Field field = partita.getClass().getDeclaredField("tentativiEffettuati"); // assegno a field il campo tentativiEffettuati di Partita
        field.setAccessible(true); //campi di partita privati --> devono essere resi accessibili
        field.set(partita, tentativiEffettuati);
        assertEquals(tentativiEffettuati, partita.getTentativiEffettuati());
    }

}


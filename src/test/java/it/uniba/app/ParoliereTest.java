package it.uniba.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

//import it.uniba.app.Wordle;

/**
 * Questa si occupa di effettuare i test sul Paroliere.
 */
@TestInstance(Lifecycle.PER_METHOD)
public class ParoliereTest {
    /**
     * Prima dell'esecuzione di ogni test inizializzo parolaSegreta
     * a Pluto perche' se fornita a parolaSegreta tramite set
     * una parola non valida, questa resta settata alla parola inserita
     * precedentemente. Mi serve dunque una parola su cui poter effettuare
     * il controllo.
     */
    @BeforeEach
    private void setUpBefore() throws Exception {
        Wordle.setParolaSegreta(new String("PLUTO"));
	}

    @Test
    public void testImpostaParolaSegretaValida() {
        Paroliere.impostaParolaSegreta("BIMBA");
        assertEquals("BIMBA", Wordle.getParolaSegreta());
    }

    @Test
    public void testImpostaParolaSegretaLong() {
        Paroliere.impostaParolaSegreta("MACCHINA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }

    @Test
    public void testImpostaParolaSegretaShort() {
        Paroliere.impostaParolaSegreta("AIA");
        assertEquals("PLUTO", Wordle.getParolaSegreta());
    }
    
}

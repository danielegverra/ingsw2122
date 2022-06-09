package it.uniba.app;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Questa si occupa di effettuare i test sul Paroliere.
 */
public class MonitorTest {
    
    private  ByteArrayOutputStream outContent;
	private  ByteArrayInputStream in;
	private static  PrintStream sysOutBackup = System.out;
	private static  InputStream sysInBackup = System.in;

    static Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    @AfterAll
    public static void restoreStreams() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
        sc.close();
    }
   
    @BeforeEach
    public void setUp() throws Exception {
        in = new ByteArrayInputStream("s".getBytes(Charset.defaultCharset()));
        System.setIn(in);
        outContent= new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent,true,Charset.defaultCharset().toString()));
    }

    @Test 
    public void testMessaggiParolaNonValida() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Parola segreta non valida." + ls;
        Monitor.messaggi("parolanonvalida");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiChiudi() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nSei uscito dal gioco." + ls;
        Monitor.messaggi("chiudi");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiNuova() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nUna nuova partita sta iniziando!" + ls;
        Monitor.messaggi("nuovapartita");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiParolaIndovinata() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Parola segreta indovinata." + ls;
        Monitor.messaggi("parolaindovinata");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiNumeroMaxTentativi() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Hai raggiunto il numero massimo di tentativi!" + ls;
        Monitor.messaggi("numeromaxtentativi");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiSecondaParola() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Questo comando non ha bisogno "
        + "di una seconda parola." + ls;
        Monitor.messaggi("secondaparola");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiParolaAssente() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Parola segreta assente." + ls;
        Monitor.messaggi("parolaassente");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiComandoAssente() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Comando non riconosciuto." + ls;
        Monitor.messaggi("comandoassente");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiConfermaScelta() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nSei sicuro della tua scelta?" + ls;
        Monitor.messaggi("confermascelta");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiDigitaScelta() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Digita S se vuoi confermare la tua decisione."
        + ls + "Digita N se vuoi tornare a giocare." + ls;
        Monitor.messaggi("digitascelta");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testMessaggiSceltaNonValida() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Non hai inserito una parola valida." + ls;
        Monitor.messaggi("sceltanonvalida");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiInserisciTentativo() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserisci il tuo tentativo: " + ls;
        Monitor.messaggi("inseriscitentativo");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiAbbandona() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Hai deciso di abbandonare la partita.\nCi rivediamo presto." + ls;
        Monitor.messaggi("abbandonapartita");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiTentativoNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Tentativo non valido." + ls;
        Monitor.messaggi("tentativononvalido");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiTentativoIncompleto() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Tentativo incompleto." + ls;
        Monitor.messaggi("tentativoincompleto");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiTentativoEccessivo() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Tentativo eccessivo." + ls;
        Monitor.messaggi("tentativoeccessivo");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiSlash() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Tutti i comandi devono iniziare per '/'." + ls;
        Monitor.messaggi("comandoslash");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiMassimoParole() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Tutti i comandi devono "
        + "avere massimo due parole." + ls;
        Monitor.messaggi("massimoparole");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiParolaLunga() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Parola segreta troppo lunga, deve avere "
        + Wordle.getDimensioneParola() + " caratteri." + ls;
        Monitor.messaggi("parolalunga", Wordle.getDimensioneParola());
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiParolaCorta() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "Parola segreta troppo corta, deve avere "
        + Wordle.getDimensioneParola() + " caratteri." + ls;
        Monitor.messaggi("parolacorta", Wordle.getDimensioneParola());
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiRivelaParola() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "La parola segreta e' " + Wordle.getParolaSegreta() + ls;
        Monitor.messaggi("rivelaparola", Wordle.getParolaSegreta());
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testMessaggiNumeroTentativi() throws UnsupportedEncodingException {
        Partita p = new Partita(6, "PLUTO");
        String ls = System.getProperty("line.separator");
		String msg = "Numero tentativi: " + p.getTentativiEffettuati() + ls;
        Monitor.messaggi("numerotentativi", p.getTentativiEffettuati());
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testMessaggiParametriParola() throws UnsupportedEncodingException {
		String msg = "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n";
        Monitor.messaggi("parametriparola",  Wordle.getDimensioneParola());
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test
    public void testStampaGrigliaTentativoEsatto() throws UnsupportedEncodingException {
        Partita p= new Partita(6, "LIMBO");
        p.setGrigliaTentativi(0, "LIMBO");
        String ls = System.getProperty("line.separator");
        String msg= ConsoleColors.WHITE + " ";
        for (int j = 0; j < 5; j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
		msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
		msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < 5; j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
		
        //stampa riga
				
		msg += ConsoleColors.WHITE + "|";
        String pTentata= "LIMBO";
		for (int i = 0; i < 5; i++) {
            msg += ConsoleColors.GREEN + "  "
			+ pTentata.charAt(i) + "  "
			+ ConsoleColors.WHITE + "|"
			+ ConsoleColors.RESET;
        }
		
		msg += "\n" + ConsoleColors.WHITE + " ";
		for (int j = 0; j < 5; j++) {
			msg += ConsoleColors.WHITE
			+ "~~~~~ " + ConsoleColors.RESET;
		}
		msg += "\n";
		
				//stampa le altre
				
		for (int i = 0; i < 5; i++) {
            
            msg+=ConsoleColors.WHITE + "|";
			for (int j = 0; j < 5; j++) {
				msg += ConsoleColors.DARK_WHITE
				+ "     " + ConsoleColors.WHITE + "|"
				+ ConsoleColors.RESET;
			}
            
            /*
             * Stampa del separatore inferiore per ogni riga
             */
             msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < 5; j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        Monitor.stampaGriglia(p);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testStampaGrigliaTentativoSbagliato() throws UnsupportedEncodingException {
        Partita p= new Partita(6, "LIMBO");
        p.setGrigliaTentativi(0, "ALMBO");
        String ls = System.getProperty("line.separator");
        String msg= ConsoleColors.WHITE + " ";
        for (int j = 0; j < 5; j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
		msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
		msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < 5; j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
		
        //stampa riga
				
		msg += ConsoleColors.WHITE + "|";
        String pTentata= "ALMBO";
        msg += ConsoleColors.GREY + "  "
        + pTentata.charAt(0) + "  "
        + ConsoleColors.WHITE + "|"
        + ConsoleColors.RESET;
        msg += ConsoleColors.YELLOW + "  "
        + pTentata.charAt(1) + "  "
        + ConsoleColors.WHITE + "|"
        + ConsoleColors.RESET;
		for (int i = 2; i < 5; i++) {
            msg += ConsoleColors.GREEN + "  "
			+ pTentata.charAt(i) + "  "
			+ ConsoleColors.WHITE + "|"
			+ ConsoleColors.RESET;
        }
		
		msg += "\n" + ConsoleColors.WHITE + " ";
		for (int j = 0; j < 5; j++) {
			msg += ConsoleColors.WHITE
			+ "~~~~~ " + ConsoleColors.RESET;
		}
		msg += "\n";
		
				//stampa le altre
				
		for (int i = 0; i < 5; i++) {
            
            msg+=ConsoleColors.WHITE + "|";
			for (int j = 0; j < 5; j++) {
				msg += ConsoleColors.DARK_WHITE
				+ "     " + ConsoleColors.WHITE + "|"
				+ ConsoleColors.RESET;
			}
            
            /*
             * Stampa del separatore inferiore per ogni riga
             */
             msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < 5; j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        Monitor.stampaGriglia(p);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
}

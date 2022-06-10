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
 * Questa si occupa di effettuare i test sul Manager.
 */
public class ManagerTest {

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
    public void testInputComandoValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserire un comando:\n> "
        + "Parola segreta assente." + ls;
        sc= new Scanner(new ByteArrayInputStream(("/MOSTRA").getBytes()));
        Manager.inputComando(sc);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testInputComandoNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserire un comando:\n> "
        + "" + ls;
        sc= new Scanner(new ByteArrayInputStream((ls + "/MOSTRA").getBytes()));
        Manager.inputComando(sc);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testScannerWordleNoSlash() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Tutti i comandi devono iniziare per '/'." +ls;
        Manager.scannerWordle("NUOVA");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testScannerWordleTooManyArgumets() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Tutti i comandi devono avere massimo due parole." +ls;
        Manager.scannerWordle("/NUOVA MAMMA BIMBA");
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testScannerWordleCorrect() throws UnsupportedEncodingException {
        String[] s = new String[2];
        s[0]= "/NUOVA";
        assertArrayEquals(s, Manager.scannerWordle("/NUOVA"));
    } 

    @Test 
    public void testParserWordleNuovaValida() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="OK." +ls;
        String[] s = new String[2];
        s[0]= "/NUOVA";
        s[1]= "MANGO";
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParserWordleNuovaNonValida() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Parola segreta assente." +ls;
        String[] s = new String[2];
        s[0]= "/NUOVA";
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }  

    @Test 
    public void testParserWordleGiocaNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Questo comando non ha bisogno di una seconda parola." +ls;
        String[] s = new String[2];
        s[0]= "/GIOCA";
        s[1]= "CIAO"; 
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }  

    @Test 
    public void testParserWordleGiocaEsci() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "OK."+ ls
        + "\nUna nuova partita sta iniziando!" + ls
        + "Di seguito vengono mostrate "
        + "le regole e il funzionamento del gioco:\n" + ls
        + "Lo scopo del gioco e' indovinare "
        + "una parola di cinque lettere "
        + "utilizzando un massimo di sei tentativi." + ls
        + "La parola segreta viene decisa "
        + "dal paroliere, che per ogni tentativo restituisce "
        + "tre tipi di indizi utili a restringere "
        + "il cerchio sulla soluzione" + ls
        + "Ogni lettera nella posizione "
        + "corretta e' evidenziata in verde," + ls
        + "ogni lettera presente nella parola"
        + "segreta ma inserita nella posizione "
        + "errata e' evidenziata in giallo" + ls
        + "e ogni lettera del tutto assente"
        + "dalla soluzione e' evidenziata in grigio." + ls
        + "\nAll'interno del gioco e' possibile "
        + "eseguire i seguenti comandi:" + ls 
        + " 1. /help: consente di visualizzare i comandi "
        + "disponibili e le regole del gioco" + ls
        + " 2. /esci: consente di chiudere "
        + "l'applicazione a seguito di una conferma positiva dell'utente " + ls
        + "\nDa paroliere e' possibile eseguire "
        + "i seguenti comandi, ma solo se non vi e' una partita in corso: " + ls
        + " 1. /nuova <parola>: consente di impostare "
        + "una nuova parola segreta, anche durante la sessione di gioco"
        + "senza uscire dall'applicazione" + ls
        + " 2. /mostra: consente di visualizzare "
        + "la parola segreta" + ls
        + "\nDa giocatore e' possibile "
        + "eseguire i seguenti comandi: " + ls 
        + " 1. /gioca: consente di visualizzare la"
        + "matrice dei tentativi vuota se nessuna partita e' in corso" + ls
        + " 2. /abbandona: consente di abbandonare"
        + "la partita a seguito di una conferma positiva dell'utente\n" + ls;
		
		//stampa griglia iniziale
		
		msg += ConsoleColors.WHITE + " ";
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
		
		for (int i = 0; i < 6; i++) {
            
            msg += ConsoleColors.WHITE + "|";
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
		
		//stampa tentativo
		
		msg += "\nInserisci il tuo tentativo: " + ls;
        msg += "\nSei sicuro della tua scelta?" + ls 
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "\nSei uscito dal gioco." + ls;

        String[] s = new String[2];
        s[0]= "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        sc= new Scanner(new ByteArrayInputStream(("/ESCI" + ls + "S").getBytes()));
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testParserWordleGiocaAbbandona() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "OK."+ ls
        + "\nUna nuova partita sta iniziando!" + ls
        + "Di seguito vengono mostrate "
        + "le regole e il funzionamento del gioco:\n" + ls
        + "Lo scopo del gioco e' indovinare "
        + "una parola di cinque lettere "
        + "utilizzando un massimo di sei tentativi." + ls
        + "La parola segreta viene decisa "
        + "dal paroliere, che per ogni tentativo restituisce "
        + "tre tipi di indizi utili a restringere "
        + "il cerchio sulla soluzione" + ls
        + "Ogni lettera nella posizione "
        + "corretta e' evidenziata in verde," + ls
        + "ogni lettera presente nella parola"
        + "segreta ma inserita nella posizione "
        + "errata e' evidenziata in giallo" + ls
        + "e ogni lettera del tutto assente"
        + "dalla soluzione e' evidenziata in grigio." + ls
        + "\nAll'interno del gioco e' possibile "
        + "eseguire i seguenti comandi:" + ls 
        + " 1. /help: consente di visualizzare i comandi "
        + "disponibili e le regole del gioco" + ls
        + " 2. /esci: consente di chiudere "
        + "l'applicazione a seguito di una conferma positiva dell'utente " + ls
        + "\nDa paroliere e' possibile eseguire "
        + "i seguenti comandi, ma solo se non vi e' una partita in corso: " + ls
        + " 1. /nuova <parola>: consente di impostare "
        + "una nuova parola segreta, anche durante la sessione di gioco"
        + "senza uscire dall'applicazione" + ls
        + " 2. /mostra: consente di visualizzare "
        + "la parola segreta" + ls
        + "\nDa giocatore e' possibile "
        + "eseguire i seguenti comandi: " + ls 
        + " 1. /gioca: consente di visualizzare la"
        + "matrice dei tentativi vuota se nessuna partita e' in corso" + ls
        + " 2. /abbandona: consente di abbandonare"
        + "la partita a seguito di una conferma positiva dell'utente\n" + ls;
		
		//stampa griglia iniziale
		
		msg += ConsoleColors.WHITE + " ";
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
		
		for (int i = 0; i < 6; i++) {
            
            msg += ConsoleColors.WHITE + "|";
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
		
		//stampa tentativo
		
		msg += "\nInserisci il tuo tentativo: " + ls;
        msg += "\nSei sicuro della tua scelta?" + ls 
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "Hai deciso di abbandonare la partita.\nCi rivediamo presto." + ls;

        String[] s = new String[2];
        s[0]= "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        sc= new Scanner(new ByteArrayInputStream(("/ABBANDONA" + ls + "S").getBytes()));
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testParserWordleGiocaValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "OK."+ ls
        + "\nUna nuova partita sta iniziando!" + ls
        + "Di seguito vengono mostrate "
        + "le regole e il funzionamento del gioco:\n" + ls
        + "Lo scopo del gioco e' indovinare "
        + "una parola di cinque lettere "
        + "utilizzando un massimo di sei tentativi." + ls
        + "La parola segreta viene decisa "
        + "dal paroliere, che per ogni tentativo restituisce "
        + "tre tipi di indizi utili a restringere "
        + "il cerchio sulla soluzione" + ls
        + "Ogni lettera nella posizione "
        + "corretta e' evidenziata in verde," + ls
        + "ogni lettera presente nella parola"
        + "segreta ma inserita nella posizione "
        + "errata e' evidenziata in giallo" + ls
        + "e ogni lettera del tutto assente"
        + "dalla soluzione e' evidenziata in grigio." + ls
        + "\nAll'interno del gioco e' possibile "
        + "eseguire i seguenti comandi:" + ls 
        + " 1. /help: consente di visualizzare i comandi "
        + "disponibili e le regole del gioco" + ls
        + " 2. /esci: consente di chiudere "
        + "l'applicazione a seguito di una conferma positiva dell'utente " + ls
        + "\nDa paroliere e' possibile eseguire "
        + "i seguenti comandi, ma solo se non vi e' una partita in corso: " + ls
        + " 1. /nuova <parola>: consente di impostare "
        + "una nuova parola segreta, anche durante la sessione di gioco"
        + "senza uscire dall'applicazione" + ls
        + " 2. /mostra: consente di visualizzare "
        + "la parola segreta" + ls
        + "\nDa giocatore e' possibile "
        + "eseguire i seguenti comandi: " + ls 
        + " 1. /gioca: consente di visualizzare la"
        + "matrice dei tentativi vuota se nessuna partita e' in corso" + ls
        + " 2. /abbandona: consente di abbandonare"
        + "la partita a seguito di una conferma positiva dell'utente\n" + ls;
		
		//stampa griglia iniziale
		
		msg += ConsoleColors.WHITE + " ";
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
		
		for (int i = 0; i < 6; i++) {
            
            msg += ConsoleColors.WHITE + "|";
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
		
		//stampa tentativo
		
		msg += "\nInserisci il tuo tentativo: " + ls;
		
		//stampa griglia piena
		
		msg += ConsoleColors.WHITE + " ";
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
        String pTentata= "MANGO";
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
            
            msg += ConsoleColors.WHITE + "|";
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
		
		//stampa congrats
				
		msg += "Parola segreta indovinata." + ls
		+ "Numero tentativi: " +  1 + ls;
        String[] s = new String[2];
        s[0]= "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        sc= new Scanner(new ByteArrayInputStream("MANGO".getBytes()));
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testParserWordleHelpNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Questo comando non ha bisogno di una seconda parola." +ls;
        String[] s = new String[2];
        s[0]= "/HELP";
        s[1]= "CIAO"; 
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    } 

    @Test 
    public void testParserWordleHelpValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nAll'interno del gioco e' possibile "
        + "eseguire i seguenti comandi:" + ls 
        + " 1. /help: consente di visualizzare i comandi "
        + "disponibili e le regole del gioco" + ls
        + " 2. /esci: consente di chiudere "
        + "l'applicazione a seguito di una conferma positiva dell'utente " + ls
        + "\nDa paroliere e' possibile eseguire "
        + "i seguenti comandi, ma solo se non vi e' una partita in corso: " + ls
        + " 1. /nuova <parola>: consente di impostare "
        + "una nuova parola segreta, anche durante la sessione di gioco"
        + "senza uscire dall'applicazione" + ls
        + " 2. /mostra: consente di visualizzare "
        + "la parola segreta" + ls
        + "\nDa giocatore e' possibile "
        + "eseguire i seguenti comandi: " + ls 
        + " 1. /gioca: consente di visualizzare la"
        + "matrice dei tentativi vuota se nessuna partita e' in corso" + ls
        + " 2. /abbandona: consente di abbandonare"
        + "la partita a seguito di una conferma positiva dell'utente\n" + ls
        + "Di seguito vengono mostrate "
        + "le regole e il funzionamento del gioco:\n" + ls
        + "Lo scopo del gioco e' indovinare "
        + "una parola di cinque lettere "
        + "utilizzando un massimo di sei tentativi." + ls
        + "La parola segreta viene decisa "
        + "dal paroliere, che per ogni tentativo restituisce "
        + "tre tipi di indizi utili a restringere "
        + "il cerchio sulla soluzione" + ls
        + "Ogni lettera nella posizione "
        + "corretta e' evidenziata in verde," + ls
        + "ogni lettera presente nella parola"
        + "segreta ma inserita nella posizione "
        + "errata e' evidenziata in giallo" + ls
        + "e ogni lettera del tutto assente"
        + "dalla soluzione e' evidenziata in grigio." + ls;

        String[] s = new String[2];
        s[0]= "/HELP";
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
    
    @Test 
    public void testParserWordleMostraNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Questo comando non ha bisogno di una seconda parola." +ls;
        String[] s = new String[2];
        s[0]= "/MOSTRA";
        s[1]= "CIAO"; 
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParserWordleMostraValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="La parola segreta e' " + Wordle.getParolaSegreta() +ls;
        String[] s = new String[2];
        s[0]= "/MOSTRA";
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParserWordleEsciValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nSei sicuro della tua scelta?" + ls 
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "\nSei uscito dal gioco." + ls;
        String[] s = new String[2];
        s[0]= "/ESCI"; 
        sc= new Scanner(new ByteArrayInputStream("S".getBytes()));
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParserWordleEsciNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Questo comando non ha bisogno di una seconda parola." +ls;
        String[] s = new String[2];
        s[0]= "/ESCI";
        s[1]= "CIAO"; 
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParserWordleComandoAssente() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg ="Comando non riconosciuto." +ls;
        String[] s = new String[2];
        s[0]= "SALVE";
        Manager.parserWordle(sc, s);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test 
    public void testParolaValidaTrue() throws UnsupportedEncodingException {
        assertTrue(Manager.parolaValida("MANGO"));
    }

    @Test 
    public void testParolaValidaMinuscoleFalse() throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("mimmo"));
    }

    @Test 
    public void testParolaValidaIntFalse() throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("23456"));
    }

    @Test 
    public void testParolaValidaSimboliFalse() throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("@@@@@"));
    }

    @Test
    public void testRichiediConfermaTrue() {
        sc= new Scanner(new ByteArrayInputStream("S".getBytes()));
        assertTrue(Manager.richiediConferma(sc));
    }

    @Test
    public void testRichiediConfermaFalse() {
        sc= new Scanner(new ByteArrayInputStream("N".getBytes()));
        assertTrue(!Manager.richiediConferma(sc));
    }

    @Test
    public void testRichiediConfermaNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nSei sicuro della tua scelta?" + ls 
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "Non hai inserito una parola valida." + ls 
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls;
        sc= new Scanner(new ByteArrayInputStream(("BYE" + ls + "S").getBytes()));
        Manager.richiediConferma(sc);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testTentativoNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo non valido." + ls 
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n" 
        + "\nInserisci il tuo tentativo: " + ls;
        sc= new Scanner(new ByteArrayInputStream(("@@@@@" + ls + "MANGO").getBytes()));
        Manager.tentativo(sc, 5, false);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testTentativoNonIncompleto() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo incompleto." + ls 
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n" 
        + "\nInserisci il tuo tentativo: " + ls;
        sc= new Scanner(new ByteArrayInputStream(("KEY" + ls + "MANGO").getBytes()));
        Manager.tentativo(sc, 5, false);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testTentativoEccessivo() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
		String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo eccessivo." + ls 
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n" 
        + "\nInserisci il tuo tentativo: " + ls;
        sc= new Scanner(new ByteArrayInputStream(("BATMAN" + ls + "MANGO").getBytes()));
        Manager.tentativo(sc, 5, false);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }
}

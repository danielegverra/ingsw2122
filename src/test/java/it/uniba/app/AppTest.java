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
 * Main test class of the application.
 */
public class AppTest {

    String[] args= new String[2];
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
        outContent= new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent,true,Charset.defaultCharset().toString()));		
    }


    /**
     * Test the getGreeting method of the App class.
     */
    @Test
    public void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(
                "app should have a greeting", classUnderTest.getGreeting());
    }

    @Test
    public void testMainGetHelpH() throws UnsupportedEncodingException {
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
        args[0]= "-h";
        App.getHelp(args, true);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

    @Test
    public void testMainGetHelpHelp() throws UnsupportedEncodingException {
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
        args[0]= "--help";
        App.getHelp(args, true);
        assertEquals(msg, outContent.toString(Charset.defaultCharset().toString()));
    }

}

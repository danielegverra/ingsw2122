package it.uniba.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
final class AppTest { //classe utilizzata per il testing.
    /**
     * Array di stringhe che contiene ciò
     * che l'utente ha inserito da tastiera.
     */
    private String[] args = new String[2];
    /**
     * Stream in cui scriviamo bytes.
     */
    private  ByteArrayOutputStream outContent;
    /**
     * Buffer che contiene bytes che possono essere letti dallo stream.
     */
    private ByteArrayInputStream in;
    /**
     * Aggiunge funzionalità ad uno stream.
     */
    private static PrintStream sysOutBackup = System.out;
    /**
     * Questo metodo testa il funzionamento di inputcomando
     * quando gli viene passato un input valido.
     */
    private static InputStream sysInBackup = System.in;
    /**
     * L'attributo sc è lo scanner che viene passato in input(?).
     */
    private static Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);
    /**
     * Funzione che restituisce lo scanner.
     * @return Lo scanner.
     */
    public static Scanner getSc() {
        return sc;
    }
    /**
     * Funzione per settare lo scanner.
     * @param s è lo scanner.
     */
    public static void setSc(final Scanner s) {
        AppTest.sc = s;
    }
    /**
     * Funzione che restituisce gli args.
     * @return gli args.
     */
    public String[] getArgs() {
        return args;
    }
    /**
     * Funzione per settare gli args.
     * @param a è un array di String.
     */
    public void setArgs(final String[] a) {
        this.args = a;
    }
    /**
     * Operazioni che andranno eseguite sempre a fine test.
     */
    @AfterAll
    public static void restoreStreams() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
        sc.close();
    }
    /**
     * Operazioni che andranno eseguite prima di ogni test.
     */
    @BeforeEach
    public void setUp() throws Exception {
        in = new ByteArrayInputStream("s".getBytes(Charset.defaultCharset()));
        System.setIn(in);
        outContent = new ByteArrayOutputStream();
        String charset = Charset.defaultCharset().toString();
        System.setOut(new PrintStream(outContent, true, charset));
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
    /**
     * Funzione che testa la funzinalità help.
     */
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
        args[0] = "-h";
        App.getHelp(args, true);
        String charset = Charset.defaultCharset().toString();
        assertEquals(msg, outContent.toString(charset));
    }
    /**
     * Funzione che testa il comando help.
     */
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
        args[0] = "--help";
        App.getHelp(args, true);
        String charset = Charset.defaultCharset().toString();
        assertEquals(msg, outContent.toString(charset));
    }

}

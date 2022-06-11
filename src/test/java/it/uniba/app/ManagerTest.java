package it.uniba.app;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    /**
     * L'attributo outContent è l'OutputStream
     * utilizzato per il controllo delle stampe.
     */
    private  ByteArrayOutputStream outContent;

    /**
     * L'attributo in è l'InputStream
     * utilizzato per lo scanner.
     */
    private ByteArrayInputStream in;

    /**
     * L'attributo sysOutBackup è
     * il backup del PrintStream utilizzato
     * per settare alla fine dei test System.out.
     */
    private static PrintStream sysOutBackup = System.out;

    /**
     * L'attributo sysInBackup è
     * il backup dell'InputStream utilizzato
     * per settare alla fine dei test System.in.
     */
    private static InputStream sysInBackup = System.in;

    /**
     * L'attributo sc è lo scanner utilizzato nei test.
     */
    private static Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    /**
     * E' il metodo che si occupa di resettare
     * l'ambiente di test dopo l'esecuzione del test.
     */
    @AfterAll
    public static void restoreStreams() {
        System.setOut(sysOutBackup);
        System.setIn(sysInBackup);
        sc.close();
    }

    /**
     * E' il metodo che si occupa di settare
     * l'InputStream e l'OutputStream prima di
     * ogni test.
     * @throws Exception
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
     * Questo metodo testa il funzionamento di inputComando()
     * quando è passato il comando corretto.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testInputComandoValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserire un comando:\n> "
        + "Parola segreta assente." + ls;
        sc = new Scanner(new ByteArrayInputStream(("/MOSTRA").getBytes()));
        Manager.inputComando(sc);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di inputComando()
     * quando è passato il comando non valido.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testInputComandoNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserire un comando:\n> "
        + "" + ls;
        sc = new Scanner(new ByteArrayInputStream((ls + "/MOSTRA").getBytes()));
        Manager.inputComando(sc);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ScannerWordle(String)
     * quando è passato il comando senza slash.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testScannerWordleNoSlash() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tutti i comandi devono iniziare per '/'." + ls;
        Manager.scannerWordle("NUOVA");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ScannerWordle(String)
     * quando è passato il comando senza slash.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testScannerWordleTooManyArgumets()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tutti i comandi devono avere "
        + "massimo due parole." + ls;
        Manager.scannerWordle("/NUOVA MAMMA BIMBA");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ScannerWordle(String)
     * quando è passato il comando corretto.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testScannerWordleCorrect() throws UnsupportedEncodingException {
        String[] s = new String[2];
        s[0] = "/NUOVA";
        assertArrayEquals(s, Manager.scannerWordle("/NUOVA"));
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /nuova con una parola valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleNuovaValida()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "OK." + ls;
        String[] s = new String[2];
        s[0] = "/NUOVA";
        s[1] = "MANGO";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /nuova con una parola non valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleNuovaNonValida()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta assente." + ls;
        String[] s = new String[2];
        s[0] = "/NUOVA";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /gioca con accanto una parola.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleGiocaNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Questo comando non ha bisogno"
        + " di una seconda parola." + ls;
        String[] s = new String[2];
        s[0] = "/GIOCA";
        s[1] = "CIAO";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /gioca seguito da /ESCI.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleGiocaEsci()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "OK." + ls
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

        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";

        for (int i = 0; i < Wordle.getMaxTentativi(); i++) {

            msg += ConsoleColors.WHITE + "|";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.DARK_WHITE
                + "     " + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET;
            }

            /*
             * Stampa del separatore inferiore per ogni riga
             */
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        msg += "\nInserisci il tuo tentativo: " + ls;
        msg += "\nSei sicuro della tua scelta?" + ls
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "\nSei uscito dal gioco." + ls;

        String[] s = new String[2];
        s[0] = "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        String command = "/ESCI" + ls + "S";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /gioca seguito da /ABBANDONA.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleGiocaAbbandona()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "OK." + ls
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

        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";

        for (int i = 0; i < Wordle.getMaxTentativi(); i++) {

            msg += ConsoleColors.WHITE + "|";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.DARK_WHITE
                + "     " + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET;
            }

            /*
             * Stampa del separatore inferiore per ogni riga
             */
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }

        msg += "\nInserisci il tuo tentativo: " + ls;
        msg += "\nSei sicuro della tua scelta?" + ls
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "Hai deciso di abbandonare la partita.\nCi rivediamo presto." + ls;

        String[] s = new String[2];
        s[0] = "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        String command = "/ABBANDONA" + ls + "S";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /gioca seguito dalla parola corretta.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleGiocaValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "OK." + ls
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

        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";

        for (int i = 0; i < Wordle.getMaxTentativi(); i++) {

            msg += ConsoleColors.WHITE + "|";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.DARK_WHITE
                + "     " + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET;
            }

            /*
             * Stampa del separatore inferiore per ogni riga
             */
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        msg += "\nInserisci il tuo tentativo: " + ls;
        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        msg += ConsoleColors.WHITE
        + "|           WORDLE            |"
        + ConsoleColors.RESET + ls;
        msg += ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        msg += ConsoleColors.WHITE + "|";
        String pTentata = "MANGO";
        for (int i = 0; i < Wordle.getDimensioneParola(); i++) {
            msg += ConsoleColors.GREEN + "  "
            + pTentata.charAt(i) + "  "
            + ConsoleColors.WHITE + "|"
            + ConsoleColors.RESET;
        }

        msg += "\n" + ConsoleColors.WHITE + " ";
        for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
            msg += ConsoleColors.WHITE
            + "~~~~~ " + ConsoleColors.RESET;
        }
        msg += "\n";
        for (int i = 0; i < Wordle.getDimensioneParola(); i++) {

            msg += ConsoleColors.WHITE + "|";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.DARK_WHITE
                + "     " + ConsoleColors.WHITE + "|"
                + ConsoleColors.RESET;
            }

            /*
             * Stampa del separatore inferiore per ogni riga
             */
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }

        msg += "Parola segreta indovinata." + ls
        + "Numero tentativi: " +  1 + ls;
        String[] s = new String[2];
        s[0] = "/GIOCA";
        Wordle.setParolaSegreta("MANGO");
        sc = new Scanner(new ByteArrayInputStream("MANGO".getBytes()));
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /help seguito da una parola.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleHelpNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Questo comando non ha bisogno"
        + " di una seconda parola." + ls;
        String[] s = new String[2];
        s[0] = "/HELP";
        s[1] = "CIAO";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /help in maniera valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleHelpValido()
    throws UnsupportedEncodingException {
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
        s[0] = "/HELP";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /mostra seguito da una parola.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleMostraNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Questo comando non ha bisogno"
        + " di una seconda parola." + ls;
        String[] s = new String[2];
        s[0] = "/MOSTRA";
        s[1] = "CIAO";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /mostra in maniera valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleMostraValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "La parola segreta e' "
        + Wordle.getParolaSegreta() + ls;
        String[] s = new String[2];
        s[0] = "/MOSTRA";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /esci.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleEsciValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nSei sicuro della tua scelta?" + ls
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "\nSei uscito dal gioco." + ls;
        String[] s = new String[2];
        s[0] = "/ESCI";
        sc = new Scanner(new ByteArrayInputStream("S".getBytes()));
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando /esci seguito da una parola.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleEsciNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Questo comando non ha bisogno"
        + " di una seconda parola." + ls;
        String[] s = new String[2];
        s[0] = "/ESCI";
        s[1] = "CIAO";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di ParserWordle(Scanner, String[])
     * quando è passato il comando non valido (assente).
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParserWordleComandoAssente()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Comando non riconosciuto." + ls;
        String[] s = new String[2];
        s[0] = "SALVE";
        Manager.parserWordle(sc, s);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di parolaValida(String)
     * quando è passata una parola valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParolaValidaTrue() throws UnsupportedEncodingException {
        assertTrue(Manager.parolaValida("MANGO"));
    }

    /**
     * Questo metodo testa il funzionamento di parolaValida(String)
     * quando è passata una parola in minuscolo.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParolaValidaMinuscoleFalse()
    throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("mimmo"));
    }

    /**
     * Questo metodo testa il funzionamento di parolaValida(String)
     * quando è passata una parola contenente numeri.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParolaValidaIntFalse() throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("23456"));
    }

    /**
     * Questo metodo testa il funzionamento di parolaValida(String)
     * quando è passata una parola non valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testParolaValidaSimboliFalse()
    throws UnsupportedEncodingException {
        assertTrue(!Manager.parolaValida("@@@@@"));
    }

    /**
     * Questo metodo testa il funzionamento di richiediConferma(Scanner)
     * seguito dalla conferma.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testRichiediConfermaTrue() {
        sc = new Scanner(new ByteArrayInputStream("S".getBytes()));
        assertTrue(Manager.richiediConferma(sc));
    }

    /**
     * Questo metodo testa il funzionamento di richiediConferma(Scanner)
     * seguito dalla negazione della conferma.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testRichiediConfermaFalse() {
        sc = new Scanner(new ByteArrayInputStream("N".getBytes()));
        assertTrue(!Manager.richiediConferma(sc));
    }

    /**
     * Questo metodo testa il funzionamento di richiediConferma(Scanner)
     * seguito da una parola non valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testRichiediConfermaNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nSei sicuro della tua scelta?" + ls
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls
        + "Non hai inserito una parola valida." + ls
        + "Digita S se vuoi confermare la tua decisione." + ls
        + "Digita N se vuoi tornare a giocare." + ls;
        String command = "BYE" + ls + "S";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.richiediConferma(sc);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di
     * tentativo(Scanner, int, Boolean) seguito da
     * una parola non valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testTentativoNonValido() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo non valido." + ls
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n"
        + "\nInserisci il tuo tentativo: " + ls;
        String command = "@@@@@" + ls + "MANGO";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.tentativo(sc, Wordle.getDimensioneParola(), false);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di
     * tentativo(Scanner, int, Boolean) seguito da
     * una parola corta.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testTentativoNonIncompleto()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo incompleto." + ls
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n"
        + "\nInserisci il tuo tentativo: " + ls;
        String command = "KEY" + ls + "MANGO";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.tentativo(sc, Wordle.getDimensioneParola(), false);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di
     * tentativo(Scanner, int, Boolean) seguito da
     * una parola lunga.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testTentativoEccessivo() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserisci il tuo tentativo: " + ls
        + "Tentativo eccessivo." + ls
        + "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n"
        + "\nInserisci il tuo tentativo: " + ls;
        String command = "BATMAN" + ls + "MANGO";
        sc = new Scanner(new ByteArrayInputStream(command.getBytes()));
        Manager.tentativo(sc, Wordle.getDimensioneParola(), false);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }
}

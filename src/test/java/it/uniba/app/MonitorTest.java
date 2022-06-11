package it.uniba.app;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata una parola non valida.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParolaNonValida()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta non valida." + ls;
        Monitor.messaggi("parolanonvalida");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'chiudi'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiChiudi() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nSei uscito dal gioco." + ls;
        Monitor.messaggi("chiudi");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'nuovapartita'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiNuova() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nUna nuova partita sta iniziando!" + ls;
        Monitor.messaggi("nuovapartita");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'parolaindovinata'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParolaIndovinata()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta indovinata." + ls;
        Monitor.messaggi("parolaindovinata");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'numeromaxtentativi'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiNumeroMaxTentativi()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Hai raggiunto il numero massimo di tentativi!"
        + ls;
        Monitor.messaggi("numeromaxtentativi");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'secondaparola'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiSecondaParola()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Questo comando non ha bisogno "
        + "di una seconda parola." + ls;
        Monitor.messaggi("secondaparola");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'parolaassente'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParolaAssente()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta assente." + ls;
        Monitor.messaggi("parolaassente");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'comandoassente'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiComandoAssente()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Comando non riconosciuto." + ls;
        Monitor.messaggi("comandoassente");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'confermascelta'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiConfermaScelta()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nSei sicuro della tua scelta?" + ls;
        Monitor.messaggi("confermascelta");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'digitascelta'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiDigitaScelta()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Digita S se vuoi confermare la tua decisione."
        + ls + "Digita N se vuoi tornare a giocare." + ls;
        Monitor.messaggi("digitascelta");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'sceltanonvalida'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiSceltaNonValida()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Non hai inserito una parola valida." + ls;
        Monitor.messaggi("sceltanonvalida");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'inseriscitentativo'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiInserisciTentativo()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "\nInserisci il tuo tentativo: " + ls;
        Monitor.messaggi("inseriscitentativo");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'abbandonapartita'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiAbbandona()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Hai deciso di abbandonare la partita."
        + "\nCi rivediamo presto." + ls;
        Monitor.messaggi("abbandonapartita");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'tentativononvalido'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiTentativoNonValido()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tentativo non valido." + ls;
        Monitor.messaggi("tentativononvalido");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'tentativoincompleto'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiTentativoIncompleto()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tentativo incompleto." + ls;
        Monitor.messaggi("tentativoincompleto");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'tentativoeccessivo'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiTentativoEccessivo()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tentativo eccessivo." + ls;
        Monitor.messaggi("tentativoeccessivo");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'comandoslash'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiSlash() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tutti i comandi devono iniziare per '/'." + ls;
        Monitor.messaggi("comandoslash");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'massimoparole'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiMassimoParole()
    throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Tutti i comandi devono "
        + "avere massimo due parole." + ls;
        Monitor.messaggi("massimoparole");
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'parolalunga'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParolaLunga() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta troppo lunga, deve avere "
        + Wordle.getDimensioneParola() + " caratteri." + ls;
        Monitor.messaggi("parolalunga", Wordle.getDimensioneParola());
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'parolacorta'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParolaCorta() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "Parola segreta troppo corta, deve avere "
        + Wordle.getDimensioneParola() + " caratteri." + ls;
        Monitor.messaggi("parolacorta", Wordle.getDimensioneParola());
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'rivelaparola'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiRivelaParola() throws UnsupportedEncodingException {
        String ls = System.getProperty("line.separator");
        String msg = "La parola segreta e' "
        + Wordle.getParolaSegreta() + ls;
        Monitor.messaggi("rivelaparola", Wordle.getParolaSegreta());
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'numerotentativi'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiNumeroTentativi()
    throws UnsupportedEncodingException {
        Partita p = new Partita(Wordle.getMaxTentativi(), "PLUTO");
        String ls = System.getProperty("line.separator");
        String msg = "Numero tentativi: "
        + p.getTentativiEffettuati() + ls;
        Monitor.messaggi("numerotentativi", p.getTentativiEffettuati());
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di messaggi(String)
     * quando è passata la parola 'parametriparola'.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testMessaggiParametriParola()
    throws UnsupportedEncodingException {
        String msg = "\nLa parola da inserire deve avere lunghezza "
        + Wordle.getDimensioneParola() + " e deve\nessere composta"
        + " da soli caratteri dell'alfabeto:\n";
        Monitor.messaggi("parametriparola",  Wordle.getDimensioneParola());
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di stampaGriglia(Partita)
     * quando il tentativo è esatto.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testStampaGrigliaTentativoEsatto()
    throws UnsupportedEncodingException {
        Partita p = new Partita(Wordle.getMaxTentativi(), "LIMBO");
        p.setGrigliaTentativi(0, "LIMBO");
        String ls = System.getProperty("line.separator");
        String msg = ConsoleColors.WHITE + " ";
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
        String pTentata = "LIMBO";
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
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        Monitor.stampaGriglia(p);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }

    /**
     * Questo metodo testa il funzionamento di stampaGriglia(Partita)
     * quando il tentativo è errato.
     * @throws UnsupportedEncodingException
     */
    @Test
    public void testStampaGrigliaTentativoSbagliato()
    throws UnsupportedEncodingException {
        Partita p = new Partita(Wordle.getMaxTentativi(), "LIMBO");
        p.setGrigliaTentativi(0, "ALMBO");
        String ls = System.getProperty("line.separator");
        String msg = ConsoleColors.WHITE + " ";
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
        String pTentata = "ALMBO";
        msg += ConsoleColors.GREY + "  "
        + pTentata.charAt(0) + "  "
        + ConsoleColors.WHITE + "|"
        + ConsoleColors.RESET;
        msg += ConsoleColors.YELLOW + "  "
        + pTentata.charAt(1) + "  "
        + ConsoleColors.WHITE + "|"
        + ConsoleColors.RESET;
        for (int i = 2; i < Wordle.getDimensioneParola(); i++) {
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
            msg += "\n" + ConsoleColors.WHITE + " ";
            for (int j = 0; j < Wordle.getDimensioneParola(); j++) {
                msg += ConsoleColors.WHITE
                + "~~~~~ " + ConsoleColors.RESET;
            }
            msg += "\n";
        }
        Monitor.stampaGriglia(p);
        String res = outContent.toString(Charset.defaultCharset().toString());
        assertEquals(msg, res);
    }
}

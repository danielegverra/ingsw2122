package it.uniba.app;
/**
 * Questa classe è di tipo 'noECB'.
 * Si occupa di gestire i colori della matrice di tentativi.
 */
public final class ConsoleColors {

    private ConsoleColors() {

    }

    /**
     * RESET.
     */

    public static final String RESET = "\033[0m";  // Text Reset

    /**
     * GREY.
     */
    public static final String GREY = "\033[48;2;128;124;124m";
    /**
     * GREEN.
     */
    public static final String GREEN = "\033[48;2;108;169;104m";
    /**
     * YELLOW.
     */
    public static final String YELLOW = "\033[48;2;200;180;91m";
    /**
     * WHITE.
     */
    public static final String WHITE = "\033[48;2;255;255;255m";
    /**
     * DARK_WHITE.
     */
    public static final String DARK_WHITE = "\033[48;2;210;210;210m";
}

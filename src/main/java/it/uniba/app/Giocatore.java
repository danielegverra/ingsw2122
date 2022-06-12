package it.uniba.app;

import java.util.Scanner;

/**
 * Questa classe è di tipo 'Control'.
 * Si occupa di gestire le azioni che può svolgere il giocatore.
 */
public final class Giocatore {

    private Giocatore() {

    }

    /**
     * Questo metodo permette di partecipare ad una partita di Wordle,
     * al suo interno vengono richiesti i tentativi all'utente.
     * @param sc è lo scanner in input.
     */
    public static void iniziaPartita(final Scanner sc) {
        if (!Wordle.getParolaSegreta().equals("")) {
            String parola = Wordle.getParolaSegreta();
            int maxTent = Wordle.getMaxTentativi();
            Partita p = new Partita(maxTent, parola);
            Boolean partitaFinita = false;
            Monitor.messaggi("nuovapartita");
            Monitor.visualizzaRegole();
            Monitor.visualizzaComandi();
            Monitor.stampaGriglia(p);
            while (p.getTentativiEffettuati() < maxTent && !partitaFinita) {
                String parolaTentata;
                int lenght = p.getParola().length();
                parolaTentata = Manager.tentativo(sc, lenght);
                if (parolaTentata.equals("/ESCI")) {
                    return;
                } else if (parolaTentata.equals("/ABBANDONA")) {
                    return;
                } else {
                    int currentTent = p.getTentativiEffettuati();
                    p.setGrigliaTentativi(currentTent, parolaTentata);
                    p.setTentativiEffettuati(currentTent + 1);
                    Monitor.stampaGriglia(p);
                    if (parolaTentata.compareTo(p.getParola()) == 0) {
                        partitaFinita = true;
                        Monitor.messaggi("parolaindovinata");
                        Monitor.messaggi("numerotentativi", currentTent + 1);
                        p.setTentativiEffettuati(currentTent);
                    }
                }
            }

            if (p.getTentativiEffettuati() >= p.getMaxTentativi()) {
                Monitor.messaggi("numeromaxtentativi");
                Monitor.messaggi("rivelaparola", Wordle.getParolaSegreta());
            }
        } else if (Wordle.getParolaSegreta().equals("")) {
            Monitor.messaggi("nonesisteparola");
        }
    }
}

package it.uniba.app;

/**
 * Questa classe è di tipo 'Control'.
 * Si occupa di gestire le azioni che può svolgere il paroliere.
 */
public final class Paroliere {

    private Paroliere() {

    };

    /**
     * Questo metodo permette al paroliere di cambiare la parola segreta
     * da indovinare.
     * @param s è la parola segreta da indovinare che deve essere impostata
     */
    public static void impostaParolaSegreta(final String s) {
        Wordle.setParolaSegreta(s);
    }

    /**
     * Questo metodo permette al paroliere di visualizzare la parola
     * segreta corrente.
     */
    public static void  visualizzaParola() {
        String parola = Wordle.getParolaSegreta();

        if (!parola.equals("")) {
            Monitor.messaggi("rivelaparola", parola);
        } else {
            Monitor.messaggi("parolaassente");
        }
    }
}

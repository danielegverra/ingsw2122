package it.uniba.app;


/**
 * Questa classe è di tipo 'Entity'.
 * Si occupa di gestire le informazioni della partita.
 */
public final class Partita {

    /**
     * La variabile tentativiEffettuati
     * indica quante volte l'utente
     * ha provate ad indovinare la parola.
     */

    private int tentativiEffettuati;
    /**
     * La variabile maxTentativi
     * indica il numero massimo di
     * tentativi che l'utente ha a disposizione.
     */
    private int maxTentativi;
    /**
     * La variabile parola segreta
     * contiene la parola che l'utente
     * deve indovinare per vincere la partita.
     */
    private String parolaSegreta;
    /**
     * La variabile grigliaTentativi
     * è la griglia di visualizzazione
     * mostrata per ciascun tentativo.
     */
    private String[] grigliaTentativi;

    /**
    * Questo metodo è un metodo di accesso.
    * @return viene restituito il numeri
    * di tentativi effettuati dall'utente.
    */

    public int getTentativiEffettuati() {
        return tentativiEffettuati;
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare l'attributo dei tentativi
     * effettuati dall'utente.
     * @param tentEffettuati è il numero
     * di tentativi effettuati dall'utente
     * da settare.
     */

    public void setTentativiEffettuati(final int tentEffettuati) {
        this.tentativiEffettuati = tentEffettuati;
    }

    /**
     * Questo metodo è un metodo di accesso.
     * @return viene restituito il numero
     * massimo di tentativi che l'utente
     * ha durante la partita.
     */

    public int getMaxTentativi() {
        return maxTentativi;
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare l'attributo relativo
     * al numero massimo di tentativi
     * effettuabili dall'utente.
     * @param maxTent è il numero
     * massimo di tentativi da settare.
     */

    public void setMaxTentativi(final int maxTent) {
        this.maxTentativi = maxTent;
    }

    /**
     * Questo metodo è un metodo di accesso.
     * @return viene restituita la parola
     * segreta che deve essere indovinata.
     */

    public String getParola() {
        return parolaSegreta;
    }

    /**
     * Questo metodo fornisce la possibilità
     * di modificare l'attributo
     * parola segreta.
     * @param parola è la parola segreta
     * che deve essere settata.
     */

    public void setParola(final String parola) {
        this.parolaSegreta = parola;
    }

    /**
     * Questo è un metodo di accesso.
     * @param index indica l'indice dell'array
     * relativo al tentativo in questione.
     * @return viene restituito il tentativo
     * effettuato all'indice i.
     */

    public String getGrigliaTentativi(final int index) {
        return grigliaTentativi[index];
    }

    /**
     * Questo metodo modifica la griglia.
     * @param index indica l'indice dell'array
     * relativo al tentativo in questione.
     * @param tentativo indica il tentativo
     * effettuato dall'utente.
     */

    public void setGrigliaTentativi(final int index, final String tentativo) {
        grigliaTentativi[index] = tentativo;
    }

    /**
     * E' il costruttore della classe Partita
     * inizializza la parola segreta, il
     * numero massimo di tentativi e porta
     * a zero il numero di tentativi efffettuati.
     * @param maxTent è il numero massimo
     * di tentativi da essere settato.
     * @param parolaSeg è la parola da indovinare
     * da essere settata.
     */

    public Partita(final int maxTent, final String parolaSeg) {
        this.tentativiEffettuati = 0;
        this.maxTentativi = maxTent;
        this.parolaSegreta = parolaSeg;
        grigliaTentativi = new String[this.maxTentativi];
        for (int i = 0; i < this.grigliaTentativi.length; i++) {
            grigliaTentativi[i] = "";
        }
    }
}

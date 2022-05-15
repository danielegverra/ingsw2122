package it.uniba.app;


/**
 * Questa classe è di tipo 'Entity'.
 * Si occupa di gestire le informazioni della partita.
 */
public class Partita {
    
    /** 
    *   ATTRIBUTI
    */

    private int tentativiEffettuati;
    private int maxTentativi;
    private String parolaSegreta;
    private String[] grigliaTentativi;

    /** 
    * METODI ACCESSO
    */

    public int getTentativiEffettuati() {
        return tentativiEffettuati;
    }

    public void setTentativiEffettuati(int tentativiEffettuati) {
        this.tentativiEffettuati = tentativiEffettuati;
    }

    public int getMaxTentativi() {
        return maxTentativi;
    }

    public void setMaxTentativi(int maxTentativi) {
        this.maxTentativi = maxTentativi;
    }

    public String getParola() {
        return new String(parolaSegreta);
    }

    public void setParola(String parola) {
        this.parolaSegreta = new String(parola);
    }

    public String getGrigliaTentativi(int index) {
        return new String(grigliaTentativi[index]);
    }

    public void setGrigliaTentativi(int index, String tentativo) {
        grigliaTentativi[index] = new String(tentativo);
    }

    /**
     * METODI
     */
    public Partita(int maxTentativi, String parolaSegreta) {
        this.tentativiEffettuati = 0;
        this.maxTentativi = maxTentativi;
        this.parolaSegreta = new String(parolaSegreta);
        grigliaTentativi = new String[this.maxTentativi];
        for(int i=0; i<this.grigliaTentativi.length; i++){
            grigliaTentativi[i] = new String("");
        }
    }
}

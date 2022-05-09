package it.uniba.app;
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
        grigliaTentativi[index]= new String(tentativo);
    }
}

# INDICE
## 1. - <a href=#intro> INTRODUZIONE</a>
## 2. - <a href=#modd> MODELLO DI DOMINIO</a>
## 3. - <a href=#rspec> REQUISITI SPECIFICI</a>
#### 3.1 - <a href=#rnfunz> REQUISITI NON FUNZIONALI</a>
#### 3.2 - <a href=#rfunz> REQUISITI FUNZIONALI</a>
## 4. - <a href=#ood> OO DESIGN</a>

<br/><br/><br/>


<h2 id="intro"> 1. Introduzione </h2>

`Wordle` è un famoso gioco di proprietà di `The New York Times Company`, rilasciato nell'ottobre 2021 da Josh Wardle, che consiste nell'indovinare una parola di cinque lettere avendo a disposizione sei tentativi, ognuno dei quali fornisce delle informazioni al giocatore; in particolare per ogni lettera viene indicato se essa è corretta, presente ma nella posizione sbagliata o assente.<br/><br/>
Questo programma software, dotato di interfaccia a linea di comando (CLI), è stato sviluppato, nell'ambito del corso universitario di Ingegneria del Software, nella sua interezza dal `gruppo Stonebraker`, che prende il nome dall'omonimo vincitore del premio Turing Award del 2014 per il suo immenso contributo alla ricerca nell'ambito dei database.<br/>
Il gruppo è composto da Adriana Frascella, Nicola Lassandro, Matteo Castano, Daniele Guerra, Ugo De Santis;<br/>
Responsabile del progetto il Professore Filippo Lanubile.

<br/><br/><br/>


<h2 id="modd"> 2. Modello di dominio </h2>

![Diagramma concettuale](../drawings/ModelloDominio.png)

<br/><br/><br/>


<h2 id="rspec"> 3. Requisiti specifici </h2>
<h3 id="rnfunz"> 3.1 Requisiti non funzionali </h3>

In termini di **portabilità**:
+ Grazie a `Docker` la distribuzione dell'applicazione è automatizzata, tramite quest'ultimo è possibile ottenere un'astrazione a livello di sistema operativo che consente l'utilizzo dell'applicazione su qualunque macchina.<br/>
+ è possibile scaricare il programma tramite il comando da console:<br/>
    `docker pull ghcr.io/softeng2122-inf-uniba/wordle-stonebraker:latest`
+ è possibile eseguire il programma tramite il comando da console:<br/>
    `docker run -it --rm ghcr.io/softeng2122-inf-uniba/wordle-stonebraker:latest`

In termini di **usabilità**:
+ il corretto funzionamento dell'applicazione ha come requisito imprescindibile la codifica UTF-8 da parte della console utilizzata(Linux terminal, MacOS terminal, Windows Powershell e Git Bash, se si utilizza quest'ultimo il comando ha prefisso *winpty*).<br/>

In termini di **requisiti organizzativi**:
+ Il programma è stato realizzato applicando `Scrum`, una metodologia di sviluppo **agile**. Questa prevede cicli di sviluppo brevi (una/due settimane massimo) denominati sprint, ognuno dei quali vede l'introduzione di un certo numero di `user story` (funzionalità) richieste dal Product Owner.<br/>

<h3 id="rfunz"> 3.2 Requisiti funzionali </h3>

I requisiti cui l'applicazione risponde sono:<br/>
+ L'utente può visualizzare l'elenco dei comandi mediante il comando `/help`<br/>
+ L'utente può chiudere l'applicazione mediante il comando `/esci`<br/>
+ Il paroliere può impostare una nuova parola segreta mediante il comando `/nuova
    [parola]`<br/>
+ Il paroliere può mostrare la parola segreta mediante il comando `/mostra`<br/>
+ Il giocatore può iniziare una nuova partita mediante il comando `/gioca`<br/>
+ Il giocatore, all'interno della partita, può `effettuare un tentativo`
    semplicemente inserendo una parola <br/>
+ Il giocatore può abbandonare la partita corrente mediante il comando 
    `/abbandona`<br/>

<br/><br/><br/>


<h2 id="ood"> 4. OO Design </h2>
Analizzando le varie necessità e documentazioni abbiamo ritenuto necessario dividere il nostro progetto in 5 classi:<br/>

+ `Giocatore` e `Paroliere` che si occupano di gestire i comandi di interesse, rispettivamente, dei due tipi di utente;<br/>
+ `Wordle` gestisce il funzionamento totale del gioco;
+ `Partita` gestisce le caratteristiche della singola partita;<br/>
+ `Manager` che si occupa dell'acquisizione dei comandi dati in input dall'utente.<br/>
+ `Monitor` che si occupa della restituzione a schermo dei messaggi di output.<br/><br/>

Abbiamo individuato alcune `user story di maggiore rilevanza` di cui sono riportati diagrammi delle classi e diagrammi di sequenza:<br/>

<br/>

<h3>impostaParolaSegreta </h3>

![Classi_imposta](../drawings/DiagrammaClassi_impostaParolaSegreta.png)

![Sequenza_imposta](../drawings/DiagrammaSequenza_impostaParolaSegreta.PNG)

<br/>

<h3>visualizzaParola </h3>

![Classi_visualizza](../drawings/DiagrammaClassi_visualizzaParola.png)

![Sequenza_visualizza](../drawings/DiagrammaSequenza_visualizzaParola.PNG)

<br/>

<h3>iniziaPartita / Effettuare Tentativo </h3>

![Classi_inizia](../drawings/DiagrammaClassi_iniziaPartita-tentativo.png)

![Sequenza_inizia](../drawings/DiagrammaSequenza_iniziaPartita-tentativo.PNG)

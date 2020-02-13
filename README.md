# AnAVIS

## Vision

Il sistema sviluppato consente ai donatori Avis di prenotare le donazioni on-line, di compilare il modulo cartaceo e di visualizzare lo storico delle donazioni effettuate, in modo tale da semplificare e velocizzare tutta la procedura di prenotazione.

Attualmente, per poter prenotare una donazione del sangue, i donatori devono telefonare o recarsi in una sede la quale riferirà loro i giorni e gli orari disponibili presso quella sede. Inoltre, ad ogni donazione, prima del prelievo si deve compilare un modello cartaceo in cui si attestano le proprie condizioni di salute.

Il sistema proposto rende più veloce la pratica di prenotazione e risulta più facile per i donatori scegliere fra tutte le date e gli orari disponibili nelle varie sedi Avis presenti nelle vicinanze. Per quanto riguarda il modello esso sarà obbligatorio per il donatore che vuole effettuare una prenotazione e sarà disponibile per la compilazione on-line richiedento principalmente i dati relativi allo stato di salute che di volta in volta potrebbero cambiare.

L'applicativo è altresì rivolto alle sedi Avis in quanto interagiranno con il sistema per inserire gli orari e le date disponibili per le donazioni, consentendo loro di risparmiare tempo in quanto le prenotazioni, visualizzate in un elenco, verranno sempre più gestite dai donatori.

La piattaforma consente inoltre ai centri trasfusionali di inviare delle richieste di "emergenza sangue” con relativo gruppo sanguigno ai donatori che si fossero resi disponibili per tali evenienze mostrandole nel profilo dell'utente, spronandolo magari a dare un contributo ed aiutando chi ne ha bisogno.

Infine il sistema permette la visualizzazione delle news più recenti reletive al mondo Avis e consente agli utenti di trovare le risposte alle domande più frequenti reletive alla donazione del sangue. 

## Tecnologie e struttura del progetto

Il progetto utilizza un'architettura layered costiutita da tre strati:

#### Backend:

- Il framework utilizzato è Spring Boot
- Il carico del lavoro è stato suddiviso usando Controller, Service, Repository ed Entity permettendo cosi di ottimizzare le responsabilità
- Fornisce chiamate API REST per l'interazione con il frontend
- L'autenticazione e l'autorizzazione sono implementati tramite Spring Security e attraverso l'utilizzo di token JWT

#### Frontend:

- Il framework utilizzato è React
- L'interazione con il backend viene gestita utilizzando la libreria Axios
- Le chiamate al backend vengono intercettate e rese sicure JWT
- Viene utilizzato il Feed RSS per visulizzare le news recenti del sito Avis

#### Persistenza

- Per la mappatura degli oggetti Java in Database relazionali viene utilizzato Hibernate
- Il database utilizzato è MySql in localhost

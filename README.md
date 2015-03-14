# FastMarket

#Panoramica dell’applicazione

L'applicazione web FastMarket è stata progettata per consentire ad una determinata catena di supermercati di mettere in vendita on-line i propri prodotti. A tal fine, consentirà all'utente di scegliere se acquistare o prenotare online diverse tipologie di articoli, tra cui piatti pronti di vario tipo da ritirare durante degli orari prestabiliti nonché cibi per intolleranti. Al momento dell’acquisto l’utente potrà scegliere se:
- ritirare la spesa direttamente in negozio, attraverso uno sportello dedicato (servizio “prenota e ritira”);
- ricevere la spesa a casa (servizio di consegna a domicilio pensato per coprire la zona circoscritta de L’Aquila e dintorni, attualmente inesistente nel Web. Il sistema è stato tuttavia progettato per essere scalabile).
Accedendo a FastMarket è possibile sfogliare il catalogo dei prodotti in vendita, utilizzando anche diversi filtri di ricerca (marca, categoria, tipologia di intolleranza), visualizzare tutte le caratteristiche di un determinato articolo, accedere direttamente alle offerte disponibili o accedere ad un’apposita sezione “Ricette del giorno”.
Il punto di forza dell’applicazione è costituito dalla sua alta usabilità, infatti, l’applicazione web permetterà all’utente di accedere ai servizi con estrema facilità anche tramite dispositivo mobile.
Presentazione degli attori che interagiscono attivamente con il sistema:
  - User;
  - Registered user;
  - Employee: un attore astratto che rappresenta concettualmente qualsiasi dipendente del supermercato, creato per rappresentare le funzionalità a cui tutti i dipendenti possono accedere.
I dipendenti del supermercato, che possono usufruire totalmente o parzialmente delle funzionalità di backoffice, sono stati divisi per ruolo:
  - Site admin: l’utente amministratore del sito è in grado di accedere a tutte le funzionalità del backoffice del sito, ovvero le funzionalità a cui possono accedere anche gli altri dipendenti del supermercato più la gestione dei dipendenti del supermercato e degli utenti registrati al sito;
  - Sales manager: dipendente incaricato nella gestione degli ordini (ovvero sia delle consegne a domicilio che delle prenotazioni), la gestione degli articoli e delle e-mail per assistenza tecnica e servizio clienti.
  - Web-marketing manager: dipendente incaricato di gestire le newsletter, le e-mail promozionali, le informazioni del sito e la sezione ricette.
Il sistema dovrà, dunque, prevedere uno strumento di backoffice accessibile agli utenti autorizzati del supermarket e differenziato in base alla tipologia di ruolo svolto, ovvero: l’amministratore del sito, il responsabile vendite e il responsabile web-marketing.

#Specifiche funzionali
Di seguito presentiamo l’elenco delle funzionalità implementate nell’applicazione suddivise per attore:
Gli utenti del sito:
User:
- Il sistema consentirà all’utente di effettuare la registrazione, inserendo tutte le informazioni personali, creando quindi un proprio profilo.
- Il sistema permetterà la ricerca degli articoli del supermarket:
  - per categoria (latte, detersivi o quant’altro);
  - per tipologia d’intolleranza (celiachia, nefropatia o quant’altro);
  - per marca (Barilla, Dixan o quant’altro);
- Il sistema permetterà di visualizzare per ogni articolo le rispettive caratteristiche e descrizione.
Registered user:
- All’utente registrato, dopo aver effettuato l’autenticazione, sarà possibile modificare il proprio profilo dal sistema.
Gli utenti amministratori del backoffice (i dipendenti):
Sales manager:
- L’utente con il ruolo di “Responsabile vendite” gestirà l’inserimento, la cancellazione e la modifica degli articoli del supermercato.
Web-marketing manager:
- L’utente con il ruolo “Responsabile vendite” sarà addetto alla gestione delle informazioni presenti all’interno del sito.
Site admin:
Oltre a fare tutto ciò che fanno gli altri utenti amministratori del backoffice:
- L’utente con il ruolo di “Amministratore del sito” sarà incaricato di gestire i dipendenti del negozio (sarà l’unico in grado di aggiungere o eliminare i profili dei dipendenti).
- Tale dipendente sarà anche incaricato di gestire gli utenti registrati e di avere accesso ai loro dati in caso di problemi con i pagamenti o le prenotazioni.
Inoltre:
- Ciascun dipendente sarà in grado di autenticarsi e modificare il proprio profilo dal sistema;

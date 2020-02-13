import React, { Component } from 'react';

class GestioneDatePages extends Component {

    constructor(props) {
        super(props)
        this.state = {
        }
    }

    //-----Render
    render() {
        return (
            <div>
                <div className="container">
                    <h1>FAQ</h1>
                    <h3>Domande frequenti per chi vuole iniziare a donare il sangue</h3>

                    <div id="accordion">

                        <Card
                            n_card={"Q1"}
                            title={"1) Per quale motivo dovrei donare il mio sangue?"}
                            text={"Il sangue umano è un prodotto naturale, non riproducibile artificialmente e indispensabile alla vita. Donare il sangue è un atto volontario e gratuito, è un dovere civico, è una manifestazione concreta di solidarietà verso gli altri, esalta il valore della vita, abbatte le barriere di razza, religione o ideologia e rappresenta uno dei pochi momenti di vera medicina preventiva. È un atto di estrema generosità che permette di salvare la vita di altre persone. Proprio il fatto che il sangue sia raro implica la necessità di metterlo a disposizione di altri individui che potrebbero trovarsi in situazione di bisogno. Pensa di essere tu al loro posto.."}
                        />
                        <Card
                            n_card={"Q2"}
                            title={"2) A me non importa nulla: se ho bisogno, il sangue lo pago"}
                            text={"Nonostante i progressi della medicina, delle scienze e della biochimica, l'uomo rimane a tutt'oggi l'unica possibile sorgente di sangue, e pertanto: nessun Ospedale è in grado di assicurare alcuna terapia trasfusionale senza la preventiva disponibilità dei donatori; per lo stesso motivo, la disponibilità del \"bene sangue\" non dipende dal mercato, quindi non ha un prezzo economico; per le ragioni esposte nei punti sopra, le Istituzioni Pubbliche (Stato, Regioni) devono contribuire con campagne di sensibilizzazione verso la popolazione e fornire gli strumenti normativi per garantire la massima sicurezza possibile e l'ottimizzazione del sistema trasfusionale in tutte le sue articolazioni."}
                        />
                        <Card
                            n_card={"Q3"}
                            title={"3) Tutti possono diventare donatori?"}
                            text={"Chiunque abbia compiuto i 18 anni di età e pesi più di 50 kg. può presentarsi presso una qualsiasi sede AVIS. Un medico effettuerà un colloquio, una visita, e gli accertamenti di tipo diagnostico e strumentale per verificare che non vi siano controindicazioni alla donazione. La tutela della salute e della sicurezza sia del donatore che del ricevente sono fondamentali."}
                        />
                        <Card
                            n_card={"Q4"}
                            title={"4) Per quali motivi potrei risultare non idoneo a diventare donatore o essere escluso dalla donazione?"}
                            text={"Le cause per le quali una persona può essere valutata non idonea o sospesa sono molteplici e tutte determinate dal principio di salvaguardare la salute sia del donatore sia del ricevente. Alcuni esempi sono: Esclusione permanente per: malattie autoimmuni, cardiovascolari, del sistema nervoso centrale; neoplasie o malattie maligne; diabete insulino – dipendente; alcuni tipi di malattie infettive (epatite B, C, ad eziologia indeterminata, AIDS, ecc.), alcolismo cronico; assunzione di droghe; comportamenti sessuali ad alto rischio di trasmissione di malattie infettive; tendenza anomala all'emorragia. Esclusione temporanea per: periodi variabili da settimane ad anni in caso di:tubercolosi, toxoplasmosi, esposizione accidentale al sangue o a strumenti contaminati; trasfusione di sangue o di emocomponenti o di plasmaderivati; endoscopia, trapianto di tessuti o cellule, intervento chirurgico di rilievo; agopuntura, piercing, tatuaggi, rapporti sessuali occasionali a rischio, viaggi, vaccinazioni. Inoltre per condizioni legate alla visita (per esempio valori di pressione arteriosa troppo alti o bassi) o agli esami effettuati (esempi, valori di emoglobina o ferro bassi,esami del fegato elevati, positività dei marcatori virali, ecc.), ed eventualmente altro a giudizio del medico."}
                        />
                        <Card
                            n_card={"Q5"}
                            title={"5) Donare sangue è dannoso per la salute?"}
                            text={"Grazie all'accurata selezione per un adulto sano la donazione di sangue non comporta alcun rischio. Esistono precise disposizioni che regolano la raccolta del sangue: la quantità del sangue che viene prelevata mediamente ad ogni prelievo è minima ed è stabilita per legge in 450 centimetri cubi +/- 10%. Tra una donazione di sangue intero e l'altra devono trascorrere almeno 90 giorni. La frequenza annua delle donazioni non deve essere superiore a 4 nell'uomo e a 2 nelle donne in età fertile. I controlli e le visite periodiche effettuate a ciascun donatore prima di ogni donazione sono uno strumento di medicina preventiva, a tutela dello stato di salute generale del donatore."}
                        />
                        <Card
                            n_card={"Q6"}
                            title={"6) Per le donne (già soggette alla perdite mestruali) donare sangue non è dannoso?"}
                            text={"La donazione di sangue per le donne non ha alcuna controindicazione. Tuttavia in virtù delle perdite legate alle mestruazioni le donne in età fertile possono effettuare solo un massimo di due donazioni di sangue intero l'anno. Il monitoraggio costante della emoglobina, effettuata prima di ogni donazione, e del ferro, tutelano la salute delle donatrici. Le donne risultano essere particolarmente \"adatte\" alla donazione di plasma in aferesi che non incide assolutamente sui globuli rossi ed il ferro."}
                        />
                        <Card
                            n_card={"Q7"}
                            title={"7) Bisogna essere a digiuno per donare sangue?"}
                            text={"Il mattino del prelievo è preferibile essere a digiuno o aver fatto una colazione leggera a base di frutta fresca o spremute, thè o caffè poco zuccherati, pane non condito o altri carboidrati. Le donne che hanno in corso una terapia anticoncezionale non devono sospenderne l'assunzione quotidiana."}
                        />
                        <Card
                            n_card={"Q8"}
                            title={"8) La privacy dei risultati delle mie analisi è garantita?"}
                            text={"Il segreto medico e la legge sulla \"Privacy\", che individua le \"figure\" responsabili al trattamento dei dati in questione assicura la massima discrezionalità e segretezza di tutti gli aspetti sanitari e dei risultati delle analisi effettuate."}
                        />
                        <Card
                            n_card={"Q9"}
                            title={"9) Perché i donatori AVIS sono periodici?"}
                            text={"trasfusionali per donare il loro sangue. A differenza dei donatori occasionali i donatori periodici sono molto controllati dal punto di vista medico, vengono costantemente sottoposti ad un'accurata visita e ad attenti controlli sul loro sangue e poiché la loro scelta di donare è libera, non condizionata da altri fattori come quelli emozionali, risultano molto più affidabili dei donatori occasionali. I donatori Avis sono inoltre anonimi, volontari non retribuiti, responsabili. Il ricorso ai donatori periodici consente inoltre: maggiore programmazione della raccolta del sangue possibile \"conversione\" dalla donazione tradizionale di sangue intero a quella differenziata mediante aferesi gestione anche delle situazioni di urgenze - emergenze di effettuare educazione sanitaria e promozione della salute"}
                        />
                        <Card
                            n_card={"Q10"}
                            title={"10) Cos'è la donazione di plasma mediante aferesi?"}
                            text={"Oggi è possibile effettuare diversi tipi di donazione: oltre a quelle tradizionale di sangue intero, si possono effettuare donazioni mirate (dette aferesi) cioè solo di alcuni componenti del sangue e, tra questi, il plasma. Nell'aferesi (termine greco che significa l'atto del \"portar via\"), attraverso l'uso di separatori cellulari, si ottiene dal sangue del donatore soltanto la componente ematica di cui si ha necessità (plasma, piastrine,…), restituendogli contemporaneamente i restanti elementi. Ciascun separatore cellulare centrifuga o filtra il sangue che defluisce da un braccio del donatore trattenendo il componente ematico necessario e restituendogli il rimanente. Si parla di plasmaferesi se si prelava solo plasma, di piastrinoaferesi se si prelavano solo piastrine, di plasmapiastrinoaferesi se si prelavano plasma e piastrine, ecc. Una volta raccolto, il plasma viene conservato diversamente dal sangue intero e dai concentrati di globuli rossi, essendo congelato (se a temperatura inferiore a – 30° C) può essere utilizzato per un periodo massimo di 12 mesi. Il sangue è composto per il 45% circa di cellule, la parte corpuscolata, e per il 55% circa di plasma, la parte liquida. Le funzioni del plasma sono numerose: mantiene costante il volume di sangue circolante, porta ai tessuti e alle cellule sostanze prevalentemente di tipo nutritivo e di regolazione (ormoni, vitamine), raccoglie tutte le sostanze di rifiuto derivanti dal metabolismo delle cellule e le elimina attraverso i reni e il sudore, interviene nei processi di difesa immunologica e nella coagulazione."}
                        />
                        <Card
                            n_card={"Q11"}
                            title={"11) Che cos'è l'autotrasfusione?"}
                            text={"L'autotrasfusione è una procedura trasfusionale che consiste nel trasfondere al soggetto unità del suo stesso sangue e si realizza con una delle seguenti modalità: predeposito recupero perioperatorio emodiluizione normovolemica Il metodo più utilizzato è il predeposito, una tecnica trasfusionale con la quale si preleva il sangue dal donatore che sarà anche ricevente, per compensare le perdite di sangue che si possono verificare nel corso di interventi chirurgici programmati. Alcuni giorni prima dell'intervento vengono prelevate unità di sangue dal paziente, in fasi successive, fino a raggiungere la quantità prevedibilmente necessaria, in modo da consentirne l'eventuale utilizzo durante l'intervento operatorio o nel post-intervento. I principali vantaggi dell'autotrasfusione sono: eliminazione delle reazioni di incompatibilità eliminazione del rischio di trasmissione di malattie infettive riduzione del rischio di immunizzazione da antigeni diversi, con possibili manifestazioni a distanza risparmio di sangue"}
                        />
                        <Card
                            n_card={"Q12"}
                            title={"12) Con quale denaro funziona l'AVIS?"}
                            text={"L'AVIS è una associazione di volontari: nessun socio impegnato nell'associazione a qualunque titolo e con qualunque funzione, percepisce compensi. Sono stipendiati invece i dipendenti che svolgono un lavoro permanente nell'associazione. L'Avis sostiene economicamente la propria azione (spese per la promozione della donazione, per l'invio dei donatori alle strutture Trasfusionali e/o per la raccolta diretta delle unità di sangue, ecc.) con i rimborsi, stabiliti da un decreto ministeriale ed erogati, per convenzione, dalle Aziende Sanitarie e/o Ospedaliere . Altre fonti di finanziamento sono costituite da contributi di Enti Locali e donazioni private."}
                        />
                        <Card
                            n_card={"Q13"}
                            title={"13) Quali vantaggi ho ad iscrivermi all'AVIS?"}
                            text={"Un nostro slogan recita \"donare sangue: una scelta per gli altri, una scelta per se stessi\". A livello individuale si ha la gratificazione morale di concorrere alla soluzione di un grave problema e l'orgoglio di appartenere ad una componente attiva del volontariato socio-sanitario, decisiva per la costruzione del sistema trasfusionale. Inoltre, donare regolarmente sangue garantisce al donatore un controllo costante del proprio stato di salute attraverso visite mediche ed accurati esami di laboratorio, eseguiti ad ogni prelievo."}
                        />
                        <Card
                            n_card={"Q14"}
                            title={"14) Ogni anno sento parlare di carenza estiva, ma non ci pensano i donatori?"}
                            text={"La carenza di sangue nei mesi estivi è purtroppo un dato di fatto: in Italia in questi mesi, ma sempre più anche nel corso dell'intero anno, si rilevano forti diminuzioni nella raccolta di sangue mentre il bisogno di emocomponenti rimane stabile. La partenza per le vacanze contribuisce a interrompere i consueti flussi di raccolta. E' necessario quindi disporre di un adeguato numero di donatori periodici sui quali poter contare tutto l'anno, festività e vacanze comprese. Per questa ragione AVIS, da anni ha avviato un'attività di sensibilizzazione per cercare di garantire l'afflusso dei donatori a intervalli regolari presso le strutture trasfusionali, e ridurre il ricorso alle donazioni occasionali e sostitutive."}
                        />


                    </div>
                </div >
            </div >
        );
    }
}

function Card(props) {
    return (
        <div className="card">
            <div className="card-header">
                <a className="collapsed card-link" data-toggle="collapse" href={"#" + props.n_card}>
                    {props.title}
                </a>
            </div>
            <div id={props.n_card} className="collapse" data-parent="#accordion">
                <div className="card-body">
                    {props.text}
                </div>
            </div>
        </div>

    );
}

export default GestioneDatePages
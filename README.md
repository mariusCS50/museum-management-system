# Proiect: Aplicație turistică

## Prezentare generală

Acest proiect simulează un sistem de gestionare a muzeelor în care muzeele pot notifica ghizii înregistrați despre evenimente. Sistemul este conceput pentru a gestiona datele despre muzee, grupuri de vizitatori și evenimente.

## Descrierea funcționalității

## Cum funcționează codul

Codul din `Main` începe prin a goli instanța singleton (`Database`) pentru a asigura că nu rămân date din testele precedente (se strică testele între ele dacă nu este curățat database-ul).

Apoi, se selectează strategia de procesare a fișierelor corespunzătoare pe baza primului argument (`museums`, `groups` sau `listener`).

Odată ce strategia este selectată, metoda `processFile` este apelată cu argumentele rămase, care reprezintă căile către fișierele ce trebuie procesate.

Fiecare implementare a strategiei (de exemplu, `MuseumProcessingStrategy`, `GroupProcessingStrategy`, `EventProcessingStrategy`) citește fișierul de intrare corespunzător, parsează comenzile sau datele și efectuează operațiunile necesare (`ListenerProcessingStrategy` este mai mult ca un wrapper, el execută toate celelalte 3 strategii).

## Design Pattern-uri Utilizate

### Observer

Pentru a implementa notificarea ghizilor despre evenimentele din muzee, am ales să folosesc design pattern-ul Observer. Acesta mi-a permis să stabilesc o relație între muzee și ghizi, astfel încât, atunci când un muzeu adaugă un eveniment, toți ghizii care sunt parte din grupurile turistice asociate muzeului sunt notificați automat. Muzeele acționează ca subiecte care gestionează o listă de observatori (ghizii) și îi notifică atunci când apar evenimente noi.

### Singleton

Am ales să folosesc design pattern-ul Singleton pentru clasa Database, deoarece trebuie să existe o singură instanță a bazei de date pe întreaga aplicație. Singleton asigură că există o instanță unică și că accesul la această instanță este controlat, evitând instanțierea multiplă a obiectului.

### Factory

Am utilizat pattern-ul Factory Method pentru a crea obiecte de tipuri diferite de persoane, cum ar fi studenți și profesori, în cadrul grupurilor turistice. Acesta mi-a permis să decuplez crearea instanțelor de tipuri concrete de Person de restul aplicației, având astfel o metodă centralizată pentru crearea obiectelor.
### Builder

Am folosit Builder pentru a crea obiectele Museum și Location, având în vedere că acestea au multe atribute opționale care pot face constructorii lor complexi. Utilizarea unui Builder mi-a permis să creez obiectele într-un mod clar și ușor de citit, fără a fi nevoit să gestionăm constructori cu mulți parametri sau să avem multe combinații de valori implicite.
### Strategy

Am ales să folosesc design pattern-ul Strategy pentru a separa logica de procesare a fișierelor în funcție de tipul acestora (museum, group, listener). În funcție de primul argument al metodei main(), sunt create diferite strategii pentru procesarea fișierelor (MuseumProcessingStrategy, GroupProcessingStrategy, ListenerProcessingStrategy). Astfel, fiecare strategie se ocupă de logica specifică pentru procesarea unui tip de fișier, iar prin utilizarea acestui pattern am obținut un cod mai flexibil și ușor de extins.
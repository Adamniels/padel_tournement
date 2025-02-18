# padel_tournement

# TODO:

- [x] vill ha en meny(egentligen två) en där man kan starta nya matcher och sen där man kan uddatera dem
  - [x] göra klar playoff meny (kanske hoppa till nästa, för allt detta kan behövas göras om)
- [x] refactor, ska ha en tournement klass istället som innehåller grund spel och slutspel då kan jag loada och savea med den
  - [x] saker gör egentligen samma sak, visa och uppdater, kan man ha något abstrakt högre klass för dessa två
        så kan man bara köra dem med hjälp av den
- [x] bygga litet bibliotek för att säkerställa input är korrekt

- [ongoing] Kunna spara allting ifall man vill återuppta senare
  - [ ] kankse bygga en egenklass som sköter att spara och återställa allting till en json fil
        kan ta in tournement object
- [ ] vill kunna köra 5 lag men verkar inte funka i nuläget, alla gånger iaf
- [ ] göra det mer skalbart
- [ ] komma på ett sätt så att ett lag inte kan spela i båda sista matcherna och får jämna pauser

package padel;

import java.util.ArrayList;
import java.util.List;

public class Playoffs {
    private Match finalMatch;
    private Match bronzeMatch;
    private List<Match> semifinal;
    private List<Match> quarterfinals;

    public Playoffs(List<Team> standings) {
        this.semifinal = new ArrayList<>();
        this.quarterfinals = new ArrayList<>();
        this.bronzeMatch = null;
        if (standings.size() == 2) {
            this.finalMatch = new Match(standings.getFirst(), standings.getFirst());
        } else if (standings.size() == 4) {
            Match semi1 = new Match(standings.getFirst(), standings.getLast());
            Match semi2 = new Match(standings.getFirst(), standings.getLast());
            this.semifinal.add(semi1);
            this.semifinal.add(semi2);
        } else if (standings.size() == 8) {
            Match querter1 = new Match(standings.getFirst(), standings.getLast());
            Match querter2 = new Match(standings.getFirst(), standings.getLast());
            Match querter3 = new Match(standings.getFirst(), standings.getLast());
            Match querter4 = new Match(standings.getFirst(), standings.getLast());
            this.quarterfinals.add(querter1);
            this.quarterfinals.add(querter2);
            this.quarterfinals.add(querter3);
            this.quarterfinals.add(querter4);
        } else {
            // to many or few teams, something is wrong
            System.out.println("Something went wrong to many or few teams in the playoff");
        }
    }

    /**
     * Get the matches based on which round we are on
     */
    public void showMatchesRound() {
        if (!quarterfinals.isEmpty()) {
            // there is matches inside querterfinal so play them
        } else if (!semifinal.isEmpty()) {
            // we are on the semifinal
        } else {
            // we are in the final round

            if (bronzeMatch != null) {
                // we might or might not have a bronzematch
            }
        }
    }

    public void updateRound() {
        // gå igenom och upatera alla först
        // sedan kan vi gå igenom matcherna och ta ut vinnarna
        // skapa ny matcher och lägga dem ett steg upp
        // nollställ sedan rundan vi är på
    }
}

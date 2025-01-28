package padel;

import java.util.List;

public class Playoffs {
    private Match finalMatch;
    private Match bronzeMatch;
    private List<Match> semifinal;
    private List<Match> quarterfinals;

    public Playoffs(List<Match> matches) {
        if (matches.size() == 1) {
            this.finalMatch = matches.getFirst();
        } else if (matches.size() == 2) {
            this.semifinal = matches;
        } else if (matches.size() == 4) {
            this.quarterfinals = matches;
        }
    }

    public void playRound() {
        // TODO: beroende på vilken lista som har matcher i sig
        // borde kunna börja nerifrån, hur kan jag återanvända det jag har
        // i tournement? jag kanske kan returnera listan med matcher
        // och bara använda denna som något sorts bibliotek???
    }
}

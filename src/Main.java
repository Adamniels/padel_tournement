
import padel.Playoffs;
import padel.Tournement;
import padel.Team;
import java.util.List;

public class Main {
    private static int countRounds(int teams, int courts) {
        int matches = (teams * (teams - 1)) / 2;
        System.out.println("matches: " + matches);
        int rounds = (int) ((matches / 2.0) + 0.5);
        return rounds;
    }

    private static void eventLoop() {
        // create a tournement
        Tournement tournement = new Tournement();
        tournement.addTeam("team1", "a", "b");
        tournement.addTeam("team2", "c", "d");
        tournement.addTeam("team3", "e", "f");
        tournement.addTeam("team4", "g", "h");
        // tournement.addTeam("team5", "i", "j");
        // tournement.addTeam("team6", "k", "l");

        int teams = 4;
        int courts = 2;

        int rounds = countRounds(teams, courts);
        System.out.println("rounds: " + rounds);
        tournement.startTournement();
        for (int i = 0; i < rounds; i++) {
            tournement.playNewMatches();
            // tournement.updateOngoingMatches();
            // tournement.printStanding();
        }
        System.out.println("RESULT PRELIMINARY ROUND:");
        tournement.printStanding();

        // starta slutspelet
        List<Team> standings = tournement.getStandings();
        Playoffs playoffs = new Playoffs(standings);

    }

    public static void main(String[] args) {
        System.out.println("PADEL TOURNEMENT");
        eventLoop();
    }
}

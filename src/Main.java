
import padel.Playoffs;
import padel.Tournement;
import padel.Team;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int countRounds(int teams, int courts) {
        int matches = (teams * (teams - 1)) / 2;
        System.out.println("matches: " + matches);
        int rounds = (int) ((matches / 2.0) + 0.5);
        return rounds;
    }

    private static int countPlayoffsRounds(int teams) {
        int playoffTeams;
        if (teams < 4) {
            playoffTeams = 2;
        } else if (teams < 8) {
            playoffTeams = 4;
        } else {
            playoffTeams = 8;
        }

        double temp = Math.log(playoffTeams) / Math.log(2);
        int playoffsRounds = (int) temp;

        return playoffsRounds;
    }

    private static void eventLoop() {
        Scanner scanner = new Scanner(System.in);
        // create a tournement and add teams manually
        Tournement tournement = new Tournement();
        tournement.addTeam("team1", "a", "b");
        tournement.addTeam("team2", "c", "d");
        tournement.addTeam("team3", "e", "f");
        tournement.addTeam("team4", "g", "h");
        tournement.addTeam("team5", "i", "j");
        tournement.addTeam("team6", "k", "l");
        // tournement.addTeam("team7", "m", "n");
        // tournement.addTeam("team8", "o", "p");

        // set parameters
        int teams = 6;
        int courts = 2;

        int rounds = countRounds(teams, courts);

        System.out.println("rounds: " + rounds);
        tournement.startTournement();
        for (int i = 0; i < rounds; i++) {
            tournement.playNewMatches();
            System.out.println("Matches finished?");
            scanner.nextLine();
            tournement.updateOngoingMatches();
            System.out.println("Do you want to print the standings? y/n");
            String ans = scanner.nextLine();
            if (ans.equals("y")) {
                tournement.printStanding();
            }
            System.out.println("play next round?");
            scanner.nextLine();
        }
        System.out.println("RESULT PRELIMINARY ROUND:");
        tournement.printStanding();

        // starta slutspel
        List<Team> standings = tournement.getStandings();
        Playoffs playoffs = new Playoffs(standings);

        int playoffRounds = countPlayoffsRounds(teams);
        System.out.println("rounds playoffs: " + playoffRounds);
        for (int j = 0; j < playoffRounds; j++) {
            playoffs.showMatchesRound();
            System.out.println("Matches finished?");
            scanner.nextLine();
            playoffs.updateRound();
        }

    }

    public static void main(String[] args) {
        System.out.println("PADEL TOURNEMENT");
        eventLoop();
    }
}

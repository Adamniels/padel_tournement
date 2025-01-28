package padel;

import java.util.*;

public class Tournement {
    // Standings strukur
    List<Team> teams;
    List<Match> playedMatches;
    List<Match> ongoingMatches;
    List<Match> nextMatches;
    Playoffs playoff;

    public Tournement() {
        // create a new standings object
        teams = new ArrayList<>();
        // create arrays for matches
        this.playedMatches = new ArrayList<>();
        this.ongoingMatches = new ArrayList<>();
        this.nextMatches = new ArrayList<>();
    }

    public void addTeam(String teamName, String mem1, String mem2) {
        Team team = new Team(teamName, mem1, mem2);
        teams.add(team);
    }

    public void startTournement() {
        // här kan jag skapa alla matcher som ska köras, sen är det bara att ta ut lag
        // efter den
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Match match = new Match(teams.get(i), teams.get(j));
                nextMatches.add(match);
                // System.out.println("added match " + match);
            }
        }
    }

    // TODO: måste ta in en parameter med olka antal banor som finns tillgängliga
    // för det är det som avgör hur många matcher vi kan köra varje runda
    public void playNewMatches() {
        Match match1 = nextMatches.removeFirst();
        Match match2 = null;
        ArrayList<String> teamsMatch1 = new ArrayList<>(Arrays.asList(match1.team1.teamName, match1.team2.teamName));
        for (int i = 0; i < nextMatches.size(); i++) {
            Match match = nextMatches.get(i);
            String team1 = match.team1.teamName;
            String team2 = match.team2.teamName;
            if (!teamsMatch1.contains(team1) && !teamsMatch1.contains(team2)) {
                match2 = nextMatches.remove(i);
                break;
            }
        }
        if (match2 != null) {
            System.out.println("NEXT MATCHES");
            System.out.println("Match 1: " + match1);
            System.out.println("Match 2: " + match2);
            ongoingMatches.add(match1);
            ongoingMatches.add(match2);

        } else {
            System.out.println("NEXT MATCHES");
            System.out.println("Match 1: " + match1);
            ongoingMatches.add(match1);
        }
    }

    public void updateOngoingMatches() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("size of ongoing: " + ongoingMatches.size());

        for (int i = 0; i < ongoingMatches.size(); i++) {
            Match match = ongoingMatches.get(i);
            System.out.println("Score " + match);
            System.out.print("team " + match.team1 + ": ");
            int team1score = scanner.nextInt();

            System.out.print("team " + match.team2 + ": ");
            int team2score = scanner.nextInt();
            System.out.println("");

            match.updateMatch(team1score, team2score);

            String winner = match.getWinner();
            int scoreDiff = match.getScore();
            if (match.team1.teamName.equals(winner)) {
                // team1 vann
                match.team1.updateTeamStats(scoreDiff, 1);
                match.team2.updateTeamStats(-scoreDiff, 0);
            } else if (match.team2.teamName.equals(winner)) {
                // team 2 vann
                match.team2.updateTeamStats(scoreDiff, 1);
                match.team1.updateTeamStats(-scoreDiff, 0);
            } else {
                // draw
                match.team1.updateTeamStats(scoreDiff, 0);
                match.team2.updateTeamStats(scoreDiff, 0);
            }
            playedMatches.add(match);
        }
        // TODO: måste gå att gör i varje loop istället, detta är inte skalbart
        ongoingMatches.remove(0);
        if (!ongoingMatches.isEmpty()) {
            ongoingMatches.remove(0);
        }
    }

    public List<Team> getStandings() {
        Collections.sort(teams);
        return teams;
    }

    public void printStanding() {
        // Sort the teams in the correct standings
        Collections.sort(teams);
        int place = 1;
        for (Team team : teams) {
            System.out.println(place + ". " + team + "\nMatches played: " + team.playedMatches + "\nWON: "
                    + team.matchesWon + "\nScore: " + team.scoreDiff);
            System.out.println("");
            place++;
        }
    }
}

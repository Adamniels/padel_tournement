import lib.Utils;
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

    private static void no_menu_eventLoop() {
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

    private static void printStartMenu() {
        System.out.println(
                "\n==== Start MENU ====\n" +
                        "(S)tart new Tournement\n" +
                        "(R)esume Tournement");

    }

    private static void printMainMenu() {
        System.out.println(
                "\n==== Tournement Started Menu ====\n" +
                        "(S)tart new round/show ongoing matches\n" +
                        "(P)rint stats\n" +
                        "(U)pdate ongoing matches\n" +
                        "(Q)uit");
    }

    private static void printPlayoffMenu() {
        System.out.println("\n==== Playoff Menu ====\n" +
                "(S)how result Preliminary rounds\n");
    }

    private static void menu_eventloop() {
        boolean ongoingGames = false;
        boolean tournementStarted = false;
        // boolean playoffs = false;
        int teams = 0;
        int courts = 0;
        int totalRounds = 0;
        Tournement tournement = null;

        printStartMenu();
        char ans = Utils.getAnswer("SR"); // TODO: ska lägga i ett library senare
        switch (ans) {
            case 'S':
                // TODO: start a new Tournement
                tournementStarted = true;
                tournement = new Tournement();

                // Here you can set the parameters for a new tournement
                tournement.addTeam("team1", "a", "b");
                tournement.addTeam("team2", "c", "d");
                tournement.addTeam("team3", "e", "f");
                tournement.addTeam("team4", "g", "h");
                // tournement.addTeam("team5", "i", "j");
                // tournement.addTeam("team6", "k", "l");
                // tournement.addTeam("team7", "m", "n");
                // tournement.addTeam("team8", "o", "p");
                teams = 4;
                courts = 2;
                totalRounds = countRounds(teams, courts);

                break;

            case 'R':
                // TODO: resume existing one(how can i do this?)

                // tournement.restoreFromFile(file); något sånt kanske?
                tournementStarted = true;
                break;

            default:
                break;
        }

        tournement.startTournement();
        int playedRounds = 0;
        while (tournementStarted && playedRounds < totalRounds) {
            printMainMenu();
            ans = Utils.getAnswer("SPUQ");

            switch (ans) {
                case 'S':
                    if (ongoingGames) {
                        System.out.println("There is already a round being played, update it before starting a new\n");
                    } else {
                        tournement.playNewMatches();
                        ongoingGames = true;
                    }
                    break;

                case 'P':
                    tournement.printStanding();
                    break;

                case 'U':
                    if (ongoingGames) {
                        tournement.updateOngoingMatches();
                        ongoingGames = false;
                    } else {
                        System.out.println("No ongoing matches, play a new round\n");
                    }
                    playedRounds++;
                    break;

                case 'Q':
                    tournementStarted = false;
                    break;

                default:
                    break;
            }
        }

        // TODO: new menu for playoffs, maybe i want to do different things

        // starta slutspel
        List<Team> standings = tournement.getStandings();
        Playoffs playoffs = new Playoffs(standings);
        int playoffRounds = countPlayoffsRounds(teams);
        int playoffsRoundsPlayed = 0;

        while (playoffsRoundsPlayed < playoffRounds) {
            printPlayoffMenu();
            // TODO: switch sats
            // - show result Preliminary round
            // - play next playoff round
            // - update rounds
            break;
        }
    }

    public static void main(String[] args) {
        System.out.println("PADEL TOURNEMENT\n");
        // no_menu_eventLoop();
        menu_eventloop();
    }
}

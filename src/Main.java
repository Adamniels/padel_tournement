import lib.Utils;
import padel.Tournement;

public class Main {

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
        "(S)tart next playoff round\n" +
        "(P)rint stats preliminary rounds\n" +
        "(U)pdate playoff matches\n" +
        "(Q)uit");
  }

  private static void menu_eventloop() {
    Tournement tournement = null;

    printStartMenu();
    char ans = Utils.getAnswer("SR");
    switch (ans) {
      case 'S':
        // start a new Tournement
        tournement = new Tournement("tournementName");

        // Here you can set the parameters for a new tournement
        tournement.addTeam("team1", "a", "b");
        tournement.addTeam("team2", "c", "d");
        tournement.addTeam("team3", "e", "f");
        tournement.addTeam("team4", "g", "h");
        // tournement.addTeam("team5", "i", "j");
        // tournement.addTeam("team6", "k", "l");
        // tournement.addTeam("team7", "m", "n");
        // tournement.addTeam("team8", "o", "p");

        tournement.start();
        break;

      case 'R':
        // TODO: resume existing one(how can i do this?)

        // tournement.restoreFromFile(file); något sånt kanske?
        break;

      default:
        break;
    }

    boolean ongoingGames = false;
    boolean tournementStarted = true;
    boolean finalMatch = false;
    while (tournementStarted) {
      if (tournement.isPlayoffStage()) {
        printPlayoffMenu();
      } else {
        printMainMenu();
      }
      ans = Utils.getAnswer("SPUQ");

      switch (ans) {
        case 'S':
          if (ongoingGames) {
            System.out.println("There is already a round being played, update it before starting a new\n");
          } else {
            tournement.playNextRound();
            ongoingGames = true;
          }
          break;

        case 'P':
          tournement.printStanding();
          break;

        case 'U':
          if (ongoingGames) {
            finalMatch = tournement.updateCurrentRound();
            ongoingGames = false;
            if (finalMatch) {
              // last game
              System.out.println("TOURNEMENT IS OVER");
              tournementStarted = false;
            }
          } else {
            System.out.println("No ongoing matches, play a new round\n");
          }
          break;

        case 'Q':
          tournementStarted = false;
          tournement.saveTournement();
          break;

        default:
          break;
      }
    }
  }

  public static void main(String[] args) {
    System.out.println("PADEL TOURNEMENT\n");
    menu_eventloop();
  }
}

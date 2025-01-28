
import padel.Tournement;

public class Main {
  private static void eventLoop(){
    // create a tournement
    Tournement tournement = new Tournement();
    tournement.addTeam("team1", "a", "b");
    tournement.addTeam("team2", "c", "d");
    tournement.addTeam("team3", "e", "f");
    tournement.addTeam("team4", "g", "h");

    int teams = 4;
    int matches = (teams*(teams-1))/2;
    System.out.println("matches: " + matches);

    tournement.startTournement();
    for(int i = 0; i < matches/2; i++){
      tournement.playNewMatches();
      tournement.updateOngoingMatches();
      tournement.printTeams();
    }
    System.out.println("RESULT PRELIMINARY ROUND:");
    tournement.printTeams();

    // starta slutspelet
    
  }

  public static void main(String[] args) {
    System.out.println("PADEL TOURNEMENT");
    eventLoop();
  }
}

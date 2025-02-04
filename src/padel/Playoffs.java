package padel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Playoffs implements TournementStage {
  private boolean finalMatch;
  private boolean bronzeMatch;
  private boolean semifinal;
  private boolean querterfinal;
  private List<Match> matches;

  public Playoffs() {
    this.matches = new ArrayList<>();
    this.finalMatch = false;
    this.bronzeMatch = false;
    this.semifinal = false;
    this.querterfinal = false;

  }

  public void start(List<Team> teams) {
    if (teams.size() < 4) {
      Match finalmatch = new Match(teams.get(0), teams.get(1));
      matches.add(finalmatch);
      this.finalMatch = true;
    } else if (teams.size() < 8) {
      Match semi1 = new Match(teams.get(0), teams.get(3));
      Match semi2 = new Match(teams.get(1), teams.get(2));
      this.matches.add(semi1);
      this.matches.add(semi2);
      this.semifinal = true;
    } else {
      Match querter1 = new Match(teams.get(0), teams.get(7));
      Match querter2 = new Match(teams.get(1), teams.get(6));
      Match querter3 = new Match(teams.get(2), teams.get(5));
      Match querter4 = new Match(teams.get(3), teams.get(4));
      this.matches.add(querter1);
      this.matches.add(querter2);
      this.matches.add(querter3);
      this.matches.add(querter4);
      this.querterfinal = true;
    }
  }

  public boolean isPlayoff() {
    return true;
  }

  /**
   * Get the matches based on which round we are on
   */
  public void playMatches(int courts) {
    // TODO: courts unused at the time, just print all the matches, maybe dont even
    // use?
    if (finalMatch) {
      // in finals we might or might not hava a bronze match
      System.out.println("FINAL MATCH");
      System.out.println(matches.getFirst());
      System.out.println("");
      if (bronzeMatch) {
        // we might or might not have a bronzematch
        System.out.println("BRONZE MATCH");
        System.out.println(matches.getLast());
        System.out.println("");
      }
    } else {
      if (querterfinal) {
        // there is matches inside querterfinal so play them
        System.out.println("QUARTER FINALS");
      } else if (semifinal) {
        // we are on the semifinal
        System.out.println("SEMIFINALS");
      }
      // print the matches in the match array
      for (Match match : matches) {
        System.out.println(match);
      }
      System.out.println("");
    }
  }

  public void updateRound() {
    // gå igenom och upatera alla först
    updateMatches();
    // sedan kan vi gå igenom matcherna och ta ut vinnarna
    List<Team> winners = new ArrayList<>();
    List<Team> losers = new ArrayList<>();

    for (Match match : matches) {
      String winner = match.getWinner();
      // System.out.println("Winner in match " + match + " is: " + winner);
      if (match.team1.teamName.equals(winner)) {
        winners.add(match.team1);
        losers.add(match.team2);
      } else {
        winners.add(match.team2);
        losers.add(match.team1);
      }
    }

    if (finalMatch) {
      // det var final rundan och ska alltså bara printa resultatet
      System.out.println("WINNER OF TOURNEMENT " + winners.getFirst().teamName);
      if (bronzeMatch) {
        System.out.println("WINNER OF BRONZE MATCH " + losers.getFirst().teamName);
      }
    } else {
      // skapa ny matcher och lägga dem ett steg upp
      List<Match> winnerMatches = new ArrayList<>();
      List<Match> loserMatches = new ArrayList<>();
      for (int i = 0; i < winners.size(); i = i + 2) {
        Match newMatch = new Match(winners.get(i), winners.get(i + 1));
        winnerMatches.add(newMatch);
      }
      ;
      for (int j = 0; j < losers.size(); j = j + 2) {
        Match newMatch = new Match(losers.get(j), losers.get(j + 1));
        loserMatches.add(newMatch);
      }

      if (querterfinal) {
        // det var quarter finals
        this.matches.clear();
        this.matches = winnerMatches;
        this.querterfinal = false;
        this.semifinal = true;

      } else if (semifinal) {
        // det var semifinal
        this.matches.clear();
        // add the final
        this.matches.add(winnerMatches.getFirst());
        // add the bronze match
        this.matches.add(loserMatches.getFirst());

        this.semifinal = false;
        this.finalMatch = true;
        this.bronzeMatch = true;
      }
    }
  }

  private void updateMatches() {
    Scanner scanner = new Scanner(System.in);

    for (int i = 0; i < matches.size(); i++) {
      Match match = matches.get(i);
      System.out.println("Score " + match);
      System.out.print("team " + match.team1 + ": ");
      int team1score = scanner.nextInt();

      System.out.print("team " + match.team2 + ": ");
      int team2score = scanner.nextInt();
      System.out.println("");

      match.updateMatch(team1score, team2score);
    }
  }
}

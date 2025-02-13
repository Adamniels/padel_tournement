package padel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Preliminary implements TournementStage {
  List<Match> playedMatches;
  List<Match> ongoingMatches;
  List<Match> nextMatches;

  public Preliminary() {
    this.playedMatches = new ArrayList<>();
    this.ongoingMatches = new ArrayList<>();
    this.nextMatches = new ArrayList<>();
  }

  public void start(List<Team> teams) {
    // creates matches based on all the teams, every team meet each other one time
    for (int i = 0; i < teams.size(); i++) {
      for (int j = i + 1; j < teams.size(); j++) {
        Match match = new Match(teams.get(i), teams.get(j));
        nextMatches.add(match);
      }
    }
    Collections.shuffle(nextMatches);
  }

  public boolean isPlayoff() {
    return false;
  }

  // BUG: om det är ojämnt med lag tror jag att det kan bli fel så att
  // så att samma lag kan behöva köra i båda dem två sista matcherna.
  // så ska det inte vara för då blir det en plan ledig
  // kanske får skriva en egen sorting, som sortera så att det inte kan hända
  public void playMatches(int courts) {
    // TODO: courts unused now, set to 2
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

  public void updateRound() {
    Scanner scanner = new Scanner(System.in);

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

  public void saveToFile(String filename) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Skapa en Gson-instans med snygg formatering
    File file = new File(filename);

    try {
      // Skapa filen om den inte finns
      if (!file.exists()) {
        file.createNewFile();
      }

      // Skriv JSON till filen
      try (FileWriter writer = new FileWriter(file)) {
        gson.toJson(this, writer);
        System.out.println("Turneringen sparad till " + filename);
      }

    } catch (IOException e) {
      System.err.println("Fel vid sparning av JSON: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

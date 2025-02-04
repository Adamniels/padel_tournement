package padel;

import java.util.*;

public class Tournement {
  List<Team> teams;
  int courts;
  int roundsPlayed;
  Preliminary preliminary;
  int preliminaryRoundsTotal;
  Playoffs playoffs;
  int playoffRoundstotal;
  TournementStage tournementStage;

  public Tournement() {
    teams = new ArrayList<>();
    playoffs = new Playoffs();
    preliminary = new Preliminary();
    // NOTE: for now always start with preliminary
    tournementStage = preliminary;
    courts = 2;

    roundsPlayed = 0;
    preliminaryRoundsTotal = 0;
    playoffRoundstotal = 0;
  }

  public void addTeam(String teamName, String mem1, String mem2) {
    Team team = new Team(teamName, mem1, mem2);
    teams.add(team);
  }

  public void start() {
    preliminaryRoundsTotal = countPrelRounds(teams.size(), courts);
    playoffRoundstotal = countPlayoffsRounds(teams.size());
  }

  public void playNextRound() {
    if (roundsPlayed == 0) {
      tournementStage.start(teams);
    }

    tournementStage.playMatches(courts);
  }

  public boolean updateCurrentRound() {
    tournementStage.updateRound();

    if (roundsPlayed == preliminaryRoundsTotal - 1) {
      // change to playoff and
      System.out.println("PRELIMINARY IS OVER, TIME FOR PLAYOFFS");
      tournementStage = playoffs;
      playoffs.start(teams);
    } else if (roundsPlayed == playoffRoundstotal + preliminaryRoundsTotal - 1) {
      return true;
    }
    roundsPlayed++;
    return false;
  }

  public boolean isPlayoffStage() {
    return tournementStage.isPlayoff();
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

  private static int countPrelRounds(int teams, int courts) {
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

}

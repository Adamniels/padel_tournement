package padel;

import java.util.*;

public interface TournementStage {
  void start(List<Team> teams);

  void playMatches(int courts);

  void updateRound();

  boolean isPlayoff();
}

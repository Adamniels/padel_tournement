package padel;


public class Team implements Comparable<Team>{
    String teamName;
    String member1;
    String member2;
    int playedMatches;
    int scoreDiff;
    int matchesWon;

    public Team(String teamName, String mem1, String mem2){
        this.teamName = teamName;
        this.member1 = mem1;
        this.member2 = mem2;
        this.playedMatches = 0;
        this.scoreDiff = 0;
        this.matchesWon = 0; 
    }

    public void updateTeamStats(int scoreDiff, int won){
        this.matchesWon += won;
        this.scoreDiff += scoreDiff;
        this.playedMatches++;
    }

    @Override
    public String toString(){
        return teamName;
    }

    @Override
    public int compareTo(Team o) {
        // Första kriteriet: Antal vinster (fallande ordning)
        if (this.matchesWon != o.matchesWon) {
            return Integer.compare(o.matchesWon, this.matchesWon);
        }
        // Andra kriteriet: Poängdifferens (fallande ordning)
        return Integer.compare(o.scoreDiff, this.scoreDiff);
    }


}

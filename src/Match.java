package src;

public class Match {
    Team team1;
    Team team2;
    int team1pts;
    int team2pts;

    public Match(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
        team1pts = 0;
        team2pts = 0;
    }

    public void updateMatch(int team1pts, int team2pts){
        this.team1pts = team1pts;
        this.team2pts = team2pts;
    }

    public String getWinner(){
        int res = team1pts - team2pts;
        if(res < 0){
            // team 2 won
            return team2.teamName;
        }else if(res > 0){
            // team 1 won
            return team1.teamName;
        }else{
            return "DRAW";
        }
    }

    public int getScore(){
        return Math.abs(team1pts-team2pts);
    }

    public Team getTeam1(){
        return team1;
    }
    public Team getTeam2(){
        return team2;
    }

    @Override
    public String toString(){
        return team1 + " vs " + team2;
    }

}


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Report implements Serializable {
    Map<String,Double> teamAvgPoints =new HashMap<>();

    public Map<String, Player> getTeamTopScorer() {
        return teamTopScorer;
    }

    public void setTeamTopScorer(Map<String, Player> teamTopScorer) {
        this.teamTopScorer = teamTopScorer;
    }

    public Map<String, Double> getTeamAvgPoints() {
        return teamAvgPoints;
    }

    public void setTeamAvgPoints(Map<String, Double> teamAvgPoints) {
        this.teamAvgPoints = teamAvgPoints;
    }

    Map<String, Player> teamTopScorer = new HashMap<>();

    public Report(Map<String, Player> teamTopScorer, Map<String, Double> teamAvgPoints) {
        this.teamTopScorer = teamTopScorer;
        this.teamAvgPoints = teamAvgPoints;
    }
    public synchronized void addAverage(String team,Double average)
    {
        teamAvgPoints.put(team,average);
        System.out.println(Thread.currentThread().getName()+"Added average!");
    }
    public synchronized void addTopScorer(String team,Player player)
    {
        teamTopScorer.put(team,player);
        System.out.println(Thread.currentThread().getName()+"Added top scorer!");
    }
    public Report(){}
}
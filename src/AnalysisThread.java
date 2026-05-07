import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalysisThread implements Runnable{
    private Report report;
    private List<Player> playerList;

    public AnalysisThread(Report report, List<Player> playerList) {
        this.report = report;
        this.playerList = playerList;
    }

    @Override
    public void run(){
        Map<String,Double> avgMap = playerList.stream().collect(Collectors.groupingBy
                (Player::getTeamName,Collectors.averagingDouble(Player::getPoints)));
        avgMap.forEach((team,avg) -> {
            report.addAverage(team,avg);
        });
    }

}

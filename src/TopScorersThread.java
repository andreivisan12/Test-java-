import java.util.*;
import java.util.stream.Collectors;

public class TopScorersThread implements Runnable{
    private Report report;
    private List<Player> playerList;

    public TopScorersThread(Report report, List<Player> playerList) {
        this.report = report;
        this.playerList = playerList;
    }

    @Override
    public void run()
    {
        Map<String,Optional<Player>> topScorers = playerList.stream().collect(Collectors.groupingBy(Player::getTeamName
                ,Collectors.maxBy(Comparator.comparingInt(Player::getPoints))));

        topScorers.forEach((team,optionalPlayer)->{
            if(optionalPlayer.isPresent()){
                report.addTopScorer(team,optionalPlayer.get());
            }
        });
    }
}

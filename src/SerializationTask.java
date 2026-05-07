import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;

public class SerializationTask implements Runnable{
    private Report report;
    private CompleteCallback callback;

    public SerializationTask(Report report, CompleteCallback callback) {
        this.report = report;
        this.callback = callback;
    }

    @Override
    public void run() {
        try(BufferedWriter out = new BufferedWriter(new FileWriter("report.txt")))
        {
            Map<String,Double> avgMap = report.getTeamAvgPoints();
            Map<String,Player> topScorersMap = report.getTeamTopScorer();

            for (String team:avgMap.keySet())
            {
                Double avg = avgMap.get(team);
                Player topScorer = topScorersMap.get(team);
                String scorerName =(topScorer!=null) ? topScorer.getPlayerName() : "Unknown";

                String line = String.format("Team: %s | Average points: %f |Top Scorer: %s",team,avg,scorerName);
                out.write(line);
                out.newLine();
            }
            if(callback!=null)
                callback.onReportGenerated("report.txt");
        }catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}

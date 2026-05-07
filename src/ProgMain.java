import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ProgMain {
    public static void main()
    {
        List<Player> players = new ArrayList<>();
        Map<String,Player> playerHashMap = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader("players.txt"))){

            String line;
            boolean isHeader = true;

            while((line = br.readLine()) != null)
            {
                if(isHeader == true)
                {
                    isHeader = false;
                    continue;
                }
                String[] data = line.split(",");
                String name = data[0].trim();
                String teamName = data[1].trim();
                int points = Integer.parseInt(data[2].trim());
                int reb = Integer.parseInt(data[3].trim());
                int ass = Integer.parseInt(data[4].trim());

                Player p = new Player(name, teamName,points,reb,ass);
                players.add(p);
                playerHashMap.put(p.getPlayerName(),p);
            }

        }catch (IOException fnf)
        {
            fnf.printStackTrace();
        }
        for (Player p: players)
            System.out.println(p.toString());
        PlayerCalculations<Double> averageLambda = (List<Player> l) ->{
            double avg = 0.0;
            for(Player p:l)
                avg+=p.getPoints();
            return avg/l.size();
        };
        Player p = new Player();
        System.out.println("Average score for players: "+p.getAverage(players,averageLambda));
        SortPlayers sortLambda =(List<Player> l) ->{
            l.sort(null);
            return l;
        };
       players = p.sortByPoints(players,sortLambda);
        for (Player o: players)
           System.out.println(o.toString());

        Report report = new Report();
        Runnable avgTask = new AnalysisThread(report,players);
        Runnable topScorersTask = new TopScorersThread(report,players);

        Thread t1 = new Thread(avgTask,"Thread-Averages");
        Thread t2 = new Thread(topScorersTask,"Thread-TopScorers");

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
            System.out.println("Successfully joined: "+t1.getName()+" and "+t2.getName());

        }catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        CompleteCallback callback = (filePath)-> {
            System.out.println("Report generated!");
        };
        Thread t3 = new Thread(new SerializationTask(report,callback),"Thread 3-Serialization");
        t3.start();
    }
}

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@FunctionalInterface
interface PlayerCalculations<T>{
     T calculation(List<Player> l);
}
@FunctionalInterface
interface SortPlayers{
    List<Player> sortPlayers(List<Player> p);
}

public class Player implements Serializable, Comparable<Player> {
    private String playerName;
    private String teamName;
    private int points;
    private int rebounds;
    private int assists;

    public Player(String playerName, String teamName, int points, int rebounds, int assists) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }
    public  Player(){}

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", points=" + points +
                ", rebounds=" + rebounds +
                ", assists=" + assists +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return points == player.points && rebounds == player.rebounds && assists == player.assists && Objects.equals(playerName, player.playerName) && Objects.equals(teamName, player.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, teamName, points, rebounds, assists);
    }
    public double getAverage(List<Player> l,PlayerCalculations<Double> playerCalculations)
    {
        return playerCalculations.calculation(l);
    }

    @Override
    public int compareTo(Player o) {
        if(this.points<o.points)
            return 1;
        else if(this.points>o.points)
            return -1;
        else
            return 0;
    }
    public List<Player> sortByPoints(List<Player> l,SortPlayers sortPlayers)
    {
        return sortPlayers.sortPlayers(l);
    }
}

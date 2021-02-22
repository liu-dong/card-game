import java.util.List;

/**
 * @author LD
 */
public class Person {

    private List<Poker> pokerList;
    private Integer score;

    public List<Poker> getPokerList() {
        return pokerList;
    }

    public void setPokerList(List<Poker> pokerList) {
        this.pokerList = pokerList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

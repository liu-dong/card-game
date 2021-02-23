import java.util.List;

/**
 * @author LD
 */
public class Person {

    private String personName;
    private List<Poker> pokerList;
    private Integer score;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

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

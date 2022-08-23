import java.util.ArrayList;
import java.util.List;

/**
 * @author LD
 */
public class Person {

    /**
     * 持牌人
     */
    private String personName;
    /**
     * 持有牌牌
     */
    private List<Poker> pokerList;
    /**
     * 积分
     */
    private Integer score;

    public Person(String personName) {
        this.personName = personName;
        this.pokerList = new ArrayList<>();
        this.score = 0;
    }

    /**
     * 摸牌
     * @return
     */
    public void getPoker(Poker poker){
        this.pokerList.add(poker);
    }

    /**
     * 出牌
     *
     * @return
     */
    public void removePoker(List<Poker> pokers){
        this.pokerList.removeAll(pokers);
    }

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

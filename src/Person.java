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

    /**
     * 摸牌
     * @return
     */
    public Poker getPoker(){
        return null;
    }

    /**
     * 出牌
     *
     * @return
     */
    public Poker removePoker(){
        return null;
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

import java.util.*;
import java.util.stream.Collectors;

/**
 * 双剑游戏
 *
 * @author liudong 2022/8/23
 */
public class SJGame {

    private Person onePerson;
    private Person twoPerson;
    private Person threePerson;
    private NewPokers newPokers;

    public SJGame(NewPokers pokers) {
        this.onePerson = new Person("甲");
        this.twoPerson = new Person("乙");
        this.threePerson = new Person("丙");
        this.newPokers = pokers;
    }

    public void startGaem(){
        //洗牌
        shuffle(newPokers.getPokers());

        //发牌
        Poker one = toDeal(newPokers);
        onePerson.getPoker(one);
        //发牌
        Poker two = toDeal(newPokers);
        twoPerson.getPoker(two);
        //发牌
        Poker three = toDeal(newPokers);
        twoPerson.getPoker(three);


    }

    /**
     * 洗牌
     *
     * @return
     */
    private void shuffle(List<Poker> pokers) {
        Collections.shuffle(pokers);
    }

    /**
     * 发牌
     * @param newPokers
     * @return
     */
    private Poker toDeal(NewPokers newPokers) {
        Random random = new Random();
        int index = random.nextInt(newPokers.getPokers().size());
        Poker poker = newPokers.getPokers().get(index);
        newPokers.getPokers().remove(poker);
        newPokers.surplusNum--;
        return poker;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<Poker> insertSort(List<Poker> pokers) {
        return pokers.stream().sorted(Comparator.comparing(Poker::getSort)).collect(Collectors.toList());
    }

    public Person getOnePerson() {
        return onePerson;
    }

    public void setOnePerson(Person onePerson) {
        this.onePerson = onePerson;
    }

    public Person getTwoPerson() {
        return twoPerson;
    }

    public void setTwoPerson(Person twoPerson) {
        this.twoPerson = twoPerson;
    }

    public Person getThreePerson() {
        return threePerson;
    }

    public void setThreePerson(Person threePerson) {
        this.threePerson = threePerson;
    }

    public NewPokers getNewPokers() {
        return newPokers;
    }

    public void setNewPokers(NewPokers newPokers) {
        this.newPokers = newPokers;
    }
}

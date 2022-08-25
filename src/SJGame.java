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
    private String winner;
    public static boolean gameOver;//游戏结束
    private int roundCount = 1;
    private int pass = 0;

    public SJGame() {
        this.onePerson = new Person("甲");
        this.twoPerson = new Person("乙");
        this.threePerson = new Person("丙");
        this.newPokers = new NewPokers();
        gameOver = false;
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        System.out.println("====================洗牌====================");
        //洗牌
        shuffle(newPokers.getPokers());
        System.out.println(newPokers.getPokers());
        System.out.println("====================发牌====================");
        //发牌
        toDealAll(newPokers, Arrays.asList(onePerson, twoPerson, threePerson));
    }

    /**
     * 选地主
     *
     * @return
     */
    public String selectLandlord() {
        boolean one = onePerson.askLoot();
        boolean two = twoPerson.askLoot();
        boolean three = threePerson.askLoot();
        if (three) {
            threePerson.setPersonType(CardGameConstants.LANDLORD);
            twoPerson.setPersonType(CardGameConstants.PEASANT);
            onePerson.setPersonType(CardGameConstants.PEASANT);
            return threePerson.getPersonName();
        } else if (two) {
            twoPerson.setPersonType(CardGameConstants.LANDLORD);
            threePerson.setPersonType(CardGameConstants.PEASANT);
            onePerson.setPersonType(CardGameConstants.PEASANT);
            return twoPerson.getPersonName();
        } else {
            onePerson.setPersonType(CardGameConstants.LANDLORD);
            threePerson.setPersonType(CardGameConstants.PEASANT);
            twoPerson.setPersonType(CardGameConstants.PEASANT);
            return onePerson.getPersonName();
        }
    }

    /**
     * 回合
     *
     * @param player
     */
    public void enterRound(String player) {
        if (gameOver) {
            System.out.println("游戏结束");
            return;
        }
        if (onePerson.getPersonName().equals(player)) {
            round(onePerson);
            enterRound(twoPerson.getPersonName());
        }
        if (twoPerson.getPersonName().equals(player)) {
            round(twoPerson);
            enterRound(threePerson.getPersonName());
        }
        if (threePerson.getPersonName().equals(player)) {
            round(threePerson);
            enterRound(onePerson.getPersonName());
        }
    }

    /**
     * 回合
     */
    public void round(Person person) {
        if (roundCount != 1 && pass != 2) {
            boolean play = person.askPlay();
            if (!play){
                pass++;
                return;
            }
        }
        person.removePoker();
        hasGameOver(person);
        roundCount++;
        pass = 0;
    }


    /**
     * 判定游戏是否结束
     *
     * @param person
     */
    public void hasGameOver(Person person) {
        if (!person.hasPoker()) {
            gameOver = true;
            endGame(person);
        }
    }

    /**
     * 结束游戏判定胜者
     *
     * @param person
     */
    public void endGame(Person person) {
        this.winner = person.getPersonName();
        System.out.println("游戏结束！胜者：" + this.winner);
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
     *
     * @param newPokers
     * @return
     */
    private void toDeal(NewPokers newPokers, Person person) {
        Random random = new Random();
        int index = random.nextInt(newPokers.getPokers().size());
        Poker poker = newPokers.getPokers().get(index);
        newPokers.getPokers().remove(poker);
        newPokers.surplusNum--;
        person.getPoker(poker);
    }

    /**
     * 发完全部的牌
     *
     * @param newPokers
     * @param personList
     */
    private void toDealAll(NewPokers newPokers, List<Person> personList) {
        while (newPokers.surplusNum > 0) {
            for (Person person : personList) {
                toDeal(newPokers, person);
            }
        }
        for (Person person : personList) {
            person.sortPoker();
        }
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

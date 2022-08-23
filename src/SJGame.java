import java.util.*;
import java.util.stream.Collectors;

/**
 * 双剑游戏
 *
 * @author liudong 2022/8/23
 */
public class SJGame {

    private List<Person> personList;
    private NewPokers newPokers;

    public SJGame(List<Person> personList, NewPokers pokers) {
        this.personList = personList;
        this.newPokers = pokers;
    }

    public void startGaem(){
        //洗牌
        shuffle(newPokers.getPokers());
        //发牌

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
     * @param pokerList
     * @return
     */
    private Map<String, Object> toDeal(List<Poker> pokerList) {
        Map<String, Object> result = new HashMap<>();
        Person person1 = new Person();
        //排序
        person1.setPokerList(insertSort(pokerList.subList(0, 17)));
        System.out.println("甲的牌" + person1.getPokerList().size() + "张：" + person1.getPokerList().toString());
        result.put("甲", person1);
        Person person2 = new Person();
        person2.setPokerList(insertSort(pokerList.subList(17, 34)));
        System.out.println("乙的牌" + person2.getPokerList().size() + "张：" + person2.getPokerList().toString());
        result.put("乙", person2);
        Person person3 = new Person();
        person3.setPokerList(insertSort(pokerList.subList(34, 51)));
        System.out.println("丙的牌" + person3.getPokerList().size() + "张：" + person3.getPokerList().toString());
        result.put("丙", person3);
        List<Poker> finalCard = insertSort(pokerList.subList(51, 54));
        System.out.println("底牌" + finalCard.size() + "张：" + finalCard);
        result.put("底牌", finalCard);
        return result;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<Poker> insertSort(List<Poker> pokers) {
        return pokers.stream().sorted(Comparator.comparing(Poker::getSort)).collect(Collectors.toList());
    }
}

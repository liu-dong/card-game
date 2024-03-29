import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 一副牌
 *
 * @author liudong 2022/8/23
 */
public class NewPokers {

    /**
     * 一副牌 54张
     */
    private List<Poker> pokers;
    /**
     * 剩余数量
     */
    private int surplusNum = 54;

    public NewPokers() {
        this.pokers = getPokerList();
        this.pokers = insertSort();//排序
        System.out.println("一副新牌共" + this.pokers.size() + "张:" + this.pokers);
    }

    /**
     * 生成一副牌
     *
     * @return
     */
    private List<Poker> getPokerList() {
        List<Poker> pokerList = new ArrayList<>(54);
        for (int i = 0; i < 4; i++) {
            int sort = i + 1;
            System.out.println("生成第一种花色：" + CardGameConstants.FLOWER_COLOR[i]);
            for (String number : CardGameConstants.NUMBER) {
                Poker poker = new Poker(sort, number, CardGameConstants.FLOWER_COLOR[i]);
                pokerList.add(poker);
                sort += 4;
            }
        }
        //小王
        Poker littleJoker = new Poker(53, "王", CardGameConstants.FLOWER_COLOR[4]);
        //大王
        Poker bigJoker = new Poker(54, "王", CardGameConstants.FLOWER_COLOR[5]);
        pokerList.add(littleJoker);
        pokerList.add(bigJoker);
        System.out.println("数量：" + pokerList.size());
        return pokerList;
    }

    /**
     * 排序
     *
     * @return
     */
    public List<Poker> insertSort() {
        return this.pokers.stream().sorted(Comparator.comparing(Poker::getSort)).collect(Collectors.toList());
    }

    public List<Poker> getPokers() {
        return pokers;
    }

    public void setPokers(List<Poker> pokers) {
        this.pokers = pokers;
    }

    public int getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }
}

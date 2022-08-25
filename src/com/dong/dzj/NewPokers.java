package com.dong.dzj;

import com.dong.common.GameConstants;

import java.util.ArrayList;
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
    public int surplusNum = 54;

    public NewPokers() {
        this.pokers = getPokerList();
        this.pokers = insertSort();//排序
        System.out.println("====================一副新牌共" + this.pokers.size() + "张====================");
        System.out.println(this.pokers);
        System.out.println("====================一副新牌共" + this.pokers.size() + "张====================");
    }

    /**
     * 生成一副牌
     *
     * @return
     */
    private List<Poker> getPokerList() {
        System.out.println("====================生成一副牌====================");
        List<Poker> pokerList = new ArrayList<>(54);
        for (int i = 0; i < 4; i++) {
            int sort = i + 1;
            System.out.println("生成第" + sort + "种花色：" + GameConstants.FLOWER_COLOR[i]);
            for (String number : GameConstants.NUMBER) {
                Poker poker = new Poker(sort, number, GameConstants.FLOWER_COLOR[i]);
                System.out.print("====================生成一张牌：" + poker);
                pokerList.add(poker);
                sort += 4;
            }
        }
        //小王
        Poker littleJoker = new Poker(53, "&", GameConstants.FLOWER_COLOR[4]);
        System.out.println("生成第" + 5 + "种花色：" + GameConstants.FLOWER_COLOR[4]);
        System.out.print("====================生成一张牌：" + littleJoker);
        //大王
        Poker bigJoker = new Poker(54, "&", GameConstants.FLOWER_COLOR[5]);
        System.out.println("生成第" + 6 + "种花色：" + GameConstants.FLOWER_COLOR[5]);
        System.out.print("====================生成一张牌：" + bigJoker);
        pokerList.add(littleJoker);
        pokerList.add(bigJoker);
        System.out.println("====================生成一副牌——" + "数量：" + pokerList.size() + "张====================");
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
}

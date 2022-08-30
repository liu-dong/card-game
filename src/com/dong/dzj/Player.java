package com.dong.dzj;

import com.dong.common.GameConstants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LD
 */
public class Player {

    /**
     * 进入游戏顺序
     */
    public int number;
    /**
     * 持牌人类型(地主landlord、农民peasant)
     */
    public String personType;
    /**
     * 持牌人
     */
    public String personName;
    /**
     * 持有牌
     */
    public List<Poker> pokerList;
    /**
     * 积分
     */
    public Integer score;

    public Player(int number, String personName) {
        this.number = number;
        this.personName = personName;
        this.pokerList = new ArrayList<>();
        this.score = 0;
    }

    /**
     * 摸牌
     *
     * @return
     */
    public void getPoker(Poker poker) {
        pokerList.add(poker);
    }

    /**
     * 理牌
     */
    public void sortPoker() {
        pokerList = pokerList.stream().sorted(Comparator.comparing(Poker::getSort)).collect(Collectors.toList());
        System.out.println(this);
    }

    /**
     * 出牌
     *
     * @return
     */
    public void removePoker() {
        List<Poker> movePokers = getMovePoker();
        pokerList.removeAll(movePokers);
        System.out.println("====================" + this.personName + "出牌：\n" + movePokers);
    }

    /**
     * 抢地主
     */
    public void grabLandlord(){

    }

    /**
     * 跳过回合（过、不抢、不要）
     */
    public void skipRound(){

    }

    /**
     * 宣布胜利
     */
    public void declareVictory(){

    }

    /**
     * 判断是否还有牌
     *
     * @return
     */
    public boolean hasPoker() {
        return pokerList.size() > 0;
    }

    /**
     * 获取需要出的牌
     *
     * @param
     * @return
     */
    public List<Poker> getMovePoker() {
        Scanner input = new Scanner(System.in);
        System.out.println(this.personName + "请输入要出牌的序号，用逗号隔开");
        String s = input.nextLine();
        System.out.println(this.personName + "输入数字为：" + s);
        String[] split = s.split(",");
        List<String> list = Arrays.asList(split);
        return pokerList.stream().filter(poker -> list.contains(String.valueOf(poker.getSort()))).collect(Collectors.toList());
    }

    /**
     * 询问是否出牌
     *
     * @return
     */
    public boolean askPlay() {
        System.out.println(this.personName + "请选择：3：要，4：不要");
        return GameConstants.PRESS.equals(action());
    }

    /**
     * 询问是否抢地主
     *
     * @return
     */
    public Map<Integer,Boolean> askLoot() {
        System.out.println(this.personName + "请选择：1：抢地主，2：不抢");
        Map<Integer,Boolean> map = new HashMap<>();
        map.put(number,GameConstants.LOOT.equals(action()));
        return map;
    }

    /**
     * 动作
     *
     * @return 抢地主(loot)、不抢(notLoot)、要(press)、不要(pass)
     */
    public String action() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        if ("1".equals(s)) {
            System.out.println(this.personName + "：抢地主");
            return "loot";
        }
        if ("2".equals(s)) {
            System.out.println(this.personName + "：不抢");
            return "notLoot";
        }
        if ("3".equals(s)) {
            System.out.println(this.personName + "：要");
            return "press";
        }
        if ("4".equals(s)) {
            System.out.println(this.personName + "：不要");
            return "pass";
        }
        return "null";
    }

    @Override
    public String toString() {
        return "====================持牌人：" + this.personName + "\n====================持有的牌：\n" + pokerList;
    }
}

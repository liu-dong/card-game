package com.dong.dzj;

import com.dong.common.GameConstants;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author LD
 */
public class Person {

    /**
     * 持牌人类型(地主landlord、农民peasant)
     */
    private String personType;
    /**
     * 持牌人
     */
    private String personName;
    /**
     * 持有牌
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
     *
     * @return
     */
    public void getPoker(Poker poker) {
        pokerList.add(poker);
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
     * 理牌
     */
    public void sortPoker() {
        pokerList = pokerList.stream().sorted(Comparator.comparing(Poker::getSort)).collect(Collectors.toList());
        System.out.println(this);
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
    public boolean askLoot() {
        System.out.println(this.personName + "请选择：1：抢地主，2：不抢");
        return GameConstants.LOOT.equals(action());
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


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    @Override
    public String toString() {
        return "====================持牌人：" + this.personName + "\n====================持有的牌：\n" + pokerList;
    }
}

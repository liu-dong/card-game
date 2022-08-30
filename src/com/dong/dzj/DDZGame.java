package com.dong.dzj;

import com.dong.sj.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 双剑游戏
 *
 * @author liudong 2022/8/23
 */
public class DDZGame {

    /**
     * 玩家一
     */
    public Player onePlayer;

    /**
     * 玩家二
     */
    public Player twoPlayer;

    /**
     * 玩家三
     */
    public Player threePlayer;

    /**
     * 一副牌
     */
    public List<Poker> pokers;

    /**
     * 胜者
     */
    public String winner;


    /**
     * 准备游戏
     */
    public void prepareGame() {
        System.out.println("====================开始游戏====================");
        System.out.println("====================等待玩家加入====================");
    }

    /**
     * 开始游戏
     */
    public void startGame() {
        System.out.println("====================洗牌====================");
        //洗牌
        shuffle(pokers);
        System.out.println(pokers);
        System.out.println("====================发牌====================");
        //发牌
        toDealAll(pokers, Arrays.asList(onePlayer, twoPlayer, threePlayer));

    }

    /**
     * 抢地主回合
     */
    public void grabLandlordRound() {

    }

    /**
     * 出牌回合
     */
    public void playPokerRound() {

    }

    /**
     * 选地主
     *
     * @return
     *//*
    public String selectLandlord() {
        boolean one = onePerson.askLoot();
        boolean two = twoPerson.askLoot();
        boolean three = threePerson.askLoot();
        if (three) {
            threePerson.setPersonType(GameConstants.LANDLORD);
            twoPerson.setPersonType(GameConstants.PEASANT);
            onePerson.setPersonType(GameConstants.PEASANT);
            return threePerson.getPersonName();
        } else if (two) {
            twoPerson.setPersonType(GameConstants.LANDLORD);
            threePerson.setPersonType(GameConstants.PEASANT);
            onePerson.setPersonType(GameConstants.PEASANT);
            return twoPerson.getPersonName();
        } else {
            onePerson.setPersonType(GameConstants.LANDLORD);
            threePerson.setPersonType(GameConstants.PEASANT);
            twoPerson.setPersonType(GameConstants.PEASANT);
            return onePerson.getPersonName();
        }
    }

    *//**
     * 回合
     *
     * @param player
     *//*
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

    *//**
     * 回合
     *//*
    public void round(Person person) {
        if (roundCount != 1 && pass != 2) {
            boolean play = person.askPlay();
            if (!play) {
                pass++;
                return;
            }
        }
        person.removePoker();
        hasGameOver(person);
        roundCount++;
        pass = 0;
    }


    *//**
     * 判定游戏是否结束
     *
     * @param person
     *//*
    public void hasGameOver(Person person) {
        if (!person.hasPoker()) {
            gameOver = true;
            endGame(person);
        }
    }*/

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
     * @param player
     * @return
     */
    private void toDeal(List<Poker> pokers, Player player) {
        Random random = new Random();
        int index = random.nextInt(pokers.size());
        Poker poker = pokers.get(index);
        pokers.remove(poker);
        player.getPoker(poker);
    }

    /**
     * 发完全部的牌
     *
     * @param pokers
     * @param playerList
     */
    private void toDealAll(List<Poker> pokers, List<Player> playerList) {
        while (pokers.size() > 0) {
            for (Player player : playerList) {
                toDeal(pokers, player);
            }
        }
        for (Player player : playerList) {
            player.sortPoker();
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
}

package com.dong.dzj;

import com.dong.common.GameConstants;

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
     * 地主
     */
    public String landlord;

    /**
     * 胜者
     */
    public String winner;

    /**
     * 回合数
     */
    public int roundCount = 1;

    /**
     * 跳过回合数
     */
    public int skipRoundCount = 0;

    /**
     * 准备游戏
     */
    public void prepareGame() {
        System.out.println("====================等待玩家加入====================");
        this.onePlayer = new Player(1, joinGame());
        System.out.println("1号玩家已加入：" + onePlayer.personName);
        this.twoPlayer = new Player(2, joinGame());
        System.out.println("2号玩家已加入：" + twoPlayer.personName);
        this.threePlayer = new Player(3, joinGame());
        System.out.println("3号玩家已加入：" + threePlayer.personName);
        this.pokers = getPokerList();
        this.pokers = insertSort();//排序
        System.out.println("====================一副新牌共" + this.pokers.size() + "张====================");
        System.out.println(this.pokers);
        System.out.println("====================一副新牌共" + this.pokers.size() + "张====================");
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
        System.out.println("====================抢地主开始====================");
        //选地主
        landlord = grabLandlordRound();
        System.out.println("抢地主结束！地主：" + this.landlord);
        //出牌
        playPokerRound(landlord);
    }

    /**
     * 结束游戏判定胜者
     */
    public void endGame() {
        System.out.println("游戏结束！胜者：" + this.winner);
    }

    /**
     * 抢地主回合
     */
    public String grabLandlordRound() {
        System.out.println("====================抢地主开始====================");
        boolean oneCall = onePlayer.callLandlore();
        if (oneCall) {
            boolean twoLoot = twoPlayer.lootLandlord();
            boolean threeLoot = threePlayer.lootLandlord();
            boolean oneLoot = onePlayer.lootLandlord();
            if (twoLoot) {
                if (threeLoot) {
                    if (oneLoot) {
                        return onePlayer.personName;
                    } else {
                        return threePlayer.personName;
                    }
                } else {
                    if (oneLoot) {
                        return onePlayer.personName;
                    } else {
                        return twoPlayer.personName;
                    }
                }
            } else {
                if (threeLoot) {
                    if (oneLoot) {
                        return onePlayer.personName;
                    } else {
                        return threePlayer.personName;
                    }
                } else {
                    return onePlayer.personName;
                }
            }
        } else {
            boolean twoCall = twoPlayer.callLandlore();
            if (twoCall) {
                boolean threeLoot = threePlayer.lootLandlord();
                if (threeLoot) {
                    boolean twoLoot = twoPlayer.lootLandlord();
                    if (twoLoot) {
                        return twoPlayer.personName;
                    } else {
                        return threePlayer.personName;
                    }
                } else {
                    return twoPlayer.personName;
                }
            } else {
                boolean threeCall = threePlayer.callLandlore();
                if (threeCall) {
                    return threePlayer.personName;
                } else {
                    return grabLandlordRound();
                }
            }
        }
    }

    /**
     * 出牌回合
     *
     * @return
     */
    public void playPokerRound(String player) {
        if (winner != null) {
            return;
        }
        if (onePlayer.personName.equals(player)) {
            winner = round(onePlayer);
            playPokerRound(twoPlayer.personName);
        }
        if (twoPlayer.personName.equals(player)) {
            winner = round(twoPlayer);
            playPokerRound(threePlayer.personName);
        }
        if (threePlayer.personName.equals(player)) {
            winner = round(threePlayer);
            playPokerRound(onePlayer.personName);
        }
    }

    /**
     * 回合
     *
     * @return
     */
    public String round(Player player) {
        if (roundCount != 1 && skipRoundCount != 2) {
            boolean play = player.askPlay();
            if (!play) {
                skipRoundCount++;
                return null;
            }
        } else {
            System.out.println("地主开始出牌！");
        }
        player.removePoker();
        if (!player.hasPoker()) {
            return player.personName;
        }
        roundCount++;
        skipRoundCount = 0;
        return null;
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

    /**
     * 加入游戏
     *
     * @return
     */
    public String joinGame() {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入姓名");
        return input.nextLine();
    }
}

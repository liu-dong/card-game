package com.dong.sj;

/**
 * @author liudong 2022/8/24
 */
public class Game {

    public static void main(String[] args) {
        //开启一盘游戏
        SJGame game = new SJGame();
        System.out.println("====================游戏开始====================");
        game.startGame();
        System.out.println("====================抢地主开始====================");
        String landlord = game.selectLandlord();
        System.out.println("====================地主：" + landlord + "====================");
        System.out.println("====================游戏进入回合，" + landlord + "先出====================");
        game.enterRound(landlord);
    }
}

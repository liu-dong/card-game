package com.dong;

import com.dong.dzj.DDZGame;

/**
 * @author liudong 2022/8/24
 */
public class Game {

    public static void main(String[] args) {
        //开启一盘游戏
        DDZGame game = new DDZGame();
        System.out.println("====================游戏准备====================");
        game.prepareGame();
        System.out.println("====================游戏开始====================");
        game.startGame();
        System.out.println("====================游戏结束====================");
        game.endGame();
    }
}

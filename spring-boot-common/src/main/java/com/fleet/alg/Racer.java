package com.fleet.alg;

public class Racer implements Runnable {

    private String winner;

    @Override
    public void run() {
        for (int dis = 1; dis <= 100; dis++) {
            String role = Thread.currentThread().getName();
            //模拟兔子睡觉
            if (dis % 10 == 0 && role.equals("兔子")) {
                try {
                    Thread.sleep(50);//睡
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(role + " 跑了 " + dis);
            //每走一步，判断是否比赛结束
            if (gameOver(dis)) {
                break;
            }
        }
    }

    public boolean gameOver(int dis) {
        if (winner != null) {
            return true;
        } else if (dis == 100) {
            winner = Thread.currentThread().getName();
            System.out.println("获胜者是 " + winner);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Racer racer = new Racer();//1.创建实现类
        new Thread(racer, "兔子").start();//2.创建代理类并start
        new Thread(racer, "乌龟").start();
    }
}

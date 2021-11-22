package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends JFrame {
    int size = 30;
    boolean[][] cellsMap;
    JButton[][] cells;

    public Main(){

        Random rand = new Random();

        cellsMap = new boolean[size][size];
        cells = new JButton[size][size];

        setSize(1500, 1000);
        setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j <30; j++) {
                cellsMap[i][j] = rand.nextInt(100) < 30;
                JButton temp = new JButton();

                if(cellsMap[i][j])
                    temp.setBackground(Color.GRAY);
                else
                    temp.setBackground(Color.WHITE);
                add(temp);
                cells[i][j] = temp;
            }
        }

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Timer time = new Timer(100, e -> {
            boolean[][] temp = new boolean[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j <30; j++) {
                    int count = countNeighbors(i , j);

                    if(cellsMap[i][j]){
                        if(count < 2){
                            temp[i][j] = false;
                        }
                        if(count == 3 || count == 2){
                            temp[i][j] = true;
                        }
                        if(count > 3){
                            temp[i][j] = false;
                        }
                    }else{
                        if(count == 3){
                            temp[i][j] = true;
                        }
                    }
                }
            }

            cellsMap = temp;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j <30; j++) {
                    if(cellsMap[i][j]){
                        cells[i][j].setBackground(new Color(rand.nextInt(10), rand.nextInt(5), rand.nextInt(10)));
                    }else{
                        cells[i][j].setBackground(Color.WHITE);
                    }
                }
            }
        });

        time.start();
    }

    public int countNeighbors(int x, int y){
        int count = 0;

        for (int i = x - 1; i <= x + 1 ; i++) {
            for (int j = y - 1; j <= y + 1 ; j++) {
                try{
                    if(cellsMap[i][j]){
                        count++;
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }

        if(cellsMap[x][y]){
            count--;
        }
        return count;
    }

    public static void main(String[] args){
        new Main();
    }
}

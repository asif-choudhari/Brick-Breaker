package brickBreaker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class setLevel implements ActionListener {
    int paddleValue;
    int ballValue;
    setLevel(int paddleValue, int ballValue){
        this.paddleValue = paddleValue;
        this.ballValue = ballValue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame gamePlayFrame = new JFrame();
        gamePlay play = new gamePlay(paddleValue, ballValue);
        gamePlayFrame.setBounds(10, 10, 700, 600);
        gamePlayFrame.setTitle("Brick Breaker");
        gamePlayFrame.setResizable(false);
        gamePlayFrame.setVisible(true);

        gamePlayFrame.add(play);
    }
}
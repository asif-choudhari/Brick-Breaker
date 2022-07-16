package brickBreaker;

import javax.swing.*;
import java.awt.*;
public class mainClass extends JPanel {
    public static void main(String[] args) {
        JFrame initialFrame = new JFrame();
        initialFrame.setBounds(10,10,700,600);
        initialFrame.getContentPane().setBackground(Color.black);
        initialFrame.setBackground(Color.black);
        initialFrame.setResizable(false);
        initialFrame.setVisible(true);
        initialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton setLevelEasy = new JButton("Easy");
        setLevelEasy.setBounds(300,250,100,30);
        initialFrame.add(setLevelEasy);

        JButton setLevelMedium = new JButton("Medium");
        setLevelMedium.setBounds(300,300,100,30);
        initialFrame.add(setLevelMedium);

        JButton setLevelHard = new JButton("Hard");
        setLevelHard.setBounds(300,350,100,30);
        initialFrame.add(setLevelHard);

        JLabel select_level = new JLabel("Select Level");
        select_level.setForeground(Color.white);
        select_level.setFont(new Font("Calibre", Font.PLAIN, 30));
        select_level.setBounds(275,175, 300 ,30);

        initialFrame.add(select_level);


        setLevelEasy.addActionListener(new setLevel(20,1));
        setLevelMedium.addActionListener(new setLevel(15,2));
        setLevelHard.addActionListener(new setLevel(10,3));

    }
}

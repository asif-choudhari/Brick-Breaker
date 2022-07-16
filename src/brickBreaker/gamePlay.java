package brickBreaker;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gamePlay extends JPanel implements KeyListener, ActionListener{
    private boolean start = false;
    private int score = 0;

    private int totalBricks = 50;
    private final Timer timer;

    private int playerPos = 310;

    private int ballPosX = 310;
    private int ballPosY = 310;
    private int ballDirX = -1;
    private int ballDirY = -2;

    private blockGenerator map;
    int paddleSpeed, ballSpeed;

    public gamePlay(int paddleSpeed, int ballSpeed) {
        map = new blockGenerator(5,10);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        timer = new Timer(delay, this);
        timer.start();
        this.paddleSpeed = paddleSpeed;
        this.ballSpeed = ballSpeed;
    }

    public void paint(Graphics g) {
        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 692, 592);

        map.drawBlock((Graphics2D) g);

        //score
        g.setColor(Color.white);
        g.setFont(new Font("calibre", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);


        //paddle
        g.setColor(Color.yellow);
        g.fillRect(playerPos, 550, 100, 8);

        //ball
        g.setColor(Color.red);
        g.fillOval(ballPosX, ballPosY, 20, 20);

        if(ballPosY > 570) {
            start = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("calibre", Font.BOLD, 30));
            g.drawString("Game Over, the score is : "+ score, 190, 300);

            g.setFont(new Font("calibre", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        if(totalBricks <= 0) {
            start = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("calibre", Font.BOLD, 30));
            g.drawString("Game Over, score is : "+ score, 260, 300);

            g.setFont(new Font("calibre", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        g.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(start) {
            if (new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPos, 550, 30, 8))) {
                ballDirY = -ballDirY;
                ballDirX = -2;
            }
            else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPos + 70, 550, 30, 8)))
            {
                ballDirY = -ballDirY;
                ballDirX = ballDirX + 1;
            }
            else if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(playerPos + 30, 550, 40, 8)))
            {
                ballDirY = -ballDirY;
            }
            A:
            for (int i = 0; i < map.block.length; i++) {
                for (int j = 0; j < map.block[0].length; j++) {
                    if(map.block[i][j] > 0) {
                        int brickX = j* map.blockWidth +80;
                        int brickY = i* map.blockHeight + 50;
                        int brickWidth = map.blockWidth;
                        int brickHeight = map.blockHeight;

                        Rectangle paddleRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        if(ballRect.intersects(paddleRect)) {
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballPosX + 19 <= paddleRect.x || ballPosX + 1 >= paddleRect.x + paddleRect.width) {
                                ballDirX = -ballDirX;
                            } else {
                                ballDirY = -ballDirY;
                            }
                            break A;
                        }
                    }
                }
            }
            ballPosX += ballSpeed*ballDirX;
            ballPosY += ballSpeed*ballDirY;
            if (ballPosX < 0) {
                ballDirX = -ballDirX;
            }
            if (ballPosY < 0) {
                ballDirY = -ballDirY;
            }
            if (ballPosX > 670) {
                ballDirX = -ballDirX;
            }
            repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerPos >= 600) {
                playerPos = 600;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(playerPos < 10) {
                playerPos = 10;
            } else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!start) {
                start = true;
                ballPosX = 310;
                ballPosY = 310;
                ballDirX = -1;
                ballDirY = -2;
                score = 0;
                playerPos = 310;
                totalBricks = 50;
                map = new blockGenerator(5, 10);
                repaint();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveRight() {
        start = true;
        playerPos += paddleSpeed;
    }
    public void moveLeft() {
        start = true;
        playerPos -= paddleSpeed;
    }
}

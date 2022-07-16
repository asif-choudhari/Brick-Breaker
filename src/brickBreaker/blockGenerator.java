package brickBreaker;

import java.awt.*;


public class blockGenerator {
    public int[][] block;
    public int blockWidth;
    public int blockHeight;
    public blockGenerator(int row, int col) {
        block  = new int[row][col];
        for(int[] rows: block) {
            for(int i=0; i<col; i++) {
                rows[i] = 1;
            }
        }
        blockWidth = 540 / col;
        blockHeight = 300 / col;
    }

    public void drawBlock(Graphics2D block) {
        for (int i = 0; i < this.block.length; i++) {
            for (int j = 0; j < this.block[0].length; j++) {
                if(this.block[i][j] > 0) {
                    block.setColor(Color.white);
                    block.fillRect(j* blockWidth +80, i* blockHeight +50, blockWidth, blockHeight);
                    block.setStroke(new BasicStroke(3));
                    block.setColor(Color.black);
                    block.drawRect(j* blockWidth +80, i* blockHeight +50, blockWidth, blockHeight);
                }
                else{
                    block.setColor(Color.black);
                    block.fillRect(j* blockWidth +80, i* blockHeight +50, blockWidth, blockHeight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        this.block[row][col] = value;
    }
}
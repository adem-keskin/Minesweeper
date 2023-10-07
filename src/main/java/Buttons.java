import javax.swing.JButton;

public class Buttons extends JButton {
    private final int row;
    private final int col;
    private int count;
    private boolean mine, flag;

    public Buttons(int row, int col) {
        this.row = row;
        this.col = col;
        this.count = 0;
        this.mine = false;
        this.flag = false;
    }

    public int getRow() {

        return row;
    }

    public int getCol() {

        return col;
    }

    public int getCount() {

        return count;
    }

    public void setCount(int count) {

        this.count = count;
    }

    public boolean isMine() {

        return mine;
    }

    public void setMine(boolean mine) {

        this.mine = mine;
    }

    public boolean isFlag() {

        return flag;
    }

    public void setFlag(boolean flag) {

        this.flag = flag;
    }
}
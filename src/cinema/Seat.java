package cinema;


public class Seat {
    private int row;
    private int column;
    private int price;
    private boolean isPurchased;

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        this.isPurchased = false;

        if (row <= 4) {
            this.price = 10;
        } else {
            this.price = 8;
        }
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) { this.price = price; }

    public boolean isPurchased() {
        return isPurchased;
    }

    public void setPurchased(boolean isPurchased) {
        this.isPurchased = isPurchased;
    }
}

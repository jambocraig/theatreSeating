package theatre;

public class Theatre {
    private final int row;
    private final int col;
    private boolean[][] seats;

    Theatre(int r, int c){
        this.row = r;
        this.col = c;
        seats = new boolean[row][col];
    }

    public void populate(int load){
        clearSeats();
        int count = 0;
        int target = (int)(row * col * load)/100;
        while(count < target){
            int r = (int)(Math.random() * row);
            int c = (int)(Math.random() * col);
            if (!seats[r][c]){
                seats[r][c] = true;
                count++;
            }
        }
    }

    private void clearSeats() {
        for(int r = 0; r < row; r++){
            for(int c = 0; c < col; c++){
                seats[r][c] = false;
            }
        }
    }

    public void print(){
        System.out.println("\nSeat State");
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if(seats[row][col]){
                    System.out.print("x ");
                } else {
                    System.out.print("o ");
                }
            }
            System.out.println();
        }
        System.out.println(seatCount());
    }

    public int seatCount(){
        int count = 0;
        for (boolean[] row:seats) {
            for (boolean seat:row) {
                if (seat) count ++;
            }
        }
        return count;
    }

    public int getRowL() {
        return row;
    }

    public int getColL() {
        return col;
    }

    public boolean getState(int r, int c){
        return seats[r][c];
    }
}

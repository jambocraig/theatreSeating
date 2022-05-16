package theatre;

public class theatreSeatTest {
    public static void main(String[] args) {
        Theatre theatre = new Theatre(10, 15);
        theatre.populate(10);
        theatre.print();
    }
}

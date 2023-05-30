public class Ticket {
    private int row;
    private int seat;
    private double price;
    private person person;


    public Ticket(int row, int seat, double price, person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;
    }

    // getters and setters
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void print() {
        System.out.println("Name entered : " + person.getName());
        System.out.println("Surname entered: " + person.getSurname());
        System.out.println("Email entered: " + person.getEmail());
        System.out.println("Seating information\nRow: " + row);
        System.out.println("Seat: " + seat);
        System.out.println("Price: £" + price+"\n");
    }

    @Override
    public String toString() {
        return " Row " + row + ", Seat " + seat + ", Price £" + price + ", " +person;
    }

}

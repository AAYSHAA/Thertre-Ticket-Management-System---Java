import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class Theatre {
    public static double Row1Price;
    public static double Row2Price;
    public static double Row3Price;
    public static double price = 0;
    static int[][] seats = new int[3][]; // 2D array to store the seats
    static ArrayList<Ticket> ticketDetails = new ArrayList<>(); //creating an array to save all ticket details.

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("------------------------------------------------------------");
        System.out.println("    Welcome to the New Theatre! (STAFF USE ONLY!)");

        seats[0] = new int[12]; // Row 1 has 12 seats
        seats[1] = new int[16]; // Row 2 has 16 seats
        seats[2] = new int[20]; // Row 3 has 20 seats

        //Employee price validation.
        boolean SetPrice = true;
        while (SetPrice) {
            try {
                System.out.println("------------------------------------------------------------");
                System.out.println("Enter ticket price for each Row: ");

                //Row Price validation.
                do {
                    System.out.print("Row 1 : ");
                    Row1Price = input.nextInt();
                    if (Row1Price <= 0) {
                        System.out.println("Invalid price!\n");
                    }
                }
                while (Row1Price <= 0);{
                }
                do {
                    System.out.print("Row 2 : ");
                    Row2Price = input.nextInt();
                    if (Row2Price <= 0) {
                        System.out.println("Invalid price!\n");
                    }
                }
                while (Row2Price <= 0);{
                }
                do {
                    System.out.print("Row 3 : ");
                    Row3Price = input.nextInt();
                    if (Row3Price <= 0) {
                        System.out.println("Invalid price!\n");
                    }
                }
                while (Row3Price <= 0);{
                }
                if(Row1Price==Row2Price || Row1Price==Row3Price || Row3Price==Row2Price ){
                    System.out.println("Invalid pricing scheme\nPlease enter again!");
                    continue;
                }
                SetPrice = false;

            } catch (InputMismatchException nfe) {
                System.out.println("Integer required!\nPlease enter the ticket prices again.");
                input.nextLine();
                System.out.println();
            }

            System.out.println("\nPricing system!\n row-1: £"+Row1Price+"\n row-2: £"+Row2Price+"\n row-3: £"+Row3Price);
        }
        System.out.println("-------------------------------------------------\n    Welcome to the New Theatre! (STAFF USE ONLY!)");

        int option;
        do {
            System.out.println("""   
                    -------------------------------------------------
                    Please select an option:
                    1) Buy a ticket
                    2) Print seating area
                    3) Cancel ticket
                    4) List available seats
                    5) Save to file
                    6) Load from file
                    7) Print ticket information and total price
                    8) Sort tickets by price
                        0) Quit
                    -------------------------------------------------""");

            //validating input to have an integer.
            System.out.print("Enter option: ");
            try {
                option = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid option!,\n Please try again!");
                input.nextLine();
                continue;
            }

            switch (option) {
                case 1 -> buy_ticket();
                case 2 -> print_seating_area();
                case 3 -> cancel_ticket();
                case 4 -> show_available();
                case 5 -> save();
                case 6 -> load();
                case 7 -> show_tickets_info();
                case 8 -> sort_tickets();
                case 0 -> {
                    System.out.println("Thank you for using Theatre program!");
                    return;
                }
                default -> System.out.println("Invalid option!,\n Please try again!");
            }
        } while (true);
    }

    public static void buy_ticket() {
        System.out.println("Buy a ticket!");
        int row, seat;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the row number (1-3): ");

            //row exception handling.
            try {
                row = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid row number!,\nPlease enter a valid integer!\n");
                input.nextLine();
                continue;
            }

            //Row number validation.
            if (row < 1 || row > 3) {
                System.out.println("Invalid row number!\n");
                continue;
            }
            System.out.print("Enter the seat number (1-" + seats[row - 1].length + "): ");
            try {
                seat = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid seat number!,\nPlease enter a valid integer!\n");
                input.nextLine();
                continue;
            }

            //seat number validation.
            if (seat < 1 || seat > seats[row - 1].length) {
                System.out.println("Invalid seat number!\n");
                continue;
            }
            if (seats[row - 1][seat - 1] == 1) {
                System.out.println("This seat is already occupied!\n");
                continue;
            }

            //Extension of the buy ticket method,Task 12.
            input.nextLine();
            if (row == 1) {
                price = Row1Price;
            }
            if (row == 2) {
                price = Row2Price;
            }
            if (row == 3) {
                price = Row3Price;
            }

            System.out.print("Enter name: ");
            String name = input.nextLine();

            System.out.print("Enter surname: ");
            String surname = input.nextLine();

            //calling email validation class.
            String email = validEmail();
            System.out.println(email);


            //creating a person object,with 3 constructors.
            person person = new person(name, surname, email);

            //creating Ticket object with 3 constructors.
            Ticket ticket = new Ticket(row, seat, price, person);

            // Record seat as sold.
            seats[row - 1][seat - 1] = 1;
            ticketDetails.add(ticket);


            System.out.println("\nTicket details!\nName: " + name + "\n" + "Surname: " + surname + "\n" + "Email: " + email + "\n" + "Price: £" + price + "\n");
            System.out.println("Row "+row+" Seat "+seat+" has been sold.\n");
            break;
        }
    }

    // Task 4: print_seating_area method
    public static void print_seating_area() {
        System.out.println("Print Seat Area!");

        System.out.println("""
                     ***********
                     *  STAGE  *
                     ***********\
                """);
        for (int i = 0; i < seats.length; i++) {
            if (i == 0) {
                System.out.print("    ");
            }
            if (i == 1) {
                System.out.print("  ");
            }
            for (int j = 0; j < seats[i].length; j++) {
                if (seats[i][j] == 1) {
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
                if (j == ((seats[i].length / 2) - 1)) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    static void cancel_ticket() {
        System.out.println("Cancel Ticket!");
        int row;
        int seat;
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter row number: ");
                row = input.nextInt();
            } catch (InputMismatchException nfe) {
                System.out.println("Enter a valid number");
                input.nextLine();
                continue;
            }
            try {
                System.out.print("Enter seat number: ");
                seat = input.nextInt();
            } catch (InputMismatchException nfe) {
                System.out.println("Enter a valid number");
                input.nextLine();
                continue;
            }

            if (row < 1 || row > seats.length || seat < 1 || seat > seats[row - 1].length) { //Validation for row and seat numbers
                System.out.println("Invalid row or seat number.");
                return;
            }
            if (seats[row - 1][seat - 1] == 0) { //Validation for seat availability
                System.out.println("Seat is already available.");
                return;
            }
            for (Ticket ticket : ticketDetails) { //Cancellation of tickets
                if (ticket.getRow() == row && ticket.getSeat() == seat) {
                    ticketDetails.remove(ticket);
                    seats[row - 1][seat - 1] = 0;
                    System.out.println("Ticket cancelled successfully!");
                    break;
                }
            }
            break;
        }
    }

    static void show_available() {
        System.out.println("Available seats: ");

        for (int i = 0; i < 3; ++i) {
            System.out.print("Seats available in row " + (i + 1) + ": ");
            for (int j = 0; j < seats[i].length; ++j) {
                if (seats[i][j] == 0) { //Available seat check.
                    System.out.print(" "+(j+1));
                    if(j<seats[i].length-1){
                        System.out.print(",");
                    }
                    else{
                        System.out.print(".");
                    }
                }
            }
            System.out.println();
        }
    }

    static void save() {
        System.out.println("Save to file");
        try {
            //writing onto the file.
            FileWriter myWriter = new FileWriter("save.txt");
            for (int i = 0; i < seats.length; i++) {
                myWriter.write("Seats available in row " + (i + 1) + ": ");
                for (int j = 0; j < seats[i].length; j++) {
                    if (seats[i][j] == 1) {
                        myWriter.write("1");
                    } else {
                        myWriter.write("0");
                    }
                    if (j == ((seats[i].length / 2) - 1)) {
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file!");
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    static void load() {
        System.out.println("Load from file");

        //File not found error handling.
        try {
            File file = new File("save.txt");
            Scanner myReader = new Scanner(file); //Scanner class is to read the contents of the text file.
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public static void show_tickets_info() {
        System.out.println("Ticket information and total price\n");
        double totalPrice = 0.0;
        int ticketNumber = 1;

        for (Ticket ticket : ticketDetails) {
            System.out.println("Ticket number: " + ticketNumber + "\n");
            ticket.print();
            ticketNumber++;
            totalPrice += ticket.getPrice();
        }
        System.out.println("\nTotal price of all tickets: £" + totalPrice + "\n");
    }

    public static void sort_tickets() {
        //Bubble sort method
        Ticket temp;
        for (int i = 1; i < ticketDetails.size(); i++) {
            for (int j = 0; j < ticketDetails.size() - i; j++) {
                if (ticketDetails.get(j).getPrice() > ticketDetails.get(j + 1).getPrice()) {

                    temp = ticketDetails.get(j);
                    ticketDetails.set(j, ticketDetails.get(j + 1));
                    ticketDetails.set(j + 1, temp);
                }
            }
        }

        System.out.println("Sorted list of ticket information based on ticket price!\n");
        for (Ticket tick : ticketDetails) {
            tick.print();
            System.out.println();
        }
    }

    //Email validation class
    public static String validEmail(){
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.print("Enter Email: ");
            String email = input.next();

            boolean emailValidity = email.indexOf('@') >=1 && email.indexOf('.') >=1 && email.indexOf('@')<email.length()-1 && email.indexOf('.')<email.length()-1;
            if (emailValidity)  {
                return email;
            }else{
                System.out.println("Invalid email!");
            }
        }
    }
}




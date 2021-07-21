package cinema;

import java.util.Scanner;
import java.util.Arrays;

public class Cinema {

    public static void printMenu() {
        System.out.println();
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    public static void printSeats(char[][] array) {
        System.out.println();
        System.out.println("Cinema:");
        
        System.out.print("  ");
        for(int i = 1; i <= array[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i = 1; i <= array.length; i++) {
            System.out.print(i + " ");
            for(int j = 0; j < array[i-1].length; j++) {
                System.out.print(array[i-1][j] + " ");
            }
            System.out.println();
        }
    }
    public static void buyTicket(char[][] array) {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter a row number:");
        int x = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int y = sc.nextInt();
              
        
        System.out.println();
        
        if (x > array.length || y > array[0].length) {
           System.out.println("Wrong input!");
           buyTicket(array);
           return;
        } else if (array[x-1][y-1] == 'S') {
            System.out.print("Ticket price: ");
            System.out.println("$" + priceOfTicket(array, x));    
            array[x-1][y-1] = 'B'; 
        } else {
            System.out.println("That ticket has already been purchased!");
            buyTicket(array);
            return;
        }
        
    }
    public static int priceOfTicket(char[][] array, int x) {
        int totalSeats = array.length * array[0].length;
        if(totalSeats <= 60) {
            return 10;
        } else {
            int front = array.length / 2;
            if (x <= front) {
                return 10;
            } else {
                return 8;
            }
        }
    }
    public static int TotalIncome (char[][] array) {
        int totalSeats = array.length * array[0].length;
        if(totalSeats <= 60) {
            return 10*totalSeats;
        } else {
            int front = array.length / 2;
            int back = array.length - front;
            int width = array[0].length;
            return front * width * 10 + back * width * 8;
        }
    }
    public static void statistics(char[][] array) {
        // Number of purchased tickets: 2
        // Percentage: 5.56%
        // Current income: $20
        // Total income: $360
        int numberOfTickets = 0;
        
        int currentIncome = 0;
        int totalIncome = TotalIncome(array);
        int totalSeats = array.length * array[0].length;
        
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[0].length; j++) {
                if (array[i][j] == 'B') {
                    numberOfTickets++;    
                    currentIncome += priceOfTicket(array, i+1);
                }
            }
        }
        
        float Percentage = ((float)numberOfTickets / (float)totalSeats) * 100; 
        System.out.println();
        System.out.printf("Number of purchased tickets: %d\n",numberOfTickets);
        System.out.printf("Percentage: %.2f",Percentage);
        System.out.println("%");
        System.out.printf("Current income: $%d\n",currentIncome);
        System.out.printf("Total income: $%d\n",totalIncome);
    }
    
    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsInRow = sc.nextInt();
        
        char[][] arrayOfSeats = new char[rows][seatsInRow];
        for (char[] row : arrayOfSeats)
            Arrays.fill(row, 'S');
        
        // printMenu();
        while (true) {
            printMenu();
            int a = sc.nextInt();
            if (a == 1) {
                printSeats(arrayOfSeats);
            } else if (a == 2) {
                buyTicket(arrayOfSeats);
            } else if (a == 3) {
                statistics(arrayOfSeats);
            } else {
                break;
            }
        }
        
    }
}

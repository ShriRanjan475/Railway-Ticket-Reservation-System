import java.util.*;

public class TicketReservationSystem
{
    //function for booking ticket
    public static void reserveTicket(Passenger passenger)
    {
        TicketManager ticketManager = new TicketManager();
        //if no WL is available, means all tickets are filled, so no tickets available
        if (TicketManager.availableWaitingList == 0)
        {
            System.out.println("No Tickets Available");
            return;
        }
        //check if preferred berth is available
        if ((passenger.berthPreference.equals("L") && TicketManager.availableLowerBerths > 0) ||
                (passenger.berthPreference.equals("M") && TicketManager.availableMiddleBerths > 0) ||
                (passenger.berthPreference.equals("U") && TicketManager.availableUpperBerths > 0))
        {
            System.out.println("Preferred Berth Available");
            if (passenger.berthPreference.equals("L"))
            {
                System.out.println("Lower Berth Given");
                //call booking function in the TicketManager class
                ticketManager.reserveTicket(passenger, (TicketManager.lowerBerthsPositions.get(0)), "L");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                TicketManager.lowerBerthsPositions.remove(0);
                TicketManager.availableLowerBerths--;
            }
            else if (passenger.berthPreference.equals("M"))
            {
                System.out.println("Middle Berth Given");
                //call booking function in the TicketManager class
                ticketManager.reserveTicket(passenger, (TicketManager.middleBerthsPositions.get(0)), "M");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                TicketManager.middleBerthsPositions.remove(0);
                TicketManager.availableMiddleBerths--;
            }
            else if (passenger.berthPreference.equals("U"))
            {
                System.out.println("Upper Berth Given");
                //call booking function in the TicketManager class
                ticketManager.reserveTicket(passenger, (TicketManager.upperBerthsPositions.get(0)), "U");
                //remove the booked position from available positions and also decrease available seats of that
                // particular type
                TicketManager.upperBerthsPositions.remove(0);
                TicketManager.availableUpperBerths--;
            }
        }
        //preference not available -> book the available berth
        else if (TicketManager.availableLowerBerths > 0)
        {
            System.out.println("Lower Berth Given");
            //call booking function in the TicketManager class
            ticketManager.reserveTicket(passenger, (TicketManager.lowerBerthsPositions.get(0)), "L");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            TicketManager.lowerBerthsPositions.remove(0);
            TicketManager.availableLowerBerths--;
        }
        else if (TicketManager.availableMiddleBerths > 0)
        {
            System.out.println("Middle Berth Given");
            //call booking function in the TicketManager class
            ticketManager.reserveTicket(passenger, (TicketManager.middleBerthsPositions.get(0)), "M");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            TicketManager.middleBerthsPositions.remove(0);
            TicketManager.availableMiddleBerths--;
        }
        else if (TicketManager.availableUpperBerths > 0)
        {
            System.out.println("Upper Berth Given");
            //call booking function in the TicketManager class
            ticketManager.reserveTicket(passenger, (TicketManager.upperBerthsPositions.get(0)), "U");
            //remove the booked position from available positions and also decrease available seats of that
            // particular type
            TicketManager.upperBerthsPositions.remove(0);
            TicketManager.availableUpperBerths--;
        }
        // if no berth available go to RAC
        else if (TicketManager.availableRacTickets > 0)
        {
            System.out.println("RAC available");
            ticketManager.addToRAC(passenger, (TicketManager.racPositions.get(0)), "RAC");
        }
        // if no RAC available go to WL
        else if (TicketManager.availableWaitingList > 0)
        {
            System.out.println("Added to Waiting List");
            ticketManager.addToWaitingList(passenger, (TicketManager.waitingListPositions.get(0)), "WL");
        }
    }

    //cancel ticket function
    public static void cancelTicket(int id)
    {
        TicketManager ticketManager = new TicketManager();
        //check if passenger id is valid
        if (!ticketManager.passengers.containsKey(id))
        {
            System.out.println("Passenger detail Unknown");
        }
        else
            ticketManager.cancelTicket(id);
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //loop to get choices from user until he stops
        while (loop)
        {
            System.out.println(" 1. Book Ticket \n 2. Cancel Ticket \n 3. Available Tickets \n 4. Booked Tickets \n 5. Exit");
            int choice = scanner.nextInt();
            switch (choice)
            {
                // book ticket
                case 1:
                {
                    //get details from Passenger
                    System.out.println("Enter Passenger name, age, and berth preference (L, M, or U)");
                    String name = scanner.next();
                    int age = scanner.nextInt();
                    //get berth preference (L, U, M)
                    String berthPreference = scanner.next();
                    //create a passenger object
                    Passenger passenger = new Passenger(name, age, berthPreference);
                    //booking
                    reserveTicket(passenger);
                }
                break;
                //cancel ticket
                case 2:
                {
                    //get passenger id to cancel
                    System.out.println("Enter passenger Id to cancel");
                    int id = scanner.nextInt();
                    cancelTicket(id);
                }
                break;
                //available tickets print
                case 3:
                {
                    TicketManager ticketManager = new TicketManager();
                    ticketManager.printAvailable();
                }
                break;
                //occupied tickets print
                case 4:
                {
                    TicketManager ticketManager = new TicketManager();
                    ticketManager.printPassengers();
                }
                break;
                //exit
                case 5:
                {
                    loop = false;
                }
                break;
                default:
                    break;
            }
        }
    }
}

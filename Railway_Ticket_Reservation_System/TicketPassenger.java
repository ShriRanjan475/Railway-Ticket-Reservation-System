public class TicketPassenger
{
    static int passengerCounter = 1; // static variable to give an ID for every new passenger
    String passengerName;
    int passengerAge;
    String berthPreference; // U or L or M
    int passengerId; // ID of passenger created automatically
    String allottedType; // allotted type (L, U, M, RAC, WL)
    int seatNumber; // seat number

    public TicketPassenger(String name, int age, String berthPreference)
    {
        this.passengerName = name;
        this.passengerAge = age;
        this.berthPreference = berthPreference;
        this.passengerId = passengerCounter++;
        allottedType = "";
        seatNumber = -1;
    }
}

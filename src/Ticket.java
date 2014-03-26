public class Ticket {

    private String carNumber;

    public Ticket(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarNumber() {
        return this.carNumber;
    }

    @Override
    public boolean equals(Object o) {
        Ticket ticket = (Ticket) o;
        if (ticket.getCarNumber().equals(this.carNumber)) {
            return true;
        }
        return false;
    }

}



public class StandardDice implements Dice {
    private int numberOfDice;

    public StandardDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

  
    public int rollDice() {
        return (int) (Math.random() * (6 * numberOfDice - 1 * numberOfDice + 1)) + 1;
    }

    public int getNumberOfDice() {
        return numberOfDice;
    }

    public void setNumberOfDice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }
}

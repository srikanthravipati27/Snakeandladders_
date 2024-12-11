

import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GameBoard {
    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jumper> snakes;
    private List<Jumper> ladders;
    private Map<String, Integer> playersCurrentPosition;
    private int boardSize;

    public GameBoard(Dice dice, Queue<Player> nextTurn, List<Jumper> snakes, List<Jumper> ladders,
                     Map<String, Integer> playersCurrentPosition, int boardSize) {
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = playersCurrentPosition;
        this.boardSize = boardSize;
    }

    public void startGame() {
        while (nextTurn.size() > 1) {
            Player player = nextTurn.poll();
            int currentPosition = playersCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice();
            int nextCell = currentPosition + diceValue;

            if (nextCell > boardSize) {
                nextTurn.offer(player);
            } else if (nextCell == boardSize) {
                System.out.println(player.getPlayerName() + " won the game!");
            } else {
                int[] nextPosition = {nextCell};
                boolean[] gotLadder = {false};

                snakes.forEach(snake -> {
                    if (snake.getStartPoint() == nextCell) {
                        nextPosition[0] = snake.getEndPoint();
                    }
                });

                if (nextPosition[0] != nextCell) {
                    System.out.println(player.getPlayerName() + " was bitten by a snake at: " + nextCell);
                }

                ladders.forEach(ladder -> {
                    if (ladder.getStartPoint() == nextCell) {
                        nextPosition[0] = ladder.getEndPoint();
                        gotLadder[0] = true;
                    }
                });

                if (nextPosition[0] != nextCell && gotLadder[0]) {
                    System.out.println(player.getPlayerName() + " climbed a ladder at: " + nextCell);
                }

                if (nextPosition[0] == boardSize) {
                    System.out.println(player.getPlayerName() + " won the game!");
                } else {
                    playersCurrentPosition.put(player.getPlayerName(), nextPosition[0]);
                    System.out.println(player.getPlayerName() + " is now at position " + nextPosition[0]);
                    nextTurn.offer(player);
                }
            }
        }
    }
}



import java.util.*;

public class PlaySnakeAndLadder {
    public static void main(String[] args) {
        Dice dice = new StandardDice(1);
        Player player1 = new Player("Alberts", 1);
        Player player2 = new Player("Pintoss", 2);

        Queue<Player> allPlayers = new LinkedList<>();
        allPlayers.offer(player1);
        allPlayers.offer(player2);

        List<Jumper> snakes = Arrays.asList(
                new Jumper(10, 2),
                new Jumper(99, 12)
        );

        List<Jumper> ladders = Arrays.asList(
                new Jumper(5, 25),
                new Jumper(40, 89)
        );

        Map<String, Integer> playersCurrentPosition = new HashMap<>();
        playersCurrentPosition.put(player1.getPlayerName(), 0);
        playersCurrentPosition.put(player2.getPlayerName(), 0);

        GameBoard gameBoard = new GameBoard(dice, allPlayers, snakes, ladders, playersCurrentPosition, 100);
        gameBoard.startGame();
    }
}

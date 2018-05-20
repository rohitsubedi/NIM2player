import java.util.*;

public class ComputerStrategy {
    /**
     * Minimax Algorithm to calculate best move
     *
     * @param int numOfStones
     * @param boolean oponents
     *
     * @return int
     */
    public static int calculateBestMove(int numOfStones, boolean oponents) {
        if (numOfStones <= 0) {
            return 10;
        }

        if (numOfStones <= 6) {
            return -10;
        }

        if (oponents) {
            int bestValue = 1000;

            for (int i = 2; i <= 6; i++) {
                numOfStones = numOfStones - i;
                bestValue   = calculateBestMove(numOfStones, true);
                numOfStones = numOfStones + i;
            }

            return bestValue;
        } else {
            int bestValue = -1000;

            for (int i = 1; i <= 3; i++) {
                numOfStones = numOfStones - i;
                bestValue   = calculateBestMove(numOfStones, true);
                numOfStones = numOfStones + i;
            }

            return bestValue;
        }
    }

    /**
     * Get best number for computer move
     *
     * @param numOfStones
     *
     * @return int
     */
    public static int getComputerBestMoves(int numOfStones) {
        int computerBestMove = -1;
        int bestValue = -1000;
        int bestMove = -1;
        int moveValue;

        for (int i = 1; i <= 3; i++) {
            numOfStones = numOfStones - i;
            moveValue   = calculateBestMove(numOfStones, true);
            numOfStones = numOfStones + i;

            if (moveValue > bestValue) {
                bestValue = moveValue;
                bestMove  = i;
            }
        }

        return bestMove;
    }
}

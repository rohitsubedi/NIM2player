import java.util.*;

public class NIM2player
{
    /**
     * Print new line as per number provided
     *
     * @param int number
     */
    private static void newLines(int number) {
        for (int i = 0; i < number; i++) {
            System.out.println("");
        }
    }
    /**
     * Capitalize the string
     *
     * @param String name
     *
     * @return String
     */
    private static String capitalize(String name) {
        return name.toUpperCase().charAt(0) + name.substring(1);
    }

    /**
     * Main function of the game
     *
     * @param String[] args
     *
     * @return null
     */
    public static void main(String[] args) {
        boolean continuePlaying = false;
        // Variable to store Number of Stones in a pile that will be selected by first player.
        int numOfStones = 0;
        // array Variables for players name and id
        String playersName[];
        String playersId[];
        // Scanner to take input from users
        Scanner input = new Scanner(System.in);

        // Declare players Name
        playersName    = new String[3];
        playersId      = new String[3];
        playersName[2] = "Computer"; // Store 3rd player as Computer;
        playersId[2]   = "ComputerID"; // Store 3rd Id as Computer Id

        do {
            continuePlaying = false;
            int count = 0;
            // Loop to get names of both human players. Ask for name again if they dont give any
            while (count < 2) {
                System.out.printf(String.format("Please enter name of Player: %d\n", count + 1));

                String name = input.nextLine();

                System.out.printf(String.format("Please enter ID of Player: %d\n", count + 1));

                String id = input.nextLine();


                if (name.length() > 0 && id.length() > 0) {
                    playersName[count] = name;
                    playersId[count]   = id;

                    count++;
                } else {
                    System.out.printf("Name or ID cannot be empty\n");
                }

            }

            newLines(2);
            System.out.printf(
                String.format(
                    "Welcome %s to the game of Taking Stones\n",
                    capitalize(playersName[0])
                )
            );

            int choice = 1; // Random Number by default

            newLines(2);
            System.out.println("Choose from these choices for number of stones");
            System.out.println("-------------------------\n");
            System.out.println("1 - Random Number");
            System.out.println("2 - Own Strategy Number");

            while (true) {
                try {
                    choice = input.nextInt();
                    input.nextLine();

                    if (choice == 1) {
                        Random rand = new Random();
                        numOfStones = rand.nextInt(50) + 3;
                    } else if (choice != 2) {
                        System.out.printf("Choices should be 1 or 2\n");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.printf("Choices should be an integer\n");
                }
            }

            // Ask First Player to choose number of stones in the NIM stack. Ask until he provide the valid number
            while (choice == 2) {
                System.out.printf("Please enter the number of stones in NIM (Greator than 3)\n");

                try {
                    int num = input.nextInt();
                    input.nextLine();

                    if (num <= 3) {
                        System.out.printf("Number of Stones should be greator than 3\n");
                        continue;
                    }

                    numOfStones = num;
                    break;
                } catch (InputMismatchException e) {
                    System.out.printf("Number of Stones should be an integer\n");
                }
            }

            // Game Logic Starts here
            count = 0; // We start from First Player
            // Loop until all stones are empty
            while (numOfStones > 0) {
                int num = 0;
                count   = count % 3;

                newLines(2);
                System.out.printf(String.format("Number of stones left in the game is %d\n", numOfStones));
                System.out.printf(String.format("Player Turn: %s\n", capitalize(playersName[count])));

                if (count == 2) { // Computer turn
                    num = ComputerStrategy.getComputerBestMoves(numOfStones);

                    System.out.printf(String.format("Computer chooses to remove %d stones\n", num));
                } else {
                    System.out.printf("Please select the number of stones to be removed (1, 2, or 3)\n");
                    try {
                        num = input.nextInt();
                        input.nextLine();

                        if (num < 1 || num > 3) {
                            System.out.printf("Number of Stones should be between 1 and 3\n");
                            continue;
                        }
                    } catch (InputMismatchException e) {
                        System.out.printf("Number of Stones should be an integer\n");
                        continue;
                    }
                }

                numOfStones -= num;

                count++; // next player
            }

            count--; // previous player was the winner

            newLines(2);
            System.out.printf(
                String.format("Congratulations %s with ID(%s). You have WON the game\n", capitalize(playersName[count % 3]), playersId[count % 3])
            );
            newLines(2);
            System.out.printf("Do you want to play the game again ('yes', 'y')\n");
            String playerWish = input.nextLine();
            playerWish = playerWish.toLowerCase();

            if (playerWish.equals("yes") || playerWish.equals("y")) {
                continuePlaying = true;
                newLines(40);
                System.out.printf("Welcome to the game of Taking Stones\n");
            }
        } while (continuePlaying);
    }
}

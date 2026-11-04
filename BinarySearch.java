import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates an array of random sorted numbers and performs
 * a binary search based on user input.
 *
 * @author  Yoma Ozoh
 * @version 1.0
 * @since   2026-10-28
 */
public final class BinarySearch{

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private BinarySearch() {
        // Prevents creating an instance of this class
        throw new IllegalStateException("Utility class");
    }

    /**
     * Performs a binary search on an array of integers.
     *
     * @param num the number to search for
     * @param array the sorted array to search in
     * @return the index of the number if found, otherwise -1
     */
    public static int binarySearch(final int num, final int[] array) {
        // Track the lowest index in the current search range
        int low = 0;
        // Track the highest index in the current search range
        int high = array.length - 1;

        // Continue searching as long as the search range is valid
        while (low <= high) {
            // Find the middle index of the current range
            int midpoint = (low + high) / 2;

            // If the target number is in the right half
            if (array[midpoint] < num) {
                // Eliminate the left half by moving low past the midpoint
                low = midpoint + 1;
            // If the target number is in the left half
            } else if (array[midpoint] > num) {
                // Eliminate the right half by moving high before the midpoint
                high = midpoint - 1;
            // Target number was found at the midpoint
            } else {
                // Return the index where the number was found
                return midpoint;
            }
        }

        // Return -1 if the number was not in the array
        return -1;
    }

    /**
     * Main method to run the binary search program loop.
     *
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        // Initialize Scanner to get user input from the console
        final Scanner scanner = new Scanner(System.in);
        // Initialize Random object to generate random numbers
        final Random random = new Random();

        // Variable to control whether the main loop continues running
        boolean keepPlaying = true;

        // Main game loop
        while (keepPlaying) {
            // Create an array to hold 10 integers
            final int[] numbers = new int[10];

            // Fill the array with 10 random integers between 0 and 100
            for (int i = 0; i < 10; i++) {
                numbers[i] = random.nextInt(101);
            }

            // Sort the numbers in ascending order (required for binary search)
            Arrays.sort(numbers);

            // Print the prompt instructions for the user
            System.out.println("What number are you searching "
                + "for in the list below? Enter \"q\" to quit.");

            // Display all the sorted numbers in the array separated by spaces
            for (int number : numbers) {
                System.out.print(number + " ");
            }
            // ask the user for number
            System.out.print("\tNumber: ");

            // Read the user's input string and remove extra spaces
            final String inputString = scanner.nextLine().trim();

            // Check if the user entered "q" or "Q" to quit the program
            if (inputString.equalsIgnoreCase("q")) {
                // Print thank you message and exit the loop
                System.out.println("Thank you for playing!");
                keepPlaying = false;
            } else {
                try {
                    // Convert the input string to an integer
                    final int userNum = Integer.parseInt(inputString);
                    // Search for the integer using the binary search method
                    final int foundIndex = binarySearch(userNum, numbers);

                    // Check if the number was found or not
                    if (foundIndex == -1) {
                        // Print message telling user the number was not found
                        System.out.println("The number " + userNum
                            + " is not found in the list of numbers.\n");
                    } else {
                        // Print message showing where the number was found
                        System.out.println("The number " + userNum
                            + " is found at index " + foundIndex + ".\n");
                    }
                } catch (NumberFormatException e) {
                    // Handle invalid input that isn't an integer or 'q'
                    System.out.println("Please input a valid integer.\n");
                }
            }
        }

        // Close the scanner object to free up system resources
        scanner.close();
    }
}
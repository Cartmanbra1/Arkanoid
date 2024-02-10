/**
 * @author Roie Amsalem < royiamsalem@gmail.com >
 * @version 1.0
 * @since 2024-01-19
 */
// Roie Amsalem 322535436

/**
 * Utility class for parsing command line arguments.
 */
public class CommandLineArguments {

    /**
     * Parses an array of strings as integers and returns them as an integer array.
     *
     * @param args the array of strings representing command line arguments
     * @return an integer array containing the parsed values
     * @throws NumberFormatException if any argument cannot be parsed as an integer
     */
    public static int[] getArgumentsAsIntArray(String[] args) {
        int[] result = new int[4];
        for (int i = 0; i < 4; i++) {
            result[i] = Integer.parseInt(args[i]);
        }
        return result;
    }
}

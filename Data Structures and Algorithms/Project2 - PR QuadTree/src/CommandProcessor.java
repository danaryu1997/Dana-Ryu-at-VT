/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 *
 * @author CS Staff
 *
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires
     *  a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     *
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the
     *  file line is not
     * one of these, an appropriate message will be written in the console.
     *  This processor method is called for each line in the file.
     *  Note that the methods called will themselves write to the console,
     *  this method does not, only calling methods that do.
     *
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        String[] split = line.split("\\s+");
        String cmd = split[0];
        //System.out.println("--------------------------" +cmd);
        switch (cmd) {
            case "insert":
                Point ins = new Point(Integer.valueOf(split[2]),
                    Integer.valueOf(split[3]));
                KVPair<String, Point> pair =
                    new KVPair<String, Point>(split[1], ins);
                data.insert(pair);
                break;

            case "remove":
                if (split.length > 2) {
                    data.remove(Integer.valueOf(split[1]),
                        Integer.valueOf(split[2]));
                }
                else {
                    data.remove(split[1]);
                }
                break;

            case "regionsearch":
                data.regionsearch(Integer.valueOf(split[1]), Integer.valueOf(
                    split[2]), Integer.valueOf(split[3]), Integer.valueOf(
                        split[4]));
                break;

            case "duplicates":
                data.duplicates();
                break;

            case "search":
                data.search(split[1]);
                break;

            case "dump":
                data.dump();
                break;

            default:// Found an unrecognized command
                System.out.println("Unrecognized input: " + cmd);
                break;


        }

    }
}

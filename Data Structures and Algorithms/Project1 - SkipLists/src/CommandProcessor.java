import java.awt.Rectangle;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

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
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
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
     * file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        String[] split = line.split("\\s+");
        String cmd = split[0];
        switch (cmd) {
            case "insert":
                Rectangle rec = new Rectangle(Integer.valueOf(split[2]), Integer
                    .valueOf(split[3]), Integer.valueOf(split[4]), Integer
                        .valueOf(split[5]));
                KVPair<String, Rectangle> ins = new KVPair<String, Rectangle>(
                    split[1], rec);
                data.insert(ins);
                break;

            case "remove":
                if (split.length > 3) {
                    data.remove(Integer.valueOf(split[1]), Integer.valueOf(
                        split[2]), Integer.valueOf(split[3]), Integer.valueOf(
                            split[4]));

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

            case "intersections":
                data.intersections();
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

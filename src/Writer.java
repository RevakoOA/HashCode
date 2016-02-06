import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 * Created by just_me on 06.02.16.
 */
public class Writer {

    public static void writeCommandsToFile(String fileOUT, ArrayList<Command> commands) {
        try{
            FileWriter fileWriter = new FileWriter(fileOUT);
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(commands.size());
            for(Command command: commands) {
                switch (command.getType()) {
                    case PAINT_LINE:
                        out.write("PAINT_LINE " + command.getParams()[0] +" "+ command.getParams()[1] +" "+ command.getParams()[2] +" "
                                + command.getParams()[3]);
                        break;
                    case PAINT_SQUARE:
                        out.write("PAINT_SQUARE " + command.getParams()[0] +" "+ command.getParams()[1] +" "+ command.getParams()[2]);
                        break;
                    case ERASE_CELL:
                        out.write("ERASE_CELL " + command.getParams()[0] +" "+ command.getParams()[1]);
                        break;
                }

            }
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

/**
 * Created by just_me on 06.02.16.
 */
public class Command {
    public enum CommandType {
        PAINT_SQUARE, PAINT_LINE, ERASE_CELL
    }

    private CommandType type;
    private int[] params;

    public CommandType getType() {
        return type;
    }

    public int[] getParams() {
        return params;
    }

    public Command(CommandType type, int... params) {
        this.type = type;
        switch (type){
            case PAINT_SQUARE:
                if (params.length != 3) {
                    throw new IllegalArgumentException("Check number of params");
                }
                if (params[0] < 0 || params[1] < 0 || params[2] < 0) {
                    throw new IllegalArgumentException("param can't be less then zero");
                }
                this.params = params;
                break;
            case PAINT_LINE:
                if (params.length!=4) {
                    throw new IllegalArgumentException("Check number of params");
                }
                if (params[0] < 0 || params[1] < 0 || params[2] < 0 || params[3] < 0) {
                    throw new IllegalArgumentException("param can't be less then zero");
                }
                if (params[0] != params[2] && params[1] != params[3]) {
                    throw new IllegalArgumentException("line can only be horizontal of vertical");
                }
                this.params = params;
                break;
            case ERASE_CELL:
                if (params.length!=2) {
                    throw new IllegalArgumentException("Check number of params");
                }
                if (params[0] < 0 || params[1] < 0) {
                    throw new IllegalArgumentException("param can't be less then zero");
                }
                this.params = params;
                break;
        }

    }
}

import java.util.ArrayList;

/**
 * Created by just_me on 06.02.16.
 */
public class Main {

    private static boolean[][] originMap;

    public static void main(String[] args) {
        originMap = Reader.readMap("Example/logo.in");
//        showMap(originMap);
        ArrayList<Command> commands = new ArrayList<>();
        commands.add(new Command(Command.CommandType.PAINT_SQUARE, 3,4,2));
        commands.add(new Command(Command.CommandType.PAINT_LINE, 6,7,6,15));
        commands.add(new Command(Command.CommandType.PAINT_LINE, 6,7,10,7));
        commands.add(new Command(Command.CommandType.PAINT_LINE, 10,10,10,10));
        commands.add(new Command(Command.CommandType.PAINT_SQUARE, 11,11,0));
        commands.add(new Command(Command.CommandType.ERASE_CELL, 3, 4));
        showMap(createMapFromCommands(commands));
        Writer.writeCommandsToFile("Example/logo.out", commands);
    }

    //todo CREATE your algorithm HERE
    public static ArrayList<Command> analyze(boolean[][] map) {
        return new ArrayList<>();
    }

    public static void showMap(boolean[][] map) {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[0].length - 1; j++) {
                System.out.print((map[i][j]) ? '#' : '.');
            }
            System.out.println();
        }
    }

    public static boolean checkMaps(boolean[][] map1, boolean[][] map2) {
        if (map1.length != map2.length && map1[0].length != map2[0].length) {
            return false;
        }
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[0].length; j++) {
                if (map1[i][j] != map2[i][j]) {
                    System.out.println("checked - PISDEC");
                    return false;
                }
            }
        }
        System.out.println("checked - GOOD, My fuuuurrer");
        return true;
    }

    public static boolean[][] createMapFromCommands(ArrayList<Command> commands) {
        boolean result[][] = new boolean[originMap.length][originMap[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = false;
            }
        }
        for (Command command : commands) {
            switch (command.getType()) {
                case PAINT_LINE:
                    int x1 = command.getParams()[0];
                    int y1 = command.getParams()[1];
                    int x2 = command.getParams()[2];
                    int y2 = command.getParams()[3];
                    if (x1 == x2) {
                        for (int i = y1; i < y2 + 1; i++) {
                            result[x1][i] = true;
                        }
                    } else {
                        if (y1 == y2) {
                            for (int i = x1; i < x2 + 1; i++) {
                                result[i][y1] = true;
                            }
                        } else {
                            throw new IllegalArgumentException("Line params is incorrect");
                        }
                    }
                    break;
                case PAINT_SQUARE:
                    int cX = command.getParams()[0];
                    int cY = command.getParams()[1];
                    int S = command.getParams()[2];
                    for (int i = cX - S; i < cX + S + 1; i++) {
                        for (int j = cY - S; j < cY + S + 1; j++) {
                            result[i][j] = true;
                        }
                    }
                    break;
                case ERASE_CELL:
                    int x = command.getParams()[0];
                    int y = command.getParams()[1];
                    result[x][y] = false;
                    break;
            }
        }
        return result;
    }
}

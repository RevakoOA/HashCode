import java.awt.*;
import java.util.ArrayList;

/**
 * Created by just_me on 06.02.16.
 */
public class Main {

    private static boolean[][] originMap;
    private static boolean[][] genMap;
    public static void main(String[] args) {
        originMap = Reader.readMap("Example/logo.in");
        genMap = new boolean[originMap.length][originMap[0].length];
//        showMap(originMap);
        for (int i = 0; i < genMap.length - 1; i++) {
            for (int j = 0; j < genMap[0].length - 1; j++) {
                genMap[i][j] = false;
            }
        }
        ArrayList<Command> commands = analyze();
        Writer.writeCommandsToFile("Example/logo.out", commands);
        if (checkMaps(originMap, genMap)) {
            System.out.println("YEAH, baby!!!");
        }
        showMap(genMap);

    }

    //todo CREATE your algorithm HERE
    public static ArrayList<Command> analyze() {
        ArrayList<Command> commands = new ArrayList<>();
        Point CP = new Point(0,0);
        while ((CP = getNextPoint(CP)) != null) {
            int varX = 1;
            while (originMap[CP.x][CP.y + varX] && CP.y + varX <= originMap[0].length - 1) {
                varX++;
            }
            int varY = 1;
            while (originMap[CP.x + varY][CP.y] && CP.x + varY <= originMap.length - 1) {
                varY++;
            }
            if (varX >= varY) {
                commands.add(new Command(Command.CommandType.PAINT_LINE, CP.x, CP.y, CP.x, CP.y + varX-1));
            } else {
                commands.add(new Command(Command.CommandType.PAINT_LINE, CP.x, CP.y, CP.x + varY-1, CP.y));
            }
            addCommandToMap(genMap, commands.get(commands.size() - 1));
        }
        return commands;
    }

    public static Point getNextPoint(Point start){
        for (int i = start.x; i < originMap.length - 1; i++) {
            if (i == start.x) {
                for (int j = start.y; j < originMap[0].length - 1; j++) {
                    if (originMap[i][j] && !genMap[i][j]) {
                        return new Point(i,j);
                    }
                }
            } else {
                for (int j = 0; j < originMap[0].length - 1; j++) {
                    if (originMap[i][j] && !genMap[i][j]) {
                        return new Point(i,j);
                    }
                }
            }
        }
        return null;
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

    public static void addCommandToMap(boolean[][] map, Command command) {
        switch (command.getType()) {
            case PAINT_LINE:
                int x1 = command.getParams()[0];
                int y1 = command.getParams()[1];
                int x2 = command.getParams()[2];
                int y2 = command.getParams()[3];
                if (x1 == x2) {
                    for (int i = y1; i < y2 + 1; i++) {
                        map[x1][i] = true;
                    }
                } else {
                    if (y1 == y2) {
                        for (int i = x1; i < x2 + 1; i++) {
                            map[i][y1] = true;
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
                        map[i][j] = true;
                    }
                }
                break;
            case ERASE_CELL:
                int x = command.getParams()[0];
                int y = command.getParams()[1];
                map[x][y] = false;
                break;
        }
    }

    public static boolean[][] createMapFromCommands(ArrayList<Command> commands) {
        boolean result[][] = new boolean[originMap.length][originMap[0].length];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = false;
            }
        }
        for (Command command : commands) {
            addCommandToMap(result, command);
        }
        return result;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by just_me on 06.02.16.
 */
public class Reader {

    public static boolean[][] readMap(String path) {
        BufferedReader br = null;
        boolean[][] result;
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(path));
            sCurrentLine = br.readLine();
            String[] data = sCurrentLine.split(" ");
            if (data.length != 2) {
                throw new IllegalArgumentException();
            }
            int height = Integer.parseInt(data[0]);
            int width = Integer.parseInt(data[1]);
            result = new boolean[height][width];
            for (int i = 0; i < height - 1; i++) {
                sCurrentLine = br.readLine();
                for (int j = 0; j < width - 1; j++) {
                    result[i][j] = (sCurrentLine.charAt(j)=='#');
                }
            }
            return result;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}

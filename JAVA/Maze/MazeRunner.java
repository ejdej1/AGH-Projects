import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MazeRunner {


    public MazeRunner(String filename) {
        try {

            var maze = LoadMaze(filename);
            var path = MakePaths(maze);
            printSolution(maze, path);

        } catch (Exception e) {

            System.out.println("EXCEPTION encountered: " + e);
            System.out.println(Arrays.toString(e.getStackTrace()));

        }
    }

    private ArrayList<ArrayList<Integer>> LoadMaze(String path) throws Exception {

        ArrayList<ArrayList<Integer>> maze = new ArrayList<ArrayList<Integer>>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line = null;

        while ((line = reader.readLine()) != null) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            for (char a : line.toCharArray()) {
                if (a == 'S') {
                    tmp.add(1);
                } else if (a == 'F') {
                    tmp.add(2);
                } else if (a == 'C') {
                    tmp.add(0);
                } else if (a == 'W') {
                    tmp.add(-1);
                } else if (a != '\t') {
                    throw new Exception("Unknown character");
                }
            }
            maze.add(tmp);
        }
        reader.close();
        return maze;
    }

    private void NumberAdjacent(ArrayList<ArrayList<Integer>> maze, ArrayList<ArrayList<Integer>> paths, int x_i,
            int y_i, int iteration) {
        System.out.println(String.format("\n Moving to [%d, %d]:", y_i, x_i));
        
        int[][] x_y = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (var el : x_y){
            int x = x_i + el[0];
            int y = y_i + el[1];

            System.out.print(String.format("[%d]\t", iteration));
            System.out.print(String.format(" Checking [%d, %d]", y, x));

            try {
                int value = maze.get(x).get(y);
                if (value == 0) {
                   
                    System.out.print(" >> (path)");
                    if (paths.get(x).get(y) == 0) {
                        System.out.print(" >> (empty)");
                        paths.get(x).set(y, iteration);
                        System.out.print(String.format(" >> taking [%d, %d] at depth ", y, x, iteration));
                        
                        NumberAdjacent(maze, paths, x, y, iteration + 1);
                    } else {
                        System.out.print(String.format(" >> (taken) [depth %d]", paths.get(x).get(y)));
                    }
                } else {
                    if (value == 1) {
                        System.out.print(String.format(" >> (START) [depth %d]", paths.get(x).get(y)));
                    } else if (value == 2) {
                        System.out.print(String.format(" >> (FINISH) [depth %d]", paths.get(x).get(y)));
                        paths.get(x).set(y, iteration);
                    } else {
                        System.out.print(String.format(" >> (wall)", x, y));
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.print(String.format(" >> (bounds)", x, y));
            }
            System.out.println();
        }
        System.out.println(">> FINISHED DEPTH: " + iteration);
    }

    private ArrayList<ArrayList<Integer>> MakePaths(ArrayList<ArrayList<Integer>> maze) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        
        //FILLING TMP ARRAY WITH ZEROS 
        for (int i = 0; i < maze.size(); i++){
            ArrayList<Integer> temp = new ArrayList<>();
            for (int z = 0; z < maze.get(0).size(); z++){
                temp.add(0);
            }
            paths.add(temp);
        }


        int start_x = -1;
        int start_y = -1;
        int end_x = -1;
        int end_y = -1;


        //FINDING THE BEGGINING AND THE END OF THE MAZE
        for (int row = 0; row < maze.size(); row++) {
            for (int col = 0; col < maze.get(0).size(); col++) {
                int value = maze.get(row).get(col);
                if (value == 1) {
                    start_x = row;
                    start_y = col;
                }else if (value == 2){
                    end_x = row;
                    end_y = col;
                }
            }
            if (start_x != -1 && start_y != -1 && end_x != -1 && end_y != -1) {
                break;
            }
        }
        

        
        paths.get(start_x).set(start_y, 1);
        System.out.println(String.format("Start at [%d, %d], Finish at [%d, %d]", start_x, start_y, end_x, end_y));
        NumberAdjacent(maze, paths, start_x, start_y, 2);
        var way = findPath(paths, start_x, start_y, end_x, end_y);

        return way;

    }

    

    private ArrayList<ArrayList<Integer>> findPath(ArrayList<ArrayList<Integer>> path, int x_start, int y_start, int x_finish, int y_finish) {

        //CREATING EMPTY 2D
        ArrayList<ArrayList<Integer>> backPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int z = 0; z < path.get(0).size(); z++){
                temp.add(0);
            }
            backPath.add(temp);
        }

        backPath.get(x_start).set(y_start, -1);

        boolean finished = false;
        int tmp_x = x_finish;
        int tmp_y = y_finish;
        
        int[][] x_y = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        
        System.out.println(String.format("[%d, %d] << %d", tmp_x, tmp_y, path.get(tmp_x).get(tmp_y)));
        while(!finished) {
            int value = path.get(tmp_x).get(tmp_y);
            for (var mod : x_y){
                int x = tmp_x + mod[0];
                int y = tmp_y + mod[1];
                try {
                    int next = path.get(x).get(y);
                    System.out.print(String.format("[%d, %d] %d --> [%d, %d] %d \n", x, y, value, tmp_x, tmp_y, next));
                    if (next == 1){
                        finished = true;
                        break;
                    }
                    if(next + 1 == value){
                        System.out.println(String.format("moving to [%d, %d]", x, y));
                        backPath.get(x).set(y, 1);
                        tmp_x = x;
                        tmp_y = y;
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.print(String.format("(bounds [%d, %d])...\n", x, y));
                }
            }
        }

        return backPath;
    }

 

    private void printSolution(ArrayList<ArrayList<Integer>> maze, ArrayList<ArrayList<Integer>> path){
        for (int x = 0; x < path.size(); x++){
            for(int y = 0; y < path.get(x).size(); y++){
                if(maze.get(x).get(y) == -1){
                    System.out.print("\u001B[44m: \u001B[0m");
                }else if(path.get(x).get(y) == 0){
                    System.out.print("  ");
                }else{
                    System.out.print("\u001B[41m- \u001B[0m");
                }
            }
            System.out.println();
        }
    }
}
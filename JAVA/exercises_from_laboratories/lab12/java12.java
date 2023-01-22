import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class java12 {
    public static void main(String args[])
        throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String text = reader.readLine();
            createDirectory("/home/inni/vstrychalski/Desktop/data");
            File [] logFiles = new File[6];

            for (int i=0; i<logFiles.length ;i++){
                logFiles[i] = new File("log"+ i +".txt");
            }

            for (int i=logFiles.length; i>0; i--){
                File srcFile = logFiles[i-1];
                File destFile = logFiles[i];
                destFile.delete();
                srcFile.renameTo(destFile);
            }

            try{

                String line;
                
             while((line = reader.readLine()) != null){
                    if(line.isEmpty()){
                        break;
                    }
                    
             }

                BufferedReader bw = new BufferedReader(new FileWriter(logFiles[0]));
            } catch (IOException e){

            }


            System.out.println(text);
        }

    public static File createDirectory(String directoryPath) throws IOException {
        File dir = new File(directoryPath);
        if (dir.exists()){
            return dir;
        }
        if (dir.mkdir()){
            return dir;
        }
        throw new IOException("Failed to create directory" + dir.getAbsolutePath());
    }
}


import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class java12 {
    public static void main(String args[])
        throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //String text = reader.readLine();
            String line;
            String text = "";
            createDirectory("C:/Users/stryc/Documents/GitHub/AGH-Projects/JAVA/exercises_from_laboratories/lab12/test");
            File [] logFiles = new File[6];

            for (int i=0; i<logFiles.length ;i++){
                logFiles[i] = new File("C:/Users/stryc/Documents/GitHub/AGH-Projects/JAVA/exercises_from_laboratories/lab12/test/log"+ i +".txt");
            }
            while((line = reader.readLine()) != null){
                            if(line.isEmpty()){
                                break;
                            }      
                            text += line;
                            }

               
            //for (int i=0; i<6; i++){
            //    System.out.println(logFiles[i].getName());
            //}        
            for (int i=logFiles.length-1; i>0; i--){
                File srcFile = logFiles[i-1];
                File destFile = logFiles[i];
                System.out.println("---> Source File" + srcFile);
                System.out.println("---> Destination File" + destFile);
                destFile.delete();
                if(srcFile.renameTo(destFile)){
                    destFile.delete();
                    System.out.println("File renamed");
                }

                System.out.println("Source File" + srcFile.getName());
                System.out.println("Destination File" + destFile.exists());
            }
            
            File file = new File("C:/Users/stryc/Documents/GitHub/AGH-Projects/JAVA/exercises_from_laboratories/lab12/test/log0.txt");
            
                    if(file.createNewFile()){
                        FileWriter writer = new FileWriter("C:/Users/stryc/Documents/GitHub/AGH-Projects/JAVA/exercises_from_laboratories/lab12/test/log0.txt");
                        writer.write(text);
                        writer.close();
                        System.out.println("File created");
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


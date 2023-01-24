import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class java12 {
    public static void main(String args[])
        throws IOException {
            
            Scanner myObj = new Scanner(System.in);  
            System.out.println("Enter the path: ");
            String path = myObj.nextLine();


            System.out.println("Enter the text: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            //String text = reader.readLine();
            String line;
            String text = "";
            createDirectory(path+ "/test");
            File [] logFiles = new File[6];

            for (int i=0; i<logFiles.length ;i++){
                logFiles[i] = new File(path + "/test/log"+ i +".txt");
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
                
                if(srcFile.exists()){
                    if(destFile.exists()){
                        destFile.delete();
                    }
                    srcFile.renameTo(destFile);
                }
            }
                    
                        FileWriter writer = new FileWriter(path +"/test/log0.txt");
                        writer.write(text);
                        writer.close();
                       // System.out.println("File created");
                           
            
            
            
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


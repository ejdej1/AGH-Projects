/*      
        Ernest Strychalski
        Computer Science, 3. semester 
        
*/

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.*;
import javax.swing.*;



public class ImageRecognition {

    private static ArrayList<Point> magpiesCoords;
    public static void main (String args[])
    throws IOException{

        int test_image_array [][] = new int [512][512];
        int ref_image_array [][] = new int [94][68];
        
        ref_image_array = ImageToArray(94, 68, "ref_image.tif");
        test_image_array = ImageToArray (512,512,"test_image.tif");

        
        int result = GetMagpie(test_image_array, ref_image_array);
        System.out.println(result);

        int [][] finalarray = ClearImage(test_image_array, ref_image_array);
        

        new JFrame("Magpies image") {
            {
                final JLabel label = new JLabel("", new ImageIcon(DisplayImage(finalarray)), 0);
                add(label);
                pack();
                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                setVisible(true);
            }
        };

    }



    private static BufferedImage LoadImage(int width, int height, String path) {

         BufferedImage image = null;
        
        try{
            File input_file = new File(path);
                image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
                image = ImageIO.read(input_file);
                System.out.println("Reading Complete.");
                return image;
        }
        catch (IOException e) {
            System.out.println("Error" + e);
            return null;
        }
    
    }

    public static int [][] ImageToArray(int w, int h, String path){
        try{ 
        BufferedImage sourceImage = LoadImage(w,h, path);
        int width = sourceImage.getWidth();
        int height = sourceImage.getHeight();
        Color white_color = new Color(255,255,255);
        Color black_color = new Color(0,0,0);
        int count = 0;
        int [][] array = new int [width][height];
        
        /*  for(int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                count++;
                Color c = new Color(sourceImage.getRGB(j, i));
                
                int r = c.getRed();
                int g = c.getBlue();
                int b = c.getBlue();

                 if (r == 0 || g == 0 || b==0){
                    continue;
                } else {
                    sourceImage.setRGB(j, i, white_color.getRGB());
                }

                //System.out.println("S.No: " + count + " Red: " + c.getRed() +"  Green: " + c.getGreen() + " Blue: " + c.getBlue());
            }
        }

        File output = new File("test.tif");
        ImageIO.write(sourceImage, "tif", output); */

        for(int i=0; i<height; i++){
            for (int j=0; j<width; j++){

                count++;
                Color c = new Color(sourceImage.getRGB(j, i));
                int r = c.getRed();
                int g = c.getBlue();
                int b = c.getBlue();

                 if (r == 255 || g == 255 || b==255){
                    array [j][i] = 1; 

                } else {
                    array[j][i] = 0;
                    sourceImage.setRGB(j, i, white_color.getRGB());
                }

                
                System.out.print(array[j][i]);
            }
            //System.out.println("");
        }
           // File output = new File("test.tif");
           // ImageIO.write(sourceImage, "tif", output);
           
            return array;
            
        } catch (Exception e) {}
        return null;
    }



    public static void OutputImage(){
        try{
            File output_file = new File(
                "C:/Users/stryc/Desktop/Lab04/output-image.png");
            ImageIO.write(LoadImage(94, 68, "C:/Users/stryc/Desktop/Lab04/ref_image.tif" ), "png", output_file);
            System.out.println("Writing complete.");
        }
        catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }


    public static int GetMagpie(int [][] test_image_array, int [][] ref_image_array){
    
        int counter = 0;
        boolean magpieFound = false, notMagpie;
        magpiesCoords = new ArrayList<Point>();

        for (int ytest = 0; ytest < test_image_array.length - ref_image_array.length; ytest++) {
            for (int xtest = 0; xtest < test_image_array[0].length - ref_image_array[0].length; xtest++) {
                if(magpieFound){
                    counter++;
                    magpieFound = false;
                    magpiesCoords.add(new Point(xtest,ytest));
                }
                notMagpie = false;
                for (int yref = 0; yref < ref_image_array.length; yref++){
                    if(notMagpie){
                        magpieFound = false;
                        break;
                    }
                    for (int xref = 0; xref < ref_image_array[0].length; xref++) {
                        if(ref_image_array[yref][xref]== 1){
                            if(test_image_array[ytest+yref][xtest+xref] == ref_image_array[yref][xref]){
                                magpieFound = true;
                            }
                            else {
                                notMagpie = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return counter;
    }

    public static int [][] ClearImage(int [][] test_image_array, int [][] ref_image_array){
        int [][]finalArray = new int[test_image_array.length][test_image_array[0].length];
        int counter = 0;
        for (int i = 0; i < finalArray.length; i++) {
            for (int j = 0; j < finalArray[0].length; j++) {
                if(counter == magpiesCoords.size()) break;
                if(i == magpiesCoords.get(counter).y && j == magpiesCoords.get(counter).x){
                    for (int k = 0; k < ref_image_array.length; k++) {
                        for (int l = 0; l < ref_image_array[0].length; l++) {
                            finalArray[j+l][i+k] = ref_image_array[k][l];
                        }
                    }
                    counter++;
                }
            }
        }
        return finalArray;
    }

    public static BufferedImage DisplayImage(int [][] finalarray){
        BufferedImage image = new BufferedImage(finalarray.length, finalarray[0].length, BufferedImage.TYPE_BYTE_GRAY);
        Graphics g = image.getGraphics();
        for (int i = 0; i < finalarray.length; i++) {
            for (int j = 0; j < finalarray[0].length; j++) {
                if(finalarray[i][j] == 1){
                    g.setColor(Color.white);
                    g.fillRect(j,i,1,1);
                }
                else{
                    g.setColor(Color.black);
                    g.fillRect(j,i,1,1);
                }
            }
        }
        g = image.createGraphics();
        g.dispose();
        return image;
    }
    
}



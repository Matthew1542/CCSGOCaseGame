import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;
import java.util.concurrent.*;
import java.net.*;

public class InventoryItem {
     
     private String item;
     private double gunPrice;
     private String quality;
     private boolean statTrak;
     private String caseID;
     private int itemN;
     private int totalItems;
     
     public InventoryItem(String item, double gunPrice, String quality, boolean statTrak, String caseID, int itemN) {
          this.item = item;
          this.gunPrice = gunPrice;
          this.quality = quality;
          this.statTrak = statTrak;
          this.caseID = caseID;
          this.itemN = itemN;
          totalItems = getTotal();
     }

     public BufferedImage caseItems, caseOpened;
     
     private int getTotal(){
          BufferedReader br = null;
          String line = "";
          try {
               //br = new BufferedReader(new FileReader("resources/" + caseID + "/" + caseID + "Colors.csv"));
               URL urlToDictionary = this.getClass().getResource("resources/" + caseID + "/" + caseID + "Colors.csv");
               InputStream is = urlToDictionary.openStream();
               br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
               while ((line = br.readLine()) != null) {
                    String[] htemp = line.split(",");
                    totalItems = Integer.parseInt(htemp[0]) + Integer.parseInt(htemp[1]) + Integer.parseInt(htemp[2]) + Integer.parseInt(htemp[3]);
               }
          } catch (FileNotFoundException e) {
               e.printStackTrace();
          } catch (IOException e) {
          } finally {
               if (br != null) {
                    try {
                         br.close();
                    } catch (IOException e) {}
               }
          }
          return totalItems;
     }
     
     public BufferedImage getImageOne(){
          BufferedImageLoader loader = new BufferedImageLoader();
          try{
               caseItems = loader.loadImage("resources/" + caseID + "/" + caseID + ".png");
          }catch(IOException e){}
          SpriteSheet ss = new SpriteSheet(caseItems);
          caseItems = null;
          if(!statTrak){
               return ss.grabImage(itemN+1, 1, 100, 100);
          }
          if(statTrak){
               return ss.grabImage(itemN+1, 2, 100, 100);
          }
          return null;
     }
     
     public BufferedImage getImageTwo(){
          BufferedImageLoader loader = new BufferedImageLoader();
          try{   
               caseOpened = loader.loadImage("resources/" + caseID + "/" + caseID + "Opened.png");   
          }catch(IOException e){}  
          SpriteSheet openedSS = new SpriteSheet(caseOpened);
          caseOpened = null;
          return openedSS.grabImage(itemN + 1, 1, 500, 315);
     }
     
     public String toString(){
          return item
               + "," + gunPrice
               + "," + quality
               + "," + statTrak
               + "," + caseID
               + "," + itemN;
     }
     
     public String getItem(){
          return item;
     }
     public double getPrice(){
          return gunPrice;
     }
     public String getQuality(){
          return quality;
     }
     public boolean getStatTrak(){
          return statTrak;
     }
     
}
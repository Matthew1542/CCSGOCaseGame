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


public class SpectrumTwo {
     
     static Random r = new Random();
     private String item;
     private int itemN = 0;
     private int qualityN;
     private double gunPrice;
     private double floatV;
     private String quality;
     private String priceString;
     private int colorN;
     static int max = 10000;
     static int min = 0;
     private String color = "";
     private boolean statTrak;
     private Game game;
     private boolean openCase = false;
     private boolean h = false;
     private boolean gen50 = false;
     private int genNumber = 7;
     public static boolean opening = false;
     public static boolean opened = false;
     public static boolean addMoney = false;
     private SpectrumTwoPrices pricingSheet;
     private SpectrumTwoGuns gunSheet; 
     private double totalm = 0;
     private double totala = 0;
     private int place = 103;
     private int totalItems;
     private int blue;
     private int purple;
     private int pink;
     private int red;
     private double casePrice;
     private String caseName;
     private String caseID;
     
     public void open(int mx, int my){
          //System.out.println(mx + " " + my);
          if(Game.caseInventory.get(Game.ids.indexOf(caseID)) > 0){
               InventoryMenu.buyItem(mx, my, 2.49);
               Game.caseInventory.set(Game.ids.indexOf(caseID), Game.caseInventory.get(Game.ids.indexOf(caseID)) - 1);
               Game.money -= 2.49;
               openCase = true;
          }
          else{
               InventoryMenu.buyItem(mx, my, casePrice);
               Game.money -= (casePrice);
               Game.caseInventory.set(Game.ids.indexOf(caseID), Game.caseInventory.get(Game.ids.indexOf(caseID)) + 1);
          }
     }
     
     public BufferedImage setImage(int itemN, boolean stat){
          if(!stat)
               return ss.grabImage(itemN+1, 1, 100, 100);
          if(stat)
               return ss.grabImage(itemN+1, 2, 100, 100);
          return null;
     }
     
     public BufferedImage small(int itemN){
          return ss.grabImage(itemN+1, 1, 100, 100);
     }
     
     public BufferedImage setImageCase(int itemN){
          BufferedImageLoader loader = new BufferedImageLoader();
          BufferedImage caseOpened = null;
          try{
               caseOpened = loader.loadImage("resources/" + caseID + "/" + caseID + "Opened.png");   
          }catch(IOException e){}  
          SpriteSheet openedSS = new SpriteSheet(caseOpened);
          caseOpened = null;
          BufferedImage tempor = openedSS.grabImage(itemN + 1, 1, 500, 315);
          openedSS = null;
          return tempor;
     }      
     
     int moveby = 89;
     int counter = 0;
     int factor = 5;
     
     public void tick(){
          if(gen50){
               if(counter < factor)
                    counter++;
               if(counter >= factor){
                    moveby*=.9;
                    counter = 0;
               }
               if(moveby < 30)
                    factor = 25;
               else if(moveby < 50)
                    factor = 15;
               else if(moveby < 70)
                    factor = 10;
               
               for(int i = 0; i < genNumber; i++) {
                    ranImages.get(i).moveX(moveby);
                    if(ranImages.get(i).getX() < -150){
                         ranImages.remove(i);
                         genNumber--;
                    }
               }
               
               if(moveby <= 0){
                    try        
                    {
                         Thread.sleep(750);
                    }
                    catch(InterruptedException ex) 
                    {
                         Thread.currentThread().interrupt();
                    }
                    gen50 = false;
                    factor = 5;
                    moveby = 89;
                    opening = false;
                    opened = true;
               }
          }        
          if(openCase == true){
               ranImages.clear();
               genNumber = place+4;
               get50();
               openCrate();
               gunPrice = pricingSheet.getPrice(qualityN, itemN, statTrak);
               double temp = gunPrice*10;
               temp = (int)temp;
               temp = temp/10;
               if(gunPrice < .1)
                    priceString = "$" + gunPrice;
               else if(gunPrice - (int)gunPrice == .0)
                    priceString = "$" + gunPrice + "0";
               else if(gunPrice - temp == 0)
                    priceString = "$" + gunPrice + "0";
               else
                    priceString = "$" + gunPrice;
               ranImages.set(place,(new RanImages(-25 + 100*place, 50, itemN, statTrak)));
               //tempOpened = (setImageCase(itemN));
               Game.inventory.add(new InventoryItem(item, gunPrice, quality, statTrak, caseID, itemN));
               Game.saveInventory();
               InventoryMenu.loaded();
               opening = true;
          }
          
          if(addMoney){
               //Game.money += gunPrice;
               Game.money += Game.inventory.get(Game.inventory.size() - 1).getPrice();
               if(Game.inventory.size() == 1){
                    Game.inventory.clear();
               }
               else{
                    for(int i = Game.inventory.size() - 1; i < Game.inventory.size(); i++){
                         try{
                              Game.inventory.set(i, Game.inventory.get(i+1));
                         }catch(IndexOutOfBoundsException e){
                              Game.inventory.remove(i);
                         }
                    }
               }
               addMoney = false;
               Game.saveInventory();
               InventoryMenu.loaded();
          }

          /*Game.money += Game.inventory.get(gunPlace).getPrice();
          else{
               for(int i = gunPlace; i < Game.inventory.size(); i++){
               }
          }
          loaded();*/
     
     }
     
     public Rectangle transBack = new Rectangle(0, 0, 650, 478);
     Color transBlack = new Color(0, 0, 0, 127);
     
     public void render(Graphics g){
          Graphics2D g2d = (Graphics2D) g;
          Font fnt0 = new Font("arial", Font.BOLD, 40);
          Font fnt1 = new Font("ariel", Font.BOLD, 16);
          Font fnt2 = new Font("ariel", Font.BOLD, 20);
          g.setFont(fnt0);
          g.setColor(Color.white);
          g.setFont(fnt1);
          for(int i = 0; i < genNumber; i++) {
               g.drawImage(setImage(ranImages.get(i).getItemN(), ranImages.get(i).getStat()), ranImages.get(i).getX(), ranImages.get(i).getY(), null);         
          }
          if(Game.caseInventory.get(Game.ids.indexOf(caseID)) > 0){
               g.drawImage(Game.caseBackground, 0, 0, null);
          }
          else
               g.drawImage(Game.caseBackground2, 0, 0, null);
          int y = 0;
          int x = 0;
          for(int f = 0; f < totalItems+1; f++){
               g.drawImage(small(f), 84+x*(73+9), 167+y*(73+4), 73, 73, null);
               x++;
               if(x > 5){
                    y++;
                    x = 0;
               }
          }
          g.setFont(fnt2);
          g.drawString("Balance: " + Game.moneyString, 10, 20);
          g.drawString("Price: $" + casePrice, 10, 40);
          caseName = caseName.replace(" > ", " ");
          if(caseName.contains("Case")){}
          else
               caseName += " Case";
          g.setFont(fnt0);
          g.drawString("" + caseName, 175, 35);
          g.setFont(fnt1);
          if(opened){
               g.setColor(transBlack);
               g2d.fill(transBack);
               g.drawImage(Game.caseOpenedBackground, 50, 25, null);
               g.drawImage(setImageCase(itemN), 75, 50, null);
               g.setColor(Color.white);
               if(statTrak){
                    g.drawString("StatTrak", 90, 330);
                    g.drawString(priceString, 90, 310);
               }
               else
                    g.drawString(priceString, 90, 330);
               g.drawString(quality, 90, 350);
          }
          
          for(int i = 0; i < InventoryMenu.moneyClick.size(); i++)
          {
               int tempTrans = InventoryMenu.moneyClick.get(i).getTrans();
               if(tempTrans <= 0){
                    if(i == InventoryMenu.moneyClick.size() - 1)
                         InventoryMenu.moneyClick.clear();
               }
               else{
                    Color greenT = new Color(0, 255, 0, tempTrans);
                    g.setColor(greenT);
                    g.setFont(fnt1);
                    g.drawString(InventoryMenu.moneyClick.get(i).getSymbol(), InventoryMenu.moneyClick.get(i).getX(), InventoryMenu.moneyClick.get(i).getY()); 
               }
          }
          
          for(int i = 0; i < InventoryMenu.buyClick.size(); i++)
          {
               int tempTrans = InventoryMenu.buyClick.get(i).getTrans();
               if(tempTrans <= 0){
                    if(i == InventoryMenu.buyClick.size() - 1)
                         InventoryMenu.buyClick.clear();
               }
               else{
                    Color greenT = new Color(255, 0, 0, tempTrans);
                    g.setColor(greenT);
                    g.setFont(fnt1);
                    g.drawString(InventoryMenu.buyClick.get(i).getSymbol(), InventoryMenu.buyClick.get(i).getX(), InventoryMenu.buyClick.get(i).getY()); 
               }
          }
          
     }
     
     public void get50() {
          for(int i = 0; i < genNumber; i++){
               openCrate();
               ranImages.add(new RanImages(-25 + 100*i, 50, itemN, statTrak));
          }
          gen50 = true;
     }
     
     public double getcasePrice(){
          return casePrice;
     }
     
     ArrayList<RanImages> ranImages = new ArrayList<RanImages>();
     //private BufferedImage tempOpened;
     //private BufferedImage caseItems, caseOpened;
     private SpriteSheet ss;
     
     public SpectrumTwo(Game game, String caseID, String cN) {
          this.caseID = caseID;
          this.caseName = cN;
          BufferedReader br = null;
          String line = "";
          try {
               URL urlToDictionary = this.getClass().getResource("resources/" + caseID + "/" + caseID + "Colors.csv");
               InputStream is = urlToDictionary.openStream();
               br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
               while ((line = br.readLine()) != null) {
                    String[] htemp = line.split(",");
                    blue = Integer.parseInt(htemp[0]);
                    purple = Integer.parseInt(htemp[1]);
                    pink = Integer.parseInt(htemp[2]);
                    red = Integer.parseInt(htemp[3]);
                    totalItems = Integer.parseInt(htemp[0]) + Integer.parseInt(htemp[1]) + Integer.parseInt(htemp[2]) + Integer.parseInt(htemp[3]);
                    casePrice = Double.parseDouble(htemp[4]);
               }
          } catch (FileNotFoundException e) {
          } catch (IOException e) {
          } finally {
               if (br != null) {
                    try {
                         br.close();
                    } catch (IOException e) {}
               }
          }
////////////////////////////////////////////////////////////////////////          
          BufferedImageLoader loader = new BufferedImageLoader();
          BufferedImage caseItems = null;
          try{
               caseItems = loader.loadImage("resources/" + caseID + "/" + caseID + ".png");  
               //caseOpened = loader.loadImage("resources/" + caseID + "/" + caseID + "Opened.png");   
          }catch(IOException e){}
          ss = new SpriteSheet(caseItems);
          //openedSS = new SpriteSheet(caseOpened);
          caseItems = null;
          //caseOpened = null;
////////////////////////////////////////////////////////////////////////          
          
          pricingSheet = new SpectrumTwoPrices(caseID);
          gunSheet = new SpectrumTwoGuns(caseID);
          get50();
          gen50 = false;
          
     }
     
     public static boolean getOpening(){
          return opening;
     }
     public static boolean getOpened(){
          return opened;
     }
     public static void setOpened(boolean t){
          opened = t;
     }
     public static void setAddMoney(boolean t){
          addMoney = t;
     }
     
     public void openCrate(){
          openCase = false;
          double number = r.nextInt(10000)/100;
          String colorT = "";
          if(number <= 0.26){
               color = "Knife";
               itemN = totalItems;
          }
          if(number > 0.26){
               color = "Red";
               if(((blue+purple+pink+red-1)-(blue+purple+pink)) == 0)
                    itemN = (blue+purple+pink);
               else
                    itemN = r.nextInt((blue+purple+pink+red-1)-(blue+purple+pink))+(blue+purple+pink);
          }
          if(number > 0.64){
               color = "Pink";
               itemN = r.nextInt((blue+purple+pink-1)-(blue+purple))+(blue+purple);
          }
          if(number > 3.20){
               color = "Purple";
               itemN = r.nextInt((blue+purple-1)-blue)+blue;
          }
          if(number > 15.98){
               color = "Blue";
               itemN = r.nextInt(blue-1);
          }
          String datas = gunSheet.getInfo(itemN);
          String[] data = datas.split("~");
          item = data[0];
          quality = data[1];
          qualityN = Integer.parseInt(data[2]);
          if(Integer.parseInt(data[3]) != 0)
               statTrak = true;
          else
               statTrak = false;
     }
     
     public String toString(){
          if(statTrak)
               return "You opened a " + color + " StatTrak " + quality + " " + item + "\n" + gunPrice;
          else
               return "You opened a " + color + " " + quality + " " + item + "\n" + gunPrice;
     }
     
}
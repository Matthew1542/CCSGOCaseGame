import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class Menu {
     
     public static Rectangle button2 = new Rectangle(Game.WIDTH + 30 + Game.moveOver, 125, 120, 100);
     public static Rectangle button1 = new Rectangle(Game.WIDTH - 30 - 120 + Game.moveOver, 125, 120, 100);
     public static Rectangle button4 = new Rectangle(Game.WIDTH + 30 + Game.moveOver, 275, 120, 100);
     public static Rectangle button3 = new Rectangle(Game.WIDTH - 30 - 120 + Game.moveOver, 275, 120, 100);
     
     public static ArrayList<BufferedImage> caseImages = new ArrayList<BufferedImage>();
     private BufferedImage spectrum;
     public static int page = 0;
     
     public static void refreshVariables(){
          button2 = new Rectangle(Game.WIDTH + 30 + Game.moveOver, 125, 120, 100);
          button1 = new Rectangle(Game.WIDTH - 30 - 120 + Game.moveOver, 125, 120, 100);
          button4 = new Rectangle(Game.WIDTH + 30 + Game.moveOver, 275, 120, 100);
          button3 = new Rectangle(Game.WIDTH - 30 - 120 + Game.moveOver, 275, 120, 100);
     }
     
     
     SpriteSheet th;
     public Menu(Game game){
          th = new SpriteSheet(Game.cases);
          for(int i = 1; i < 27; i++){
               caseImages.add(th.grabImage(i, 1, 100, 100));
          }
     }
     
     public static void changePage(int temp){
          if(page + temp < 0 || page + temp >= Game.ids.size()){
          }
          else
               page = page + temp;
     }
     
     public void tick(){
          if(Game.menuOpened || Game.menuClosing){
               SideMenu.tick();
               refreshVariables();
          }
          else{
               refreshVariables();
          }
     }
     
     public static int sell = 0;
     
     public void render(Graphics g){
          if(Game.menuOpened){
               SideMenu.render(g);
          }
          Graphics2D g2d = (Graphics2D) g;
          g.drawImage(Game.background, 0 + Game.moveOver, 0, null);
          Color transBlack = new Color(0, 0, 0, 127);
          Font fnt0 = new Font("arial", Font.BOLD, 40);
          g.setFont(fnt0);
          g.setColor(Color.white);
          g.drawString("Pick A Case To Open", Game.WIDTH / 2 - 50 + Game.moveOver, 50);
          Font fnt1 = new Font("ariel", Font.BOLD, 20);
          g.setFont(fnt1);
          g.drawString("" + Game.moneyString, 10 + Game.moveOver, 25);
          g2d.draw(button1);
          g2d.draw(button2);
          g2d.draw(button3);
          g2d.draw(button4);
          g2d.fillRect(605 + Game.moveOver, 15, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 25, 30, 5);
          g2d.fillRect(605 + Game.moveOver, 35, 30, 5);
          g.setColor(transBlack);
          g2d.fillRect(button1.x + 1, button1.y + 1, 119, 99);
          g2d.fillRect(button2.x + 1, button2.y + 1, 119, 99);
          g2d.fillRect(button3.x + 1, button3.y + 1, 119, 99);
          g2d.fillRect(button4.x + 1, button4.y + 1, 119, 99);
          g.setColor(Color.white);       
          
          try{
               if(page < Game.ids.size()){
                    g.drawImage(caseImages.get(page), button1.x+10, button1.y, null);
                    if(Game.caseNames.get(page).contains(" > ")){
                         int t = Game.caseNames.get(page).indexOf(" > ");
                         g.drawString(Game.caseNames.get(page).substring(0, t), button1.x + 5, button1.y + 20);
                         g.drawString(Game.caseNames.get(page).substring(t+3, Game.caseNames.get(page).length()), button1.x + 5, button1.y + 95);
                    }
                    else{
                         g.drawString(Game.caseNames.get(page), button1.x + 5, button1.y + 20);
                         g.drawString("Case", button1.x + 5, button1.y + 95);
                    }
                    g2d.fillRect(button1.x+105, button1.y, 15, 20);
                    if(sell == 1){
                         g.setColor(Color.red);
                         g2d.fillRect(276+ Game.moveOver, 126, 14, 18);
                         g.setColor(Color.white);
                         g.drawString("$", button1.x+107, button1.y + 17);
                    }
                    else{
                         g.setColor(Color.black);
                         g2d.fillRect(276+ Game.moveOver, 126, 14, 18);
                         g.setColor(Color.white);
                         g.drawString(Game.caseInventory.get(page) + "", button1.x+107, button1.y + 17);
                    }
               }
               if(page+1 < Game.ids.size()){
                    g.drawImage(caseImages.get(page+1), button2.x+10, button2.y, null);
                    if(Game.caseNames.get(page+1).contains(" > ")){
                         int t = Game.caseNames.get(page+1).indexOf(" > ");
                         g.drawString(Game.caseNames.get(page+1).substring(0, t), button2.x + 5, button2.y + 20);
                         g.drawString(Game.caseNames.get(page+1).substring(t+3, Game.caseNames.get(page+1).length()), button2.x + 5, button2.y + 95);
                    }
                    else{
                         g.drawString(Game.caseNames.get(page+1), button2.x + 5, button2.y + 20);
                         g.drawString("Case", button2.x + 5, button2.y + 95);
                    }
                    g2d.fillRect(button2.x+105, button2.y, 15, 20);
                    if(sell == 2){
                         g.setColor(Color.red);
                         g2d.fillRect(456+ Game.moveOver, 126, 14, 18);
                         g.setColor(Color.white);
                         g.drawString("$", button2.x+107, button2.y + 17);
                    }
                    else{
                         g.setColor(Color.black);
                         g2d.fillRect(456+ Game.moveOver, 126, 14, 18);
                         g.setColor(Color.white);
                         g.drawString(Game.caseInventory.get(page + 1) + "", button2.x+107, button2.y + 17);
                    }
               }
               if(page+2 < Game.ids.size()){
                    g.drawImage(caseImages.get(page+2), button3.x+10, button3.y, null);
                    if(Game.caseNames.get(page+2).contains(" > ")){
                         int t = Game.caseNames.get(page+2).indexOf(" > ");
                         g.drawString(Game.caseNames.get(page+2).substring(0, t), button3.x + 5, button3.y + 20);
                         g.drawString(Game.caseNames.get(page+2).substring(t+3, Game.caseNames.get(page+2).length()), button3.x + 5, button3.y + 95);
                    }
                    else{
                         g.drawString(Game.caseNames.get(page+2), button3.x + 5, button3.y + 20);
                         g.drawString("Case", button3.x + 5, button3.y + 95);
                    }
                    g2d.fillRect(button3.x+105, button3.y, 15, 20);
                    if(sell == 3){
                         g.setColor(Color.red);
                         g2d.fillRect(276+ Game.moveOver, 276, 14, 18);    
                         g.setColor(Color.white);
                         g.drawString("$", button3.x+107, button3.y + 17);
                    }
                    else{
                         g.setColor(Color.black);
                         g2d.fillRect(276+ Game.moveOver, 276, 14, 18);    
                         g.setColor(Color.white);
                         g.drawString(Game.caseInventory.get(page+2) + "", button3.x+107, button3.y + 17);
                    }
               }
               if(page+3 < Game.ids.size()){
                    g.drawImage(caseImages.get(page+3), button4.x+10, button4.y, null);
                    if(Game.caseNames.get(page+3).contains(" > ")){
                         int t = Game.caseNames.get(page+3).indexOf(" > ");
                         g.drawString(Game.caseNames.get(page+3).substring(0, t), button4.x + 5, button4.y + 20);
                         g.drawString(Game.caseNames.get(page+3).substring(t+3, Game.caseNames.get(page+3).length()), button4.x + 5, button4.y + 95);
                    }
                    else{
                         g.drawString(Game.caseNames.get(page+3), button4.x + 5, button4.y + 20); 
                         g.drawString("Case", button4.x + 5, button4.y + 95);
                    }
                    g2d.fillRect(button4.x+105, button4.y, 15, 20);
                    if(sell == 4){
                         g.setColor(Color.red);
                         g2d.fillRect(456+ Game.moveOver, 276, 14, 18);    
                         g.setColor(Color.white);
                         g.drawString("$", button4.x+107, button4.y + 17);
                    }
                    else{
                         g.setColor(Color.black);
                         g2d.fillRect(456+ Game.moveOver, 276, 14, 18);                 
                         g.setColor(Color.white);
                         g.drawString(Game.caseInventory.get(page+3) + "", button4.x+107, button4.y + 17);
                    }
               }
          }catch(IndexOutOfBoundsException e){
               JOptionPane.showMessageDialog(null, "ERROR");
          }
          
          
          for(int i = 0;i < 6;i++){
               g2d.fillRect(75 + Game.moveOver - 5*i, 190 + 10*i, 5, 120 - 20*i);
               g2d.fillRect(560 + Game.moveOver + 5*i, 190 + 10*i, 5, 120 - 20*i);                   
          }
          
          if(Game.menuOpened){
               g.setColor(transBlack);
               g2d.fillRect(0 + Game.moveOver, 0, 650, 500);
          }
     }
}
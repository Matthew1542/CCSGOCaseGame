import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.image.BufferStrategy;
import javax.imageio.*;
import java.io.*;

public class MouseMotion implements MouseMotionListener{
     
     public static boolean sliderClicked = false;
     public static boolean dragStart = true;
     public static int initialY;
     
     @Override
     public void mouseMoved(MouseEvent e){
          int mx = e.getX();
          int my = e.getY();
          if(Game.menuOpened){
               
          }
          else{
               if(Game.State == Game.State.MENU){
                    if(mx >= 276 && mx <= 290 && my >= 126 && my <= 144){
                         Menu.sell = 1;
                    }
                    else if(mx >= 456 && mx <= 470 && my >= 126 && my <= 144){
                         Menu.sell = 2;
                    }
                    else if(mx >= 276 && mx <= 290 && my >= 276 && my <= 294){
                         Menu.sell = 3;
                    }
                    else if(mx >= 456 && mx <= 470 && my >= 276 && my <= 294){
                         Menu.sell = 4;
                    }
                    else
                         Menu.sell = 0;
               }
          }
     }
     
     @Override
     public void mouseDragged(MouseEvent e){
          int mx = e.getX();
          int my = e.getY();
          if(Game.menuOpened){
               
          }
          else{
               if(Game.State == Game.State.UPGRADE){
                    if(sliderClicked == true){
                         if(dragStart){
                              initialY = my;
                              dragStart = false;
                         }
                         int moveBy = my - initialY;
                         if(moveBy <= 55 + UpgradeMenu.sliderHeight && moveBy >= 0)
                              UpgradeMenu.moveValue = moveBy;
                         else if(!(moveBy <= 55 + UpgradeMenu.sliderHeight))
                              UpgradeMenu.moveValue = 321;
                         else if(!(moveBy >= 0 + UpgradeMenu.sliderHeight))
                              UpgradeMenu.moveValue = 0;
                    }    
               }
          }  
     }
     
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;

import java.awt.event.*;
/**
 *
 * @author Salvador
 */
public class keyListener implements KeyListener{
    
    int getPlayer2GameKey(int code){
        int keyPressed=-1;
        switch(code){
            case KeyEvent.VK_LEFT:{
                keyPressed = Defs.key_Left;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                keyPressed = Defs.key_Right;
                break;
            }
            case KeyEvent.VK_DOWN:{
                keyPressed = Defs.key_Down;
                break;
            }
            case KeyEvent.VK_UP:{
                keyPressed = Defs.key_Up;
                break;
            }
            case KeyEvent.VK_ENTER:{
                keyPressed = Defs.key_Fire;
                break;
            }
        }
        return keyPressed;
    }
    int getGameKey(int code){
        int keyPressed=-1;
        switch(code){
            
            /*case KeyEvent.VK_ENTER:{
                keyPressed = Defs.key_Enter;
                break;
            }*/
            
            case KeyEvent.VK_ESCAPE:{
                keyPressed = Defs.key_Esc;
                break;
            }
            case KeyEvent.VK_A:{
                keyPressed = Defs.key_Left;
                break;
            }
            case KeyEvent.VK_D:{
                keyPressed = Defs.key_Right;
                break;
            }
            case KeyEvent.VK_S:{
                keyPressed = Defs.key_Down;
                break;
            }
            case KeyEvent.VK_W:{
                keyPressed = Defs.key_Up;
                break;
            }
            case KeyEvent.VK_SPACE:{
                keyPressed = Defs.key_Fire;
                break;
            }
            case KeyEvent.VK_F1:{
                keyPressed = Defs.key_F1;
                break;
            }
            case KeyEvent.VK_F2:{
                keyPressed = Defs.key_F2;
                break;
            }
            case KeyEvent.VK_F3:{
                keyPressed = Defs.key_F3;
                break;
            }
            case KeyEvent.VK_F4:{
                keyPressed = Defs.key_F4;
                break;
            }
            case KeyEvent.VK_F5:{
                keyPressed = Defs.key_F5;
                break;
            }
            case KeyEvent.VK_F6:{
                keyPressed = Defs.key_F6;
                break;
            }
        }
        return keyPressed;
    }
    boolean player1Key(int code){
        if(getGameKey(code)!=-1)
            return true;
        return false;
    }
    public void keyPressed( KeyEvent key ){
        int code = key.getKeyCode();
        if (player1Key(code))
            spitBomb.player1keyPressed=getGameKey(code);
        else
            spitBomb.player2keyPressed=getPlayer2GameKey(code);
    }
    public void keyReleased( KeyEvent event )
    {
        int keyReleased=-1;
        int code = event.getKeyCode();
        
        if (player1Key(code)){
            keyReleased=getGameKey(code);
            if(keyReleased==spitBomb.player1keyPressed)
                spitBomb.player1keyPressed=-1;
        } else {
            keyReleased=getPlayer2GameKey(code);
            if(keyReleased==spitBomb.player2keyPressed)
                spitBomb.player2keyPressed=-1;
        }
        
    }
    public void keyTyped( KeyEvent event ){}
}

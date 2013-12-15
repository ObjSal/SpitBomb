/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;

import java.awt.*;
import java.awt.Image;
import java.io.BufferedInputStream;
//import javazoom.jl.player.Player;
/**
 *
 * @author Salvador
 */
public class BlockSprite {
    Image img=null;
    int x=0;
    int y=0;
    int type=0;
    
    BlockSprite(Image img, int type,int x, int y){
        this.img=img;
        this.type=type;
        this.x=x;
        this.y=y;
    }
    //Player player;
    /*void playSFX(){
        try{
            if(player!=null){
                player.close();
                System.gc();
            }
            
            BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("sounds/sfx_block.mp3"));
            player = new Player(bis);
            
        }catch(Exception e){System.out.println("error "+e.getMessage());}
        new Thread() {
            @Override public void run() {
                try {
                    player.play();
                }catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }*/
    
    void explode(){
        this.img=Images.floor;
        this.type=Defs.floor;
        //playSFX();
    }
    BlockSprite(int type,int x, int y){
        this.type=type;
        this.x=x;
        this.y=y;
        switch(type)
        {
            case Defs.hardBlock:
            {
                img=Images.HardBlock;
                break;
            }
            case Defs.softBlock:
            {
                img=Images.Block;
                break;
            }
        }
        
    }
    
    void paint(Graphics g){
        if(img!=null){
            g.drawImage(img,x,y,null);
        }
    }
}

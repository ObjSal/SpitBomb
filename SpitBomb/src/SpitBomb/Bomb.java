/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;

import java.awt.*;
import java.io.BufferedInputStream;
//import javazoom.jl.player.Player;
/**
 *
 * @author Salvador
 */
public class Bomb {
    //Player player;
    
    int direccion;
    int currImg;
    boolean finished=false;
    boolean internatFinished=false;
    int framesKilled=5;
    
    double x;
    double y;
    
    final double timeInImage=500;
    double lastTimeInImage;
            
    Image [][] imgs;
    
    Bomb(int direccion, int x, int y){
        this.direccion=direccion;
        this.x=x;
        this.y=y;
        
        imgs = new Image[4][3];
        loadImgs();
    }
    
    public void update(){
        if(!finished)
            switch(direccion){
                case Defs.Right:{
                    moveRight();
                    break;
                }
                case Defs.Left:{
                    moveLeft();
                    break;
                }
                case Defs.Up:{
                    moveUp();
                    break;
                }
                case Defs.Down:{
                    moveDown();
                    break;
                }
            }
    }
    /*void playSFX(){
        try{
            if(player!=null){
                player.close();
                System.gc();
            }
            
            BufferedInputStream bis = new BufferedInputStream(getClass().getResourceAsStream("sounds/sfx_bomb.mp3"));
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
    void moveLeft(){
        if(!internatFinished){
            if(x>0)
                x-=Defs.BombSteps;
            else{
                //finished=true;
                internatFinished=true;
                //playSFX();
            }
        }
    }
    
    void moveRight(){
        if(!internatFinished){
            if(x+Defs.BombWidth<spitBomb.screenWidth)
                x+=Defs.BombSteps;
            else{
                //finished=true;
                internatFinished=true;
                //playSFX();
            }
        }
    }
    
    void moveUp(){
        if(!internatFinished){
            if(y>0)
                y-=Defs.BombSteps;
            else{
                //finished=true;
                internatFinished=true;
                //playSFX();
            }
        }
    }
    
    void moveDown(){
        if(!internatFinished){
            if(y+Defs.BombHeight<spitBomb.screenWidth)
                y+=Defs.BombSteps;
            else{
                //finished=true;
                internatFinished=true;
                //playSFX();
            }
        }
    }
    
    void paint(Graphics g){
        if(!internatFinished){
            if(imgs!=null){
                if(System.currentTimeMillis()>lastTimeInImage+timeInImage){
                    lastTimeInImage=System.currentTimeMillis();
                    currImg++;
                }
                if(currImg>2)
                    currImg=0;
                g.drawImage(imgs[direccion][currImg],(int)x,(int)y,null);
            }
        } else {
            /*if(player==null)
                playSFX();*/
            g.drawImage(Images.explosion,(int)x-3,(int)y-10,50,50,null);
            if(framesKilled--==0)
                finished=true;
        }
    }
    
    void loadImgs(){
        for(int dir=0;dir<=Defs.Down;dir++)
            for(int img=0;img<3;img++){
                switch(dir)
                {
                    case Defs.Right:
                    {
                        switch(img)
                        {
                            case 0:
                            {
                                imgs[dir][img]=Images.Bomb_Right01;
                                break;
                            }
                            case 1:
                            {
                                imgs[dir][img]=Images.Bomb_Right02;
                                break;
                            }
                            case 2:
                            {
                                imgs[dir][img]=Images.Bomb_Right03;
                                break;
                            }
                        }
                        break;
                    }
                    case Defs.Left:
                    {
                        switch(img)
                        {
                            case 0:
                            {
                                imgs[dir][img]=Images.Bomb_Left01;
                                break;
                            }
                            case 1:
                            {
                                imgs[dir][img]=Images.Bomb_Left02;
                                break;
                            }
                            case 2:
                            {
                                imgs[dir][img]=Images.Bomb_Left03;
                                break;
                            }
                        }
                        break;
                    }
                    case Defs.Up:
                    {
                        switch(img)
                        {
                            case 0:
                            {
                                imgs[dir][img]=Images.Bomb_Up01;
                                break;
                            }
                            case 1:
                            {
                                imgs[dir][img]=Images.Bomb_Up02;
                                break;
                            }
                            case 2:
                            {
                                imgs[dir][img]=Images.Bomb_Up03;
                                break;
                            }
                        }
                        break;
                    }
                    case Defs.Down:
                    {
                        switch(img)
                        {
                            case 0:
                            {
                                imgs[dir][img]=Images.Bomb_Down01;
                                break;
                            }
                            case 1:
                            {
                                imgs[dir][img]=Images.Bomb_Down02;
                                break;
                            }
                            case 2:
                            {
                                imgs[dir][img]=Images.Bomb_Down01;
                                break;
                            }
                        }
                        break;
                    }
                }
            }
    }
}

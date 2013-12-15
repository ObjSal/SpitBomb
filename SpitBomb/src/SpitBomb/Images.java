/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package SpitBomb;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author salvador.guerrero
 */
public class Images {

    static Image PC_Right=null;
    static Image PC_Left = null;
    static Image PC_Up = null;
    static Image PC_Down = null;
    static Image PC_LeftOpen = null;
    static Image PC_RightOpen = null;
    static Image PC_DownOpen = null;
    static Image Menu = null;
    static Image floor = null;
    
    static Image Bomb_Right01 = null;
    static Image Bomb_Right02 = null;
    static Image Bomb_Right03 = null;
    static Image Bomb_Left01 = null;
    static Image Bomb_Left02 = null;
    static Image Bomb_Left03 = null;
    static Image Bomb_Up01 = null;
    static Image Bomb_Up02 = null;
    static Image Bomb_Up03 = null;
    static Image Bomb_Down01 = null;
    static Image Bomb_Down02 = null;
    static Image Bomb_Down03 = null;
    static Image explosion = null;
    static Image killed = null;
    
    //Menu
    static Image NewGame01=null;
    static Image NewGame02=null;
    static Image Continue01=null;
    static Image Continue02=null;
    static Image Multiplayer01=null;
    static Image Multiplayer02=null;
    static Image Help01=null;
    static Image Help02=null;
    static Image About01=null;
    static Image About02=null;
    static Image Exit01=null;
    static Image Exit02=null;
    static Image HardBlock = null;
    static Image Block = null;
            
    Images(){
        loadImages();
    }
    void loadImages(){
        Menu = new ImageIcon(getClass().getResource("img/Menu.png")).getImage();
        PC_Right = new ImageIcon(getClass().getResource("img/PC_Right.png")).getImage();
        PC_Left = new ImageIcon(getClass().getResource("img/PC_Left.png")).getImage();
        PC_Up = new ImageIcon(getClass().getResource("img/PC_Up.png")).getImage();
        PC_Down = new ImageIcon(getClass().getResource("img/PC_Down.png")).getImage();
        PC_LeftOpen = new ImageIcon(getClass().getResource("img/PC_LeftOpen.png")).getImage();
        PC_RightOpen = new ImageIcon(getClass().getResource("img/PC_RightOpen.png")).getImage();
        PC_DownOpen = new ImageIcon(getClass().getResource("img/PC_DownOpen.png")).getImage();
        
        Bomb_Right01 = new ImageIcon(getClass().getResource("img/Bomb_Right01.png")).getImage();
        Bomb_Right02 = new ImageIcon(getClass().getResource("img/Bomb_Right02.png")).getImage();
        Bomb_Right03 = new ImageIcon(getClass().getResource("img/Bomb_Right03.png")).getImage();
        Bomb_Left01 = new ImageIcon(getClass().getResource("img/Bomb_Left01.png")).getImage();
        Bomb_Left02 = new ImageIcon(getClass().getResource("img/Bomb_Left02.png")).getImage();
        Bomb_Left03 = new ImageIcon(getClass().getResource("img/Bomb_Left03.png")).getImage();
        Bomb_Up01 = new ImageIcon(getClass().getResource("img/Bomb_Up01.png")).getImage();
        Bomb_Up02 = new ImageIcon(getClass().getResource("img/Bomb_Up02.png")).getImage();
        Bomb_Up03 = new ImageIcon(getClass().getResource("img/Bomb_Up03.png")).getImage();
        Bomb_Down01 = new ImageIcon(getClass().getResource("img/Bomb_Down01.png")).getImage();
        Bomb_Down02 = new ImageIcon(getClass().getResource("img/Bomb_Down02.png")).getImage();
        Bomb_Down03 = new ImageIcon(getClass().getResource("img/Bomb_Down03.png")).getImage();
        explosion = new ImageIcon(getClass().getResource("img/explosion.png")).getImage();
        killed = new ImageIcon(getClass().getResource("img/killed.png")).getImage();
        
        NewGame01 = new ImageIcon(getClass().getResource("img/newGame01.png")).getImage();
        NewGame02 = new ImageIcon(getClass().getResource("img/newGame02.png")).getImage();
        Continue01 = new ImageIcon(getClass().getResource("img/Continue01.png")).getImage();
        Continue02 = new ImageIcon(getClass().getResource("img/Continue02.png")).getImage();
        Multiplayer01 = new ImageIcon(getClass().getResource("img/Multiplayer01.png")).getImage();
        Multiplayer02 = new ImageIcon(getClass().getResource("img/Multiplayer02.png")).getImage();
        Help01 = new ImageIcon(getClass().getResource("img/Help01.png")).getImage();
        Help02 = new ImageIcon(getClass().getResource("img/Help02.png")).getImage();
        About01 = new ImageIcon(getClass().getResource("img/About01.png")).getImage();
        About02 = new ImageIcon(getClass().getResource("img/About02.png")).getImage();
        Exit01 = new ImageIcon(getClass().getResource("img/Exit01.png")).getImage();
        Exit02 = new ImageIcon(getClass().getResource("img/Exit02.png")).getImage();
        
        HardBlock = new ImageIcon(getClass().getResource("img/HardBlock.png")).getImage();
        Block = new ImageIcon(getClass().getResource("img/Block.png")).getImage();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpitBomb;

import javax.swing.*;

/**
 *
 * @author Salvador
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    static JFrame f;
    public static void main(String[] args) {
        f=new JFrame("SpitBomb (Escupe Bombas)");
        spitBomb c = new spitBomb();
        keyListener kl = new keyListener();
        
        f.add(c);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(c.frameWidth, c.frameHeight);
        f.addKeyListener(kl);
        c.addKeyListener(kl);
        f.setVisible(true);
        new Thread(c).start();
        // TODO code application logic here
    }

}

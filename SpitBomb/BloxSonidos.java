/*
 * Blox
 * Clase BloxSonidos
 * Aquí está todo lo relacionado a sonidos
 * dev by jLab
 */

import java.io.InputStream;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class BloxSonidos{
	
	private static Sequencer player[];	
	
	public static void cargarSonidos(){
		player = new Sequencer[Const.rutasSonidos.length];
		Sequence currentSound;
		InputStream is;
		try{
			for(int i = 0; i < Const.rutasSonidos.length; i++){				
				currentSound = MidiSystem.getSequence(Blox.class.getResourceAsStream(Const.rutasSonidos[i]));			
				is = Blox.class.getResourceAsStream(Const.rutasSonidos[i]);
	            is.close();  
	            player[i] = MidiSystem.getSequencer();
	            player[i].open();
	            player[i].setSequence(currentSound);
	            if(i == Const.SONIDO_BGM)
	            	player[i].setLoopCount(Sequencer.LOOP_CONTINUOUSLY);        
			}
		}catch(Exception e){ e.printStackTrace(); }
	}
	
	public static void tocaBGM(){
		player[Const.SONIDO_BGM].start();
	}
	
	public static void paraBGM(){
		player[Const.SONIDO_BGM].setMicrosecondPosition(0);
		player[Const.SONIDO_BGM].stop();		
	}
	
	public static void tocaFX(int idSonido){
		player[idSonido].setMicrosecondPosition(0);
		player[idSonido].start();
	}
}

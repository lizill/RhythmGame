package dynamic_beat_21;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Note extends Thread {
	
	private Image noteBasicImage = new ImageIcon(Main.class.getResource("../images/noteBasic.png")).getImage();
	private int x, y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
	private String noteType;
	private boolean proceeded = true;
	
	public String getNoteType() {
		return noteType;
	}
	
	public boolean isProceeded() {
		return proceeded;
	}
	
	public void close() {
		proceeded = false;
	}
	
	public Note(String noteType, String difficulty) {
		if (difficulty.equals("Hard")) {
			if(noteType.equals("S")) {
				x = 228;
			} else if(noteType.equals("D")) {
				x = 332;
			} else if(noteType.equals("F")) {
				x = 436;
			} else if(noteType.equals("Space")) {
				x = 540;
			} else if(noteType.equals("J")) {
				x = 744;
			} else if(noteType.equals("K")) {
				x = 848;
			} else if(noteType.equals("L")) {
				x = 952;
			}
		}
		else if (difficulty.equals("Easy")) {
			if(noteType.equals("D")) {
				x = 436;
			} else if(noteType.equals("F")) {
				x = 540;
			} else if(noteType.equals("J")) {
				x = 644;
			} else if(noteType.equals("K")) {
				x = 748;
			}
		}
		this.noteType = noteType;
	}
	
	public void screenDraw(Graphics2D g) {
		if(!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		}
		else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x+100, y, null);
		}
	}
	
	public void drop() {
		y += Main.NOTE_SPEED;
		if(y > 620)	close();
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				drop();
				if(proceeded) {
					Thread.sleep(Main.SLEEP_TIME);
				} else {
					interrupt();
					break;
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String judge() { //580
		if(y > 600 && y <= 620) { // 587
			close();
			return "Good";
		} else if(y > 560 && y <= 600) { // 573
			close();
			return "Perfect";
		} else if(y > 540 && y <= 560) { // 565
			close();
			return "Great";
		} else if(y > 520 && y <= 540) { // 550
			close();
			return "Good";
		}
		return "None";
	}
	
	public int getY() {
		return y;
	}
}

package dynamic_beat_20;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import javazoom.jl.player.Player;

public class Game extends Thread {
	
	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	private Image resultBackground = new ImageIcon(Main.class.getResource("../images/resultBackground.png")).getImage();
	
	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	private Music resultMusic = new Music("resultMusic.mp3", true);
	private int score, combo, miss, good, great, perfect;
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	ArrayList<Integer> comboList = new ArrayList<Integer>();
	
	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false) {
			@Override
			public void run() {
				super.run();

				DynamicBeat.isGameResult = true;
				resultMusic.start();
			}
		};
	}
	
	public void screenDraw(Graphics2D g) {
		if(!DynamicBeat.isGameResult) {
			if(difficulty.equals("Easy")) {
				g.drawImage(noteRouteDImage, 436, 30, null);
				g.drawImage(noteRouteFImage, 540, 30, null);
				g.drawImage(noteRouteJImage, 644, 30, null);
				g.drawImage(noteRouteKImage, 748, 30, null);
				g.drawImage(noteRouteLineImage, 432, 30, null);
				g.drawImage(noteRouteLineImage, 536, 30, null);
				g.drawImage(noteRouteLineImage, 640, 30, null);
				g.drawImage(noteRouteLineImage, 744, 30, null);
				g.drawImage(noteRouteLineImage, 848, 30, null);
				g.drawImage(gameInfoImage, 0, 660, null);
				g.drawImage(judgementLineImage, 0, 580, null);
				for(int i=0; i<noteList.size(); i++) {
					Note note = noteList.get(i);
					if(note.getY() > 620) {
						System.out.println("Miss");
						judgeEvent("Miss");
					}
					if(!note.isProceeded()) {
						noteList.remove(i);
						i--;
					} else {
						note.screenDraw(g);
					}
				}
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.setColor(Color.white);
				g.setFont(new Font("Airal", Font.BOLD, 30));
				g.drawString(titleName, 20, 702);
				g.drawString(difficulty, 1192, 702);
				g.setFont(new Font("Airal", Font.PLAIN, 26));
				g.setColor(Color.DARK_GRAY);
				g.drawString("D", 478, 609);
				g.drawString("F", 582, 609);
				g.drawString("J", 686, 609);
				g.drawString("K", 790, 609);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.drawString(String.format("%06d", score), 565, 702);
				g.setColor(Color.white);
				g.setFont(new Font("SANS_SERIF", Font.BOLD, 62));
				g.drawString(String.valueOf(combo), 960, 340);
				g.drawImage(blueFlareImage, 500, 405, null);
				g.drawImage(judgeImage, 460, 420, null);
				g.drawImage(keyPadDImage, 436, 580, null);
				g.drawImage(keyPadFImage, 540, 580, null);
				g.drawImage(keyPadJImage, 644, 580, null);
				g.drawImage(keyPadKImage, 748, 580, null);
			}
			else if(difficulty.equals("Hard")) {
				g.drawImage(noteRouteSImage, 228, 30, null);
				g.drawImage(noteRouteDImage, 332, 30, null);
				g.drawImage(noteRouteFImage, 436, 30, null);
				g.drawImage(noteRouteSpace1Image, 540, 30, null);
				g.drawImage(noteRouteSpace2Image, 640, 30, null);
				g.drawImage(noteRouteJImage, 744, 30, null);
				g.drawImage(noteRouteKImage, 848, 30, null);
				g.drawImage(noteRouteLImage, 952, 30, null);
				g.drawImage(noteRouteLineImage, 224, 30, null);
				g.drawImage(noteRouteLineImage, 328, 30, null);
				g.drawImage(noteRouteLineImage, 432, 30, null);
				g.drawImage(noteRouteLineImage, 536, 30, null);
				g.drawImage(noteRouteLineImage, 740, 30, null);
				g.drawImage(noteRouteLineImage, 844, 30, null);
				g.drawImage(noteRouteLineImage, 948, 30, null);
				g.drawImage(noteRouteLineImage, 1052, 30, null);
				g.drawImage(gameInfoImage, 0, 660, null);
				g.drawImage(judgementLineImage, 0, 580, null);
				for(int i=0; i<noteList.size(); i++) {
					Note note = noteList.get(i);
					if(note.getY() > 620) {
						System.out.println("Miss");
						judgeEvent("Miss");
					}
					if(!note.isProceeded()) {
						noteList.remove(i);
						i--;
					} else {
						note.screenDraw(g);
					}
				}
				g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g.setColor(Color.white);
				g.setFont(new Font("Airal", Font.BOLD, 30));
				g.drawString(titleName, 20, 702);
				g.drawString(difficulty, 1192, 702);
				g.setFont(new Font("Airal", Font.PLAIN, 26));
				g.setColor(Color.DARK_GRAY);
				g.drawString("S", 270, 609);
				g.drawString("D", 374, 609);
				g.drawString("F", 478, 609);
				g.drawString("Space Bar", 580, 609);
				g.drawString("J", 784, 609);
				g.drawString("K", 889, 609);
				g.drawString("L", 993, 609);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.drawString(String.format("%06d", score), 565, 702);
				g.setColor(Color.white);
				g.setFont(new Font("SANS_SERIF", Font.BOLD, 62));
				g.drawString(String.valueOf(combo), 1100, 340);
				g.drawImage(blueFlareImage, 500, 405, null);
				g.drawImage(judgeImage, 460, 420, null);
				g.drawImage(keyPadSImage, 228, 580, null);
				g.drawImage(keyPadDImage, 332, 580, null);
				g.drawImage(keyPadFImage, 436, 580, null);
				g.drawImage(keyPadSpace1Image, 540, 580, null);
				g.drawImage(keyPadSpace2Image, 640, 580, null);
				g.drawImage(keyPadJImage, 744, 580, null);
				g.drawImage(keyPadKImage, 848, 580, null);
				g.drawImage(keyPadLImage, 952, 580, null);
			}
		} else if(DynamicBeat.isGameResult) {
			comboList.add(combo);
			if(score > DynamicBeat.userScore && !DynamicBeat.userID.equals("GEST")) {
				DynamicBeat.userScore = score;
				DynamicBeat.DB.setUserScore(DynamicBeat.userID, DynamicBeat.userScore);
			} else if (score > DynamicBeat.userScore && DynamicBeat.userID.equals("GEST")) DynamicBeat.userScore = score;
			g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g.drawImage(resultBackground, 0, 0, null);
			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("SANS_SERIF", Font.BOLD, 48));
			g.drawString(String.format("%06d", score), 720, 210);
			g.drawString(String.format("%04d", maxCombo(comboList)), 832, 484);
			g.drawString(String.format("%04d", perfect), 620, 348);
			g.drawString(String.format("%04d", great), 620, 412);
			g.drawString(String.format("%04d", good), 620, 475);
			g.drawString(String.format("%04d", miss), 620, 539);
		}
	}
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("tomSmall.mp3", false).start();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("kick.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadDImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("snare.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadFImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("crash.mp3", false).start();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadSpace1Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
		keyPadSpace2Image = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("hi-Hat.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadJImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("tomMidle.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadKImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadPressed.png")).getImage();
//		new Music("ride.mp3", false).start();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
		keyPadLImage = new ImageIcon(Main.class.getResource("../images/keyPadBasic.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		resultMusic.close();
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		
		if(titleName.equals("Homura") && difficulty.equals("Easy")) {
			int startTime = 2000;
			int gap = 395;
			beats = new Beat[] {
//					------------------------------------ 1    D F J K
					new Beat(startTime + gap*0, "J"),
					new Beat(startTime + gap*1, "D"),
					new Beat(startTime + gap*2, "F"),
					new Beat(startTime + gap*3, "D"),
					new Beat(startTime + gap*4, "F"),
					new Beat(startTime + gap*5, "K"),
					new Beat(startTime + gap*6, "J"),
					new Beat(startTime + gap*7, "K"),
//					------------------------------------ 2
					new Beat(startTime + gap*8, "J"),
					new Beat(startTime + gap*9, "D"),
					new Beat(startTime + gap*10, "F"),
					new Beat(startTime + gap*11, "D"), new Beat(startTime + gap*11, "K"),
//					new Beat(startTime + gap*12, "F"),
//					new Beat(startTime + gap*13, "K"),
//					new Beat(startTime + gap*14, "J"),
//					new Beat(startTime + gap*15, "F"),
//					------------------------------------ 3
					new Beat(startTime + gap*16, "F"),
					new Beat(startTime + gap*17, "K"),
					new Beat(startTime + gap*18, "J"),
					new Beat(startTime + gap*19, "K"),
					new Beat(startTime + gap*20, "J"),
					new Beat(startTime + gap*21, "D"),
					new Beat(startTime + gap*22, "F"),
					new Beat(startTime + gap*23, "D"),
//					------------------------------------ 4
					new Beat(startTime + gap*24, "F"),
					new Beat(startTime + gap*25, "K"),
					new Beat(startTime + gap*26, "J"),
					new Beat(startTime + gap*27, "D"), new Beat(startTime + gap*27, "K"),
//					new Beat(startTime + gap*28, "F"),
//					new Beat(startTime + gap*29, "K"),
					new Beat(startTime + gap*30, "D"),
//					new Beat(startTime + gap*31, "K"),
//					------------------------------------ 5
//					new Beat(startTime + gap*32, "D"),
					new Beat(startTime + gap*33, "K"),
//					new Beat(startTime + gap*34, "F"),
//					new Beat(startTime + gap*35, "K"),
//					new Beat(startTime + gap*36, "J"),
//					new Beat(startTime + gap*37, "D"),
//					new Beat(startTime + gap*38, "F"),
					
					new Beat(startTime + gap*39, "F"),
						new Beat(startTime + gap*39 + gap/2, "D"),
//					------------------------------------ 6
					new Beat(startTime + gap*40, "J"), new Beat(startTime + gap*40, "K"),
					new Beat(startTime + gap*41, "F"),
						new Beat(startTime + gap*41 + gap/2, "J"),
//					new Beat(startTime + gap*42, "F"),
					new Beat(startTime + gap*43, "J"),
						new Beat(startTime + gap*43 + gap/2, "K"),
					new Beat(startTime + gap*44, "D"), new Beat(startTime + gap*44, "F"),
					new Beat(startTime + gap*45, "J"),
						new Beat(startTime + gap*45 + gap/2, "K"),
//					new Beat(startTime + gap*46, "J"),
					new Beat(startTime + gap*47, "F"),
//					------------------------------------ 7
					new Beat(startTime + gap*48, "D"), new Beat(startTime + gap*48, "K"),
					new Beat(startTime + gap*49, "F"),
						new Beat(startTime + gap*49 + gap/2, "D"), new Beat(startTime + gap*49 + gap/2, "K"),
//					new Beat(startTime + gap*50, "F"),
						new Beat(startTime + gap*50 + gap/2, "J"),
					new Beat(startTime + gap*51, "K"), 
//					new Beat(startTime + gap*52, "K"),
//					new Beat(startTime + gap*53, "K"),
//					new Beat(startTime + gap*54, "J"),
					new Beat(startTime + gap*55, "F"),
						new Beat(startTime + gap*55 + gap/2, "J"),
//					------------------------------------ 8
					new Beat(startTime + gap*56, "D"),
					new Beat(startTime + gap*57, "K"),
						new Beat(startTime + gap*57 + gap/2, "K"),
//					new Beat(startTime + gap*58, "K"),
					new Beat(startTime + gap*59, "F"),
					new Beat(startTime + gap*60, "J"),
					new Beat(startTime + gap*61, "D"),
//					new Beat(startTime + gap*62, "J"),
					new Beat(startTime + gap*63, "D"),
						new Beat(startTime + gap*63 + gap/2, "F"),
//					------------------------------------ 9
					new Beat(startTime + gap*64, "J"), new Beat(startTime + gap*64, "K"),
					new Beat(startTime + gap*65, "F"),
						new Beat(startTime + gap*65 + gap/2, "D"),
					new Beat(startTime + gap*66, "F"), 
					new Beat(startTime + gap*67, "D"), new Beat(startTime + gap*67, "K"),
//					new Beat(startTime + gap*68, "D"),
//					new Beat(startTime + gap*69, "K"),
//					new Beat(startTime + gap*70, "J"),
					
					new Beat(startTime + gap*71, "J"),
						new Beat(startTime + gap*71 + gap/2, "K"),
//					------------------------------------ 10
					new Beat(startTime + gap*72, "D"), new Beat(startTime + gap*72, "F"),
					new Beat(startTime + gap*73, "J"),
						new Beat(startTime + gap*73 + gap/2, "F"),
//					new Beat(startTime + gap*74, "F"),
					new Beat(startTime + gap*75, "F"),
						new Beat(startTime + gap*75 + gap/2, "D"),
					new Beat(startTime + gap*76, "J"), new Beat(startTime + gap*76, "K"),
					new Beat(startTime + gap*77, "F"),
						new Beat(startTime + gap*77 + gap/2, "D"),
//					new Beat(startTime + gap*78, "J"),
					new Beat(startTime + gap*79, "J"),
//					------------------------------------ 11
					new Beat(startTime + gap*80, "D"), new Beat(startTime + gap*80, "K"),
					new Beat(startTime + gap*81, "J"),
						new Beat(startTime + gap*81 + gap/2, "D"), new Beat(startTime + gap*81 + gap/2, "K"),
//					new Beat(startTime + gap*82, "F"),
						new Beat(startTime + gap*82 + gap/2, "F"),
					new Beat(startTime + gap*83, "D"), 
//					new Beat(startTime + gap*84, "K"),
//					new Beat(startTime + gap*85, "K"),
//					new Beat(startTime + gap*86, "J"),
					new Beat(startTime + gap*87, "J"),
						new Beat(startTime + gap*87 + gap/2, "F"),
//					------------------------------------ 12
					new Beat(startTime + gap*88, "K"),
					new Beat(startTime + gap*89, "D"),
						new Beat(startTime + gap*89 + gap/2, "D"),
//					new Beat(startTime + gap*90, "K"),
					new Beat(startTime + gap*91, "J"),
					new Beat(startTime + gap*92, "F"),
					new Beat(startTime + gap*93, "K"),
//					new Beat(startTime + gap*94, "J"),
					new Beat(startTime + gap*95, "K"),
						new Beat(startTime + gap*95 + gap/2, "J"),
//					------------------------------------ 13
					new Beat(startTime + gap*96, "D"), new Beat(startTime + gap*96, "F"),
					new Beat(startTime + gap*97, "J"),
						new Beat(startTime + gap*97 + gap/2, "K"),
					new Beat(startTime + gap*98, "J"), 
					new Beat(startTime + gap*99, "D"), new Beat(startTime + gap*99, "K"),
//					new Beat(startTime + gap*100, "D"),
//					new Beat(startTime + gap*101, "K"),
//					new Beat(startTime + gap*102, "J"),
//					new Beat(startTime + gap*103, "J"),
					
//					------------------------------------ 14
//					new Beat(startTime + gap*104, "K"),
//					new Beat(startTime + gap*105, "J"),
//					new Beat(startTime + gap*106, "K"),
//					new Beat(startTime + gap*107, "J"),
//					new Beat(startTime + gap*108, "K"),
//					new Beat(startTime + gap*109, "J"),
//					new Beat(startTime + gap*110, "K"),
//					new Beat(startTime + gap*111, "J"),
						
			};
		}
		else if(titleName.equals("Homura") && difficulty.equals("Hard")) {
			int startTime = 2000;
			int gap = 395;
			beats = new Beat[] {
					//------------------------------------ 1
					new Beat(startTime + gap*0, "Space"),
					new Beat(startTime + gap*1, "D"),
					new Beat(startTime + gap*2, "F"),
					new Beat(startTime + gap*3, "S"),
					new Beat(startTime + gap*4, "Space"),
					new Beat(startTime + gap*5, "D"),
					new Beat(startTime + gap*6, "F"),
					new Beat(startTime + gap*7, "S"),
					//------------------------------------ 2
					new Beat(startTime + gap*8, "Space"),
					new Beat(startTime + gap*9, "K"),
					new Beat(startTime + gap*10, "J"),
					new Beat(startTime + gap*11, "S"), new Beat(startTime + gap*11, "L"),
//					new Beat(startTime + gap*12, "Space"),
//					new Beat(startTime + gap*13, "Space"),
//					new Beat(startTime + gap*14, "Space"),
//					new Beat(startTime + gap*15, "Space"),
					//------------------------------------ 3
					new Beat(startTime + gap*16, "Space"),
					new Beat(startTime + gap*17, "K"),
					new Beat(startTime + gap*18, "J"),
					new Beat(startTime + gap*19, "L"),
					new Beat(startTime + gap*20, "Space"),
					new Beat(startTime + gap*21, "K"),
					new Beat(startTime + gap*22, "J"),
					new Beat(startTime + gap*23, "L"),
					//------------------------------------ 4
					new Beat(startTime + gap*24, "Space"),
					new Beat(startTime + gap*25, "D"),
					new Beat(startTime + gap*26, "F"),
					new Beat(startTime + gap*27, "S"), new Beat(startTime + gap*27, "L"),
//					new Beat(startTime + gap*28, "Space"),
//					new Beat(startTime + gap*29, "Space"),
//					new Beat(startTime + gap*30, "Space"),
//					new Beat(startTime + gap*31, "Space"),
					//------------------------------------ 5
					new Beat(startTime + gap*32, "S"),
					new Beat(startTime + gap*33, "L"),
//					new Beat(startTime + gap*34, "Space"),
//					new Beat(startTime + gap*35, "Space"),
//					new Beat(startTime + gap*36, "Space"),
//					new Beat(startTime + gap*37, "Space"),
					new Beat(startTime + gap*38, "K"),
					new Beat(startTime + gap*39, "D"),
					//------------------------------------ 6
					new Beat(startTime + gap*40, "K"),
					new Beat(startTime + gap*41, "Space"),
//					new Beat(startTime + gap*42, "K"),
					new Beat(startTime + gap*43, "K"),
					new Beat(startTime + gap*44, "D"),
					new Beat(startTime + gap*45, "Space"),
//					new Beat(startTime + gap*46, "Space"),
					new Beat(startTime + gap*47, "Space"),
					//------------------------------------ 7
					new Beat(startTime + gap*48, "D"), new Beat(startTime + gap*48, "K"),
					new Beat(startTime + gap*49, "J"),
						new Beat(startTime + gap*49 + gap/2, "K"),
//					new Beat(startTime + gap*50, "Space"),
						new Beat(startTime + gap*50 + gap/2, "F"),
					new Beat(startTime + gap*51, "D"), new Beat(startTime + gap*51, "K"),
//					new Beat(startTime + gap*52, "Space"),
//					new Beat(startTime + gap*53, "Space"),
//					new Beat(startTime + gap*54, "Space"),
					new Beat(startTime + gap*55, "J"),
						new Beat(startTime + gap*55 + gap/2, "K"),
					//------------------------------------ 8
					new Beat(startTime + gap*56, "Space"),
//					new Beat(startTime + gap*57, "Space"),
//					new Beat(startTime + gap*58, "Space"),
					new Beat(startTime + gap*59, "D"),
					new Beat(startTime + gap*60, "J"), new Beat(startTime + gap*60, "K"),
					new Beat(startTime + gap*61, "F"),
//					new Beat(startTime + gap*62, "Space"),
					new Beat(startTime + gap*63, "S"), new Beat(startTime + gap*63, "L"),
					//------------------------------------ 9
					new Beat(startTime + gap*64, "Space"), new Beat(startTime + gap*64, "K"),
					new Beat(startTime + gap*65, "D"),
						new Beat(startTime + gap*65 + gap/2, "J"),
//					new Beat(startTime + gap*66, "Space"),
						new Beat(startTime + gap*66 + gap/2, "F"),
					new Beat(startTime + gap*67, "J"),
//					new Beat(startTime + gap*68, "Space"),
//					new Beat(startTime + gap*69, "Space"),
//					new Beat(startTime + gap*70, "Space"),
					new Beat(startTime + gap*71, "K"),
					//------------------------------------ 10
					new Beat(startTime + gap*72, "D"),
					new Beat(startTime + gap*73, "Space"),
//					new Beat(startTime + gap*74, "Space"),
					new Beat(startTime + gap*75, "D"),
					new Beat(startTime + gap*76, "K"),
					new Beat(startTime + gap*77, "Space"),
//					new Beat(startTime + gap*78, "Space"),
					new Beat(startTime + gap*79, "Space"),
					//------------------------------------ 11
					new Beat(startTime + gap*80, "D"), new Beat(startTime + gap*80, "K"),
					new Beat(startTime + gap*81, "J"),
						new Beat(startTime + gap*81 + gap/2, "K"),
//					new Beat(startTime + gap*82, "Space"),
						new Beat(startTime + gap*82 + gap/2, "F"),
					new Beat(startTime + gap*83, "D"), new Beat(startTime + gap*83, "K"),
//					new Beat(startTime + gap*84, "Space"),
//					new Beat(startTime + gap*85, "Space"),
//					new Beat(startTime + gap*86, "Space"),
					new Beat(startTime + gap*87, "F"),
						new Beat(startTime + gap*87 + gap/2, "D"),
					//------------------------------------ 12
					new Beat(startTime + gap*88, "Space"),
//					new Beat(startTime + gap*89, "Space"),
//					new Beat(startTime + gap*90, "Space"),
					new Beat(startTime + gap*91, "K"),
					new Beat(startTime + gap*92, "D"), new Beat(startTime + gap*92, "F"),
					new Beat(startTime + gap*93, "J"),
//					new Beat(startTime + gap*94, "Space"),
					new Beat(startTime + gap*95, "S"), new Beat(startTime + gap*95, "L"),
					//------------------------------------ 13
					new Beat(startTime + gap*96, "D"), new Beat(startTime + gap*96, "Space"),
					new Beat(startTime + gap*97, "K"),
						new Beat(startTime + gap*97 + gap/2, "F"),
//					new Beat(startTime + gap*98, "Space"),
						new Beat(startTime + gap*98 + gap/2, "J"),
					new Beat(startTime + gap*99, "D"), new Beat(startTime + gap*99, "K"),
//					new Beat(startTime + gap*100, "Space"),
//					new Beat(startTime + gap*101, "Space"),
//					new Beat(startTime + gap*102, "Space"),
//					new Beat(startTime + gap*103, "Space"),
					//------------------------------------ 14
//					new Beat(startTime + gap*104, "Space"),
//					new Beat(startTime + gap*105, "Space"),
//					new Beat(startTime + gap*106, "Space"),
//					new Beat(startTime + gap*107, "Space"),
//					new Beat(startTime + gap*108, "Space"),
//					new Beat(startTime + gap*109, "Space"),
//					new Beat(startTime + gap*110, "Space"),
//					new Beat(startTime + gap*111, "Space"),
			};
		}
		else if(titleName.equals("Gurenka") && difficulty.equals("Easy")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("Gurenka") && difficulty.equals("Hard")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};
		}
		else if(titleName.equals("Catch the Moment") && difficulty.equals("Easy")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "k"),
			};
		}
		else if(titleName.equals("Catch the Moment") && difficulty.equals("Hard")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "L"),
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName(), difficulty);
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					return;
				}
			}
		}
	}
	
	public void judge(String input) {
		for(int i=0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlare.png")).getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeMiss.png")).getImage();
			comboList.add(combo);
			combo = 0;
			miss++;
		} else if(judge.equals("Good")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGood.png")).getImage();
			score += 5;
			combo++;
			good++;
		} else if(judge.equals("Great")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgeGreat.png")).getImage();
			score += 10;
			combo++;
			great++;
		} else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon(Main.class.getResource("../images/judgePerfect.png")).getImage();
			score += 12;
			combo++;
			perfect++;
		}
	}
	
	public int maxCombo(ArrayList<Integer> combo) {
		int maxCombo = 0;
		
		for(int i=0; i<combo.size(); i++) {
			if(maxCombo < combo.get(i)) {
				maxCombo = combo.get(i);
			}
		}
		
		return maxCombo;
	}
}

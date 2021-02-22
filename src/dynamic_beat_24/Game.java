package dynamic_beat_24;

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
	
	private Image noteRouteLineImage = new ImageIcon("images/noteRouteLine.png").getImage();
	private Image judgementLineImage = new ImageIcon("images/judgementLine.png").getImage();
	private Image gameInfoImage = new ImageIcon("images/gameInfo.png").getImage();
	private Image noteRouteSImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteDImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteFImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteSpace1Image = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteSpace2Image = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteJImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteKImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image noteRouteLImage = new ImageIcon("images/noteRoute.png").getImage();
	private Image blueFlareImage;
	private Image judgeImage;
	private Image keyPadSImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadDImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadFImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadSpace1Image = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadSpace2Image = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadJImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadKImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image keyPadLImage = new ImageIcon("images/keyPadBasic.png").getImage();
	private Image resultBackground = new ImageIcon("images/resultBackground.png").getImage();
	private Image fullComboImage = new ImageIcon("images/fullCombo.png").getImage();
	
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
//				g.drawImage(judgementLineImage, 0, 580, null);
				for(int i=0; i<noteList.size(); i++) {
					Note note = noteList.get(i);
					if(note.getY() > 620) {
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
				g.drawString("D", 478, 649);
				g.drawString("F", 582, 649);
				g.drawString("J", 686, 649);
				g.drawString("K", 790, 649);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.drawString(String.format("%06d", score), 565, 702);
				g.setColor(Color.white);
				g.setFont(new Font("SANS_SERIF", Font.BOLD, 62));
				g.drawString(String.valueOf(combo), 960, 340);
				g.drawImage(blueFlareImage, 500, 405, null);
				g.drawImage(judgeImage, 460, 420, null);
				g.drawImage(keyPadDImage, 436, 620, null);
				g.drawImage(keyPadFImage, 540, 620, null);
				g.drawImage(keyPadJImage, 644, 620, null);
				g.drawImage(keyPadKImage, 748, 620, null);
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
//				g.drawImage(judgementLineImage, 0, 580, null);
				for(int i=0; i<noteList.size(); i++) {
					Note note = noteList.get(i);
					if(note.getY() > 620) {
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
				g.drawString("S", 270, 649);
				g.drawString("D", 374, 649);
				g.drawString("F", 478, 649);
				g.drawString("Space Bar", 580, 649);
				g.drawString("J", 784, 649);
				g.drawString("K", 889, 649);
				g.drawString("L", 993, 649);
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Elephant", Font.BOLD, 30));
				g.drawString(String.format("%06d", score), 565, 702);
				g.setColor(Color.white);
				g.setFont(new Font("SANS_SERIF", Font.BOLD, 62));
				g.drawString(String.valueOf(combo), 1100, 340);
				g.drawImage(blueFlareImage, 500, 405, null);
//				g.drawImage(judgeImage, 460, 420, null);
				g.drawImage(keyPadSImage, 228, 620, null);
				g.drawImage(keyPadDImage, 332, 620, null);
				g.drawImage(keyPadFImage, 436, 620, null);
				g.drawImage(keyPadSpace1Image, 540, 620, null);
				g.drawImage(keyPadSpace2Image, 640, 620, null);
				g.drawImage(keyPadJImage, 744, 620, null);
				g.drawImage(keyPadKImage, 848, 620, null);
				g.drawImage(keyPadLImage, 952, 620, null);
			}
		} else if(DynamicBeat.isGameResult) {
			comboList.add(combo);
			if(score > DynamicBeat.userScore && !DynamicBeat.userID.equals("GEST")) {
				DynamicBeat.userScore = score;
				DynamicBeat.DB.setUserScore(DynamicBeat.userID, DynamicBeat.userScore);
			} else if (score > DynamicBeat.userScore && DynamicBeat.userID.equals("GEST"))
				DynamicBeat.userScore = score;
			
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
			
			if(miss == 0) {
				g.drawImage(fullComboImage, 708, 478, null);
			}
		}
	}
	
	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadSImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseS() {
		noteRouteSImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadSImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadDImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadDImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadFImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadFImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressSpace() {
		judge("Space");
		noteRouteSpace1Image = new ImageIcon("images/noteRoutePressed.png").getImage();
		noteRouteSpace2Image = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadSpace1Image = new ImageIcon("images/keyPadPressed.png").getImage();
		keyPadSpace2Image = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon("images/noteRoute.png").getImage();
		noteRouteSpace2Image = new ImageIcon("images/noteRoute.png").getImage();
		keyPadSpace1Image = new ImageIcon("images/keyPadBasic.png").getImage();
		keyPadSpace2Image = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadJImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadJImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadKImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadKImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon("images/noteRoutePressed.png").getImage();
		keyPadLImage = new ImageIcon("images/keyPadPressed.png").getImage();
	}
	public void releaseL() {
		noteRouteLImage = new ImageIcon("images/noteRoute.png").getImage();
		keyPadLImage = new ImageIcon("images/keyPadBasic.png").getImage();
	}
	
	@Override
	public void run() {
		dropNotes(this.titleName);
	}
	
	public void close() {
		gameMusic.close();
		resultMusic.close();
		DynamicBeat.isGameResult = false;
		this.interrupt();
	}
	
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		
		if(titleName.equals("Homura") && difficulty.equals("Easy")) {
			int startTime = 2060;
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
					new Beat(startTime + gap*100, "D"), new Beat(startTime + gap*100, "K"),
					new Beat(startTime + gap*101, "J"),
					new Beat(startTime + gap*102, "K"), 
						new Beat(startTime + gap*102 + gap/2, "K"), 
					new Beat(startTime + gap*103, "J"),
					
//					------------------------------------ 14
					new Beat(startTime + gap*104, "F"), new Beat(startTime + gap*104, "J"),
					new Beat(startTime + gap*105, "J"),
					new Beat(startTime + gap*106, "D"),
					new Beat(startTime + gap*107, "F"),
					new Beat(startTime + gap*108, "F"), new Beat(startTime + gap*108, "J"),
					new Beat(startTime + gap*109, "F"), new Beat(startTime + gap*109, "J"),
					new Beat(startTime + gap*110, "D"),
					new Beat(startTime + gap*111, "F"),
//					------------------------------------ 15
					new Beat(startTime + gap*112, "F"), new Beat(startTime + gap*112, "J"),
					new Beat(startTime + gap*113, "J"),
						new Beat(startTime + gap*113 + gap/2, "F"),
					new Beat(startTime + gap*114, "K"),
					new Beat(startTime + gap*115, "F"),
					new Beat(startTime + gap*116, "D"), new Beat(startTime + gap*116, "K"),
					new Beat(startTime + gap*117, "J"),
					new Beat(startTime + gap*118, "D"),
					new Beat(startTime + gap*119, "F"),
//					------------------------------------ 16
					new Beat(startTime + gap*120, "F"), new Beat(startTime + gap*104, "J"),
					new Beat(startTime + gap*121, "F"),
					new Beat(startTime + gap*122, "K"),
					new Beat(startTime + gap*123, "J"),
					new Beat(startTime + gap*124, "F"), new Beat(startTime + gap*124, "J"),
					new Beat(startTime + gap*125, "F"), new Beat(startTime + gap*125, "J"),
					new Beat(startTime + gap*126, "K"),
					new Beat(startTime + gap*127, "J"),
//					------------------------------------ 17
					new Beat(startTime + gap*128, "F"), new Beat(startTime + gap*128, "J"),
					new Beat(startTime + gap*129, "F"),
						new Beat(startTime + gap*129 + gap/2, "J"),
					new Beat(startTime + gap*130, "D"),
					new Beat(startTime + gap*131, "J"),
					new Beat(startTime + gap*132, "D"), new Beat(startTime + gap*132, "K"),
					new Beat(startTime + gap*133, "F"),
					new Beat(startTime + gap*134, "K"),
					new Beat(startTime + gap*135, "J"),
						new Beat(startTime + gap*135 + gap/2, "K"),
//					------------------------------------ 18
					new Beat(startTime + gap*136, "D"), new Beat(startTime + gap*136, "F"),
//					new Beat(startTime + gap*137, "J"),
						new Beat(startTime + gap*137 + gap/2, "J"),
					new Beat(startTime + gap*138, "D"), new Beat(startTime + gap*138, "F"),
//					new Beat(startTime + gap*139, "J"),
						new Beat(startTime + gap*139 + gap/2, "J"),
					new Beat(startTime + gap*140, "D"), new Beat(startTime + gap*140, "F"),
					new Beat(startTime + gap*141, "D"),
					new Beat(startTime + gap*142, "D"),
					new Beat(startTime + gap*143, "F"),
						new Beat(startTime + gap*143 + gap/2, "D"),
//					------------------------------------ 19
					new Beat(startTime + gap*144, "J"), new Beat(startTime + gap*144, "K"),
//					new Beat(startTime + gap*145, "J"),
						new Beat(startTime + gap*145 + gap/2, "F"),
					new Beat(startTime + gap*146, "J"), new Beat(startTime + gap*146, "K"),
//					new Beat(startTime + gap*147, "J"),
						new Beat(startTime + gap*147 + gap/2, "F"),
					new Beat(startTime + gap*148, "J"), new Beat(startTime + gap*148, "K"),
					new Beat(startTime + gap*149, "K"),
					new Beat(startTime + gap*150, "K"),
					new Beat(startTime + gap*151, "J"),
						new Beat(startTime + gap*151 + gap/2, "K"),
//					------------------------------------ 20
					new Beat(startTime + gap*152, "F"), new Beat(startTime + gap*152, "J"),
					new Beat(startTime + gap*153, "J"),
						new Beat(startTime + gap*153 + gap/2, "F"),
					new Beat(startTime + gap*154, "K"),
					new Beat(startTime + gap*155, "F"),
					new Beat(startTime + gap*156, "F"), new Beat(startTime + gap*156, "J"),
					new Beat(startTime + gap*157, "F"),
					new Beat(startTime + gap*158, "K"),
					new Beat(startTime + gap*159, "D"), new Beat(startTime + gap*159, "K"),
//					------------------------------------ 21
//					new Beat(startTime + gap*160, "J"),
					new Beat(startTime + gap*161, "D"), new Beat(startTime + gap*161, "K"),
					new Beat(startTime + gap*162, "F"),
					new Beat(startTime + gap*163, "D"),
					new Beat(startTime + gap*164, "D"), new Beat(startTime + gap*164, "K"),
					new Beat(startTime + gap*165, "D"),
						new Beat(startTime + gap*165 + gap/2, "F"),
					new Beat(startTime + gap*166, "K"),
						new Beat(startTime + gap*166 + gap/2, "J"),
					new Beat(startTime + gap*167, "F"),
					
//					------------------------------------ 22
					new Beat(startTime + gap*168, "F"), new Beat(startTime + gap*168, "J"),
					new Beat(startTime + gap*169, "K"),
						new Beat(startTime + gap*169 + gap/2, "K"),
					new Beat(startTime + gap*170, "D"),
					new Beat(startTime + gap*171, "F"),
//					new Beat(startTime + gap*172, "J"),
						new Beat(startTime + gap*172 + gap/2, "F"),
					new Beat(startTime + gap*173, "F"),
					new Beat(startTime + gap*174, "D"),
					new Beat(startTime + gap*175, "F"),
//					------------------------------------ 23
					new Beat(startTime + gap*176, "F"), new Beat(startTime + gap*176, "J"),
					new Beat(startTime + gap*177, "D"),
						new Beat(startTime + gap*177 + gap/2, "D"),
					new Beat(startTime + gap*178, "K"),
					new Beat(startTime + gap*179, "J"),
//					new Beat(startTime + gap*180, "J"),
						new Beat(startTime + gap*180 + gap/2, "J"),
					new Beat(startTime + gap*181, "J"),
					new Beat(startTime + gap*182, "K"),
					new Beat(startTime + gap*183, "J"),
						new Beat(startTime + gap*183 + gap/2, "J"),
//					------------------------------------ 24 //
					new Beat(startTime + gap*184, "F"), new Beat(startTime + gap*184, "J"),
					new Beat(startTime + gap*185, "J"),
						new Beat(startTime + gap*185 + gap/2, "F"),
					new Beat(startTime + gap*186, "K"),
					new Beat(startTime + gap*187, "F"),
					new Beat(startTime + gap*188, "F"), new Beat(startTime + gap*188, "J"),
					new Beat(startTime + gap*189, "F"),
					new Beat(startTime + gap*190, "K"),
					new Beat(startTime + gap*191, "D"), new Beat(startTime + gap*191, "K"),
//						------------------------------------ 25
//					new Beat(startTime + gap*192, "J"),
					new Beat(startTime + gap*193, "D"), new Beat(startTime + gap*193, "K"),
					new Beat(startTime + gap*194, "F"),
					new Beat(startTime + gap*195, "D"),
					new Beat(startTime + gap*196, "F"),
					new Beat(startTime + gap*197, "J"),
					new Beat(startTime + gap*198, "D"),
						new Beat(startTime + gap*198 + gap/2, "F"), new Beat(startTime + gap*198 + gap/2, "J"),
					new Beat(startTime + gap*199, "K"),
						new Beat(startTime + gap*199 + gap/2, "F"), new Beat(startTime + gap*199 + gap/2, "J"),
//					------------------------------------ 26
						
					new Beat(startTime + gap*200, "F"), new Beat(startTime + gap*200, "J"),
					new Beat(startTime + gap*201, "D"),
						new Beat(startTime + gap*201 + gap/2, "D"),
					new Beat(startTime + gap*202, "K"),
					new Beat(startTime + gap*203, "J"),
//					new Beat(startTime + gap*204, "J"),
						new Beat(startTime + gap*204 + gap/2, "J"),
					new Beat(startTime + gap*205, "J"),
					new Beat(startTime + gap*206, "K"),
					new Beat(startTime + gap*207, "J"),
//					------------------------------------ 27
					new Beat(startTime + gap*208, "F"), new Beat(startTime + gap*208, "J"),
					new Beat(startTime + gap*209, "K"),
						new Beat(startTime + gap*209 + gap/2, "K"),
					new Beat(startTime + gap*210, "D"),
					new Beat(startTime + gap*211, "F"),
					new Beat(startTime + gap*212, "D"), new Beat(startTime + gap*212, "F"),
					new Beat(startTime + gap*213, "D"), new Beat(startTime + gap*213, "F"),
					new Beat(startTime + gap*214, "J"),
					new Beat(startTime + gap*215, "F"),
//					------------------------------------ 28
					new Beat(startTime + gap*216, "F"), new Beat(startTime + gap*216, "J"),
					new Beat(startTime + gap*217, "D"),
						new Beat(startTime + gap*217 + gap/2, "D"),
					new Beat(startTime + gap*218, "K"),
					new Beat(startTime + gap*219, "J"),
					new Beat(startTime + gap*220, "J"), new Beat(startTime + gap*220, "K"),
					new Beat(startTime + gap*221, "J"), new Beat(startTime + gap*221, "K"),
					new Beat(startTime + gap*222, "F"),
					new Beat(startTime + gap*223, "D"), new Beat(startTime + gap*223, "K"),
//					------------------------------------ 29
//					new Beat(startTime + gap*224, "J"),
					new Beat(startTime + gap*225, "D"), new Beat(startTime + gap*225, "K"),
					new Beat(startTime + gap*226, "J"),
					new Beat(startTime + gap*227, "K"),
					new Beat(startTime + gap*228, "J"),
					new Beat(startTime + gap*229, "F"),
						new Beat(startTime + gap*229 + gap/2, "J"),
					new Beat(startTime + gap*230, "D"),
						new Beat(startTime + gap*230 + gap/2, "K"),
					new Beat(startTime + gap*231, "F"),
						new Beat(startTime + gap*231 + gap/2, "J"),
//					------------------------------------ 30
					new Beat(startTime + gap*232, "D"), new Beat(startTime + gap*232, "K"),
					new Beat(startTime + gap*233, "F"),
					new Beat(startTime + gap*234, "J"), new Beat(startTime + gap*234, "K"),
					new Beat(startTime + gap*235, "F"),
						new Beat(startTime + gap*235 + gap/2, "F"),
					new Beat(startTime + gap*236, "D"),
					new Beat(startTime + gap*237, "F"),
					new Beat(startTime + gap*238, "J"),
//					new Beat(startTime + gap*239, "J"),
//					------------------------------------ 31
					new Beat(startTime + gap*240, "D"), new Beat(startTime + gap*240, "K"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
//					new Beat(startTime + gap*224, "J"),
			};
		}
		else if(titleName.equals("Homura") && difficulty.equals("Hard")) {
			int startTime = 2000;
			int gap = 395;
			beats = new Beat[] {
					new Beat(startTime + gap*0, "Space"),
			};
		}
		else if(titleName.equals("Gurenka") && difficulty.equals("Easy")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "F"),
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
					new Beat(startTime, "F"),
			};
		}
		else if(titleName.equals("Catch the Moment") && difficulty.equals("Hard")) {
			int startTime = 2000;
			beats = new Beat[] {
					new Beat(startTime, "Space"),
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				synchronized(this) {
					Note note = new Note(beats[i].getNoteName(), difficulty);
					note.start();
					noteList.add(note);
					i++;
					dropped = true;
				}
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
	
	public synchronized void judgeEvent(String judge) {
		if(!judge.equals("None")) {
			blueFlareImage = new ImageIcon("images/blueFlare.png").getImage();
		}
		if(judge.equals("Miss")) {
			judgeImage = new ImageIcon("images/judgeMiss.png").getImage();
			comboList.add(combo);
			combo = 0;
			miss++;
		} else if(judge.equals("Good")) {
			judgeImage = new ImageIcon("images/judgeGood.png").getImage();
			score += 5;
			combo++;
			good++;
		} else if(judge.equals("Great")) {
			judgeImage = new ImageIcon("images/judgeGreat.png").getImage();
			score += 10;
			combo++;
			great++;
		} else if(judge.equals("Perfect")) {
			judgeImage = new ImageIcon("images/judgePerfect.png").getImage();
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

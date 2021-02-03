package dynamic_beat_22;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPage extends JFrame {

	private ImageIcon exitButtonEnteredImage = new ImageIcon("images/exitButtonEntered.png");
	private ImageIcon exitButtonBasicImage = new ImageIcon("images/exitButtonBasic.png");
	
	private JLabel label = new JLabel("계정정보를 입력해주세요.");
	private JPanel panel = new JPanel();
	
	private JLabel idLabel = new JLabel("아이디");
	private JLabel pw1Label = new JLabel("비밀번호");
	private JLabel pw2Label = new JLabel("비밀번호 재입력");
	private JLabel nameLabel = new JLabel("이름");
	private JLabel emailLabel = new JLabel("이메일");
	
	private JTextField idTextfield = new JTextField(20);
	private JPasswordField pw1Textfield = new JPasswordField(20);
	private JPasswordField pw2Textfield = new JPasswordField(20);
	private JTextField nameTextfield = new JTextField(20);
	private JTextField emailTextfield = new JTextField(20);

	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton signInButton = new JButton("회원가입");
	
	private String message = "";
	
	public SignUpPage() {
		this.setSize(450, 650);
		this.setTitle("Sign Up Page");
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(null);
		setUndecorated(true);
		
		exitButton.setBounds(410, 10, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				dispose();
			}
		});
		add(exitButton);
		
		label.setBounds(70, 30, 350, 100);
		label.setFont(new Font("Airal", Font.BOLD, 25));
		this.add(label);

		idTextfield.setFont(new Font("Airal", Font.BOLD, 18));
		panel.add(idLabel);
		panel.add(idTextfield);

		pw1Textfield.setFont(new Font("Airal", Font.BOLD, 18));
		pw2Textfield.setFont(new Font("Airal", Font.BOLD, 18));
		panel.add(pw1Label);
		panel.add(pw1Textfield);
		panel.add(pw2Label);
		panel.add(pw2Textfield);
		
		nameTextfield.setFont(new Font("Airal", Font.BOLD, 18));
		panel.add(nameLabel);
		panel.add(nameTextfield);
		
		emailTextfield.setFont(new Font("Airal", Font.BOLD, 18));
		panel.add(emailLabel);
		panel.add(emailTextfield);

		panel.setBounds(50, 120, 350, 400);
		panel.setLayout(new GridLayout(0, 1));
		this.add(panel);
		
		signInButton.setBounds(50, 550, 350, 60);
		signInButton.setFont(new Font("Airal", Font.BOLD, 20));
		signInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
//				signInButton.setIcon(signInButtonEnteredImage);
				signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
//				signInButton.setIcon(signInButtonBasicImage);
				signInButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
				buttonPressedMusic.start();
				if(isfieldEmpty()) {
					System.out.println(message);
				}
				else if(!isPasswordSame()) {
					message = "비밀번호와 비밀번호 확인이 서로 다릅니다.";
					JOptionPane.showMessageDialog(null, message, "회원가입 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(!isEmail()) {
					message = "이메일 형식을 지켜주세요.";
					JOptionPane.showMessageDialog(null, message, "회원가입 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(DynamicBeat.DB.isSameMemberID(idTextfield.getText())) {
					message = "이미 사용된 아이디 입니다.";
					JOptionPane.showMessageDialog(null, message, "회원가입 실패", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(DynamicBeat.DB.setUserInfo(idTextfield.getText(), pw1Textfield.getText(), nameTextfield.getText(), emailTextfield.getText())) {
					message = "회원가입이 완료되었습니다.";
					JOptionPane.showMessageDialog(null, message, "회원가입 성공", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else {
					message = "회원가입에 실패했습니다.";
					JOptionPane.showMessageDialog(null, message, "회원가입 실패", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		this.add(signInButton);
		
		this.setVisible(true);
	}
	
	private boolean isfieldEmpty() {
		if(idTextfield.getText().isEmpty()) {
			message = "아이디를 입력해주세요.";
			return true;
		} else if(pw1Textfield.getText().isEmpty()) {
			message = "비밀번호를 입력해주세요.";
			return true;
		} else if(pw2Textfield.getText().isEmpty()) {
			message = "비밀번호 확인을 입력해주세요.";
			return true;
		} else if(nameTextfield.getText().isEmpty()) {
			message = "이름을 입력해주세요.";
			return true;
		} else if(emailTextfield.getText().isEmpty()) {
			message = "이메일을 입력해주세요.";
			return true;
		}
		
		return false;
	}
	
	private boolean isPasswordSame() {
		if(pw1Textfield.getText().equals(pw2Textfield.getText()))
			return true;
		
		return false;
	}
	
	private boolean isEmail() {
		String fieldEmail = emailTextfield.getText();
		
		if(fieldEmail.charAt(0)=='@') return false;
		if(fieldEmail.charAt(fieldEmail.length()-1)=='@') return false;
		
		int count = 0;
		for(int i=1; i<fieldEmail.length()-1; i++) {
			if(fieldEmail.charAt(i)=='@') count++;
		}
		if(count != 1) return false;
		
		return true;
	}
}

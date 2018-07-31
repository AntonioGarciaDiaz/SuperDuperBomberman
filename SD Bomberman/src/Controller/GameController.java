package Controller;


/* Credit a zetcode.com pour des tutoriels sur la creation de jeux en java.
 * http://zetcode.com/tutorials/javagamestutorial/ */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Board.GameBoard;
import Board.MenuBoard;
import Model.GameModel;
import Model.MoleHole;

public class GameController extends JFrame{
	/**Controleur du jeu.
	 * Recoit les modifications et met a jour la vue et le modele. 
	 * Sert en meme temps de JFrame.
	 */
	
	private MenuBoard menu;
	private GameBoard board;
	
	private GameModel model;
	private ArrayList<Listener> listeners = new ArrayList<Listener>(); //Listeners du jeu
	private ActionListener menuListener; //Listener du menu
	
	private ActionListener repeatThread; //Thread qui s'execute toutes les 7 ms
	private ActionListener backToMenu; //Thread qui relance le menu apres la fin
	private Timer rTime; private Timer mTime; //Temps pour lancer les deux threads précédents.
	
	private int scaleX;
	private int scaleY;
	private int playerNum;
	private Boolean playing;
	
	
	//----------------------------------------------------------
	
	
	public GameController() {
		/**Lors de la premiere partie, les options sont reglees
		 * aux valeurs par defaut : tableau de 15x13 cases avec
		 * deux joueurs.
		 */
		setTitle("Super Duper Bomberman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scaleX = 15; scaleY = 13; playerNum = 2;
		mainMenu();
	}
	
	
	//----------------------------------------------------------
	
	
	public void mainMenu(){
		/**Gere la phase "menu" du controleur, nottament en enlevant les eventuelles
		 * traces de la phase "jeu", et en gerant les clics sur les boutons par moyen
		 * d'un action listener. Pendant cette phase sont etablis les parametres du
		 * prochain jeu, qui serviront a lancer le nouveau modele.
		 */
		if(model!=null){model=null; listeners.clear(); remove(board);}
		menu = new MenuBoard(scaleX,scaleY,playerNum); add(menu);
		pack(); setLocationRelativeTo(null); // Met a la taille du board et au centre de l'ecran.
		
		if(menuListener==null){menuListener = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(e.getActionCommand().equals("StartGame")){startGame();}
				
				if(e.getActionCommand().equals("ScaleXUp") && scaleX<27){
					scaleX+=2; menu.displayScX(scaleX); menu.repaint();
				}if(e.getActionCommand().equals("ScaleXDown") && scaleX>7){
					scaleX-=2; menu.displayScX(scaleX); menu.repaint();
				}
				
				if(e.getActionCommand().equals("ScaleYUp") && scaleY<21){
					scaleY+=2; menu.displayScY(scaleY); menu.repaint();
				}if(e.getActionCommand().equals("ScaleYDown") && scaleY>7){
					scaleY-=2; menu.displayScY(scaleY); menu.repaint();
				}
				
				if(e.getActionCommand().equals("PlayerUp") && playerNum<4){
					playerNum+=1; menu.displayPlayers(playerNum); menu.repaint();
				}if(e.getActionCommand().equals("PlayerDown") && playerNum>1){
					playerNum-=1; menu.displayPlayers(playerNum); menu.repaint();
				}
			}
		};}
		menu.addMenuListener(menuListener);
		this.requestFocusInWindow();
	}
	
	
	//----------------------------------------------------------
	
	
	public void startGame(){
		/**Quitte la phase "menu" et lance la phase "jeu" du controleur.
		 * Charge le modele et la vue du nouveau jeu, ainsi que les listeners de
		 * chaque joueur, puis lance un thread de rafraichissement toutes les 7 ms.
		 */
		if(model==null){
			remove(menu); playing=true;
			model = new GameModel(scaleX,scaleY,playerNum);
			board = new GameBoard(scaleX,scaleY,model);
			for(int i=0; i<model.getPlayers().size(); i++){
				Listener kl = new Listener(model.getPlayers().get(i));
				listeners.add(kl); this.addKeyListener(kl);
			}this.requestFocusInWindow();

			add(board);
			pack();setLocationRelativeTo(null); // Met a la taille du board et au centre de l'ecran.
		
			repeatThread = new ActionListener(){	//Le thread qu'on repete a chaque fois.
				public void actionPerformed(ActionEvent e) {
					rTime.stop(); if(model!=null){stepCycle(); rTime.start();}
				}
			};	rTime = new Timer(7, repeatThread); //On repete le thread toutes les 7 ms.
			rTime.start();
		}
	}
	
	
	//----------------------------------------------------------
	
	
	public void stepCycle(){
		/**Ce cycle se repete a chaque step du jeu (toutes les 7 ms).
		 * On bouge les joueurs, on gere les colissions entre objets et
		 * les objets a enlever, on verifie si le jeu est fini, et on
		 * met a jour la vue.
		 */
		movePlayers();
		model.explosionCheck(); model.holeCheck();
		model.hitPlayersCheck(); model.hitWallsCheck();
		model.powerUpCheck(); model.undergroundCheck();
		model.endGameCheck();
		board.repaint();
		if(model.getOver() && playing){
			playing = false;
			backToMenu = new ActionListener(){	//Le thread qu'on repete a chaque fois.
				public void actionPerformed(ActionEvent e) {mTime.stop(); mainMenu();}
			};	mTime = new Timer(4000, backToMenu); //On repete le thread toutes les 7 ms.
			mTime.start();
		}
	}
	
	
	
	public void movePlayers(){
		/**Prend en charge le mouvement des joueurs (non morts) en verifiant
		 * les imputs recueillis par les listeners, les colissions, et les
		 * carcteristiques du joueur (sous terre, vitesse, etc).
		 */
		for(int i=0; i<listeners.size(); i++){
			Listener kl = listeners.get(i);
			if(!model.getOver() && kl.getPlayer().getDeathPose() == 0){ //Si le joueur n'est pas mort.
				int x = kl.getPlayer().getPosX(); int y = kl.getPlayer().getPosY();
				int sp = kl.getPlayer().getSpeed();
				Boolean ug = kl.getPlayer().isUnderground();
				if(kl.getPressed().equals("R") && !model.collisionCheck(x+sp,y,i+1,ug)){
					kl.getPlayer().setPosX(x+sp);
				}else if(kl.getPressed().equals("L") && !model.collisionCheck(x-sp,y,i+1,ug)){
					kl.getPlayer().setPosX(x-sp);
				}else if(kl.getPressed().equals("U") && !model.collisionCheck(x,y-sp,i+1,ug)){
					kl.getPlayer().setPosY(y-sp);
				}else if(kl.getPressed().equals("D") && !model.collisionCheck(x,y+sp,i+1,ug)){
					kl.getPlayer().setPosY(y+sp);
				}
				if(kl.getAction()){
					kl.setAction(false);
					if(ug && !model.collisionCheck(x,y,i+1)){
						MoleHole newH = new MoleHole(kl.getPlayer().gridPosition(x), kl.getPlayer().gridPosition(y));
						model.getHoles().add(newH);
						kl.getPlayer().outOfGround(newH.getPosX(), newH.getPosY());
					}else if(!ug){kl.getPlayer().placeBomb();}
				}
			}
		}
	}
	
	
	//----------------------------------------------------------
	
	
	public static void main(String[] args) {
		GameController gc = new GameController();
		gc.setVisible(true);
	}
   
}

package Board;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuBoard extends JPanel implements SwingConstants{
	/** Vue du menu.
	 * L'interface graphique du menu, ou le joueur choisit les options.
	 * Sert de JPanel dans le JFrame du controleur.
	 */
	
	private JPanel playerPanel = new JPanel();
	private JPanel scaleXPanel = new JPanel();
	private JPanel scaleYPanel = new JPanel();
	private JPanel startPanel = new JPanel();
	
	private JButton scaleXDownB;
	private JButton scaleXUpB;
	private JLabel scXLabel;
	
	private JButton scaleYDownB;
	private JButton scaleYUpB;
	private JLabel scYLabel;
	
	private JButton playerDownB;
	private JButton playerUpB;
	private JLabel playerLabel;
	
	private JButton startB;

	private Image backgroundSpr;
	private Image wallSpr;
	
	private ImageIcon titleSpr;
	private ImageIcon moreSpr; private ImageIcon lessSpr;
	private ImageIcon startSpr;
	
	
	//----------------------------------------------------------
	
	
	public MenuBoard(int scX, int scY, int pl) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		getAllSprites(); getAllFields(); getAllButtons(scX,scY,pl); 
		setPreferredSize(new Dimension(17*32, 15*32));
	}
	
	
	//----------------------------------------------------------
	
	
	public void getAllFields(){
		/**Ajoute tous les champs au menu.
		 * Chacun de ces champs affiche des boutons, du texte et/ou
		 * des images, selon sa fonction. De haut en bas, ils affichent :
		 * - Le titre du jeu, sous forme d'image.
		 * - Le bouton servant a lancer le jeu.
		 * - Les boutons reglant la largeur du tableau de jeu.
		 * - Les boutons reglant la hauteur du tableau de jeu.
		 * - Les boutons reglant le nombre de joueurs.
		 */
		JPanel title = new JPanel(); title.setOpaque(false);
		title.add(new JLabel(titleSpr, CENTER));
		add(title);
		
		scaleXPanel = new JPanel(); scaleXPanel.setOpaque(false);
		scaleYPanel = new JPanel(); scaleYPanel.setOpaque(false);
		playerPanel = new JPanel(); playerPanel.setOpaque(false);
		startPanel = new JPanel(); startPanel.setOpaque(false);
		
		add(startPanel); add(scaleXPanel); add(scaleYPanel); add(playerPanel);
	}
	
	public void getAllButtons(int scX, int scY, int pl){
		/**Ajoute tous les boutons aux champs du menu, ainsi que
		 * les labels montrant les informations de chaque champ.
		 */
		startB = getButton(startPanel, "StartGame", startSpr);
		
		scaleXDownB = getButton(scaleXPanel, "ScaleXDown", lessSpr);
		scXLabel = new JLabel("Stage width : "+Integer.toString(scX));
		scaleXPanel.add(scXLabel);
		scaleXUpB = getButton(scaleXPanel, "ScaleXUp", moreSpr);
		
		scaleYDownB = getButton(scaleYPanel, "ScaleYDown", lessSpr);
		scYLabel = new JLabel("Stage height : "+Integer.toString(scY));
		scaleYPanel.add(scYLabel);
		scaleYUpB = getButton(scaleYPanel, "ScaleYUp", moreSpr);
		
		playerDownB = getButton(playerPanel, "PlayerDown", lessSpr);
		playerLabel = new JLabel("Number of players : "+Integer.toString(pl));
		playerPanel.add(playerLabel);
		playerUpB = getButton(playerPanel, "PlayerUp", moreSpr);
	}


	public JButton getButton(JPanel p, String Action, ImageIcon spr){
		/**Ajoute un bouton en particulier, en specifiant un champ,
		 * un string d'action que l'ecouteur devra lire, et une image
		 * a afficher (sous forme d'ImageIcon).
		 */
		JButton button = new JButton(spr);
		button.setSize(button.getPreferredSize());
		button.setActionCommand(Action);
		p.add(button);
		return button;
	}
	
	
	//----------------------------------------------------------
	
	
	private void getAllSprites(){
		/**Charge tous les sprites depuis le fichier de resources.
		 * Les sprites sont des images utilisees pour representer
		 * graphiquement chaque element du menu.
		 */
		backgroundSpr = getSprite("ground.png");
		wallSpr = getSprite("solid.png");
		
		titleSpr = new ImageIcon(getClass().getResource("/title.png"));
		moreSpr = new ImageIcon(getClass().getResource("/arrow+.png"));
		lessSpr = new ImageIcon(getClass().getResource("/arrow-.png"));
		startSpr = new ImageIcon(getClass().getResource("/start.png"));
	}
	
	
	private Image getSprite(String file) {
		/**Charge un sprite depuis les resources, en utilisant
    	 * un nom de fichier.
    	 */
		ImageIcon newSprite = new ImageIcon(getClass().getResource("/"+file));
		Image sprite = newSprite.getImage();
		return sprite;
	}	
	
	
	//----------------------------------------------------------
	
	
	public void paintComponent(Graphics g){
		/**Fonction appelee lorsqu'il faut mettre a jour la vue.
		 * Affiche le fond du menu, les autres elements etant
		 * recharges automatiquement.
		 */
		super.paintComponent(g);
		
		// Remplit le board de sprites du sol.
		for(int i=0; i<17; i++){ 	for(int j=0; j<15; j++){
			if(i == 0 || i == 16 || j == 0 || j == 14){
				g.drawImage(wallSpr, i*32, j*32, null);
			}else{
				g.drawImage(backgroundSpr, i*32, j*32, null);
			}
		}}
	}
	
	
	//----------------------------------------------------------
	
	
	public void addMenuListener(ActionListener ml){
		/**Ajoute un ecouteur pour tous les boutons du menu,
		 * cette fonction est appelee depuis le controleur.
    	 */
		scaleXDownB.addActionListener(ml); scaleXUpB.addActionListener(ml);
		scaleYDownB.addActionListener(ml); scaleYUpB.addActionListener(ml);
		playerDownB.addActionListener(ml); playerUpB.addActionListener(ml);
		startB.addActionListener(ml);
	}


	public void displayScX(int scX) {
		/**Met a jour la largeur du tableau a montrer dans le menu.
    	 */
		this.scXLabel.setText("Stage width : "+Integer.toString(scX));
	}


	public void displayScY(int scY) {
		/**Met a jour la hauteur du tableau a montrer dans le menu.
    	 */
		this.scYLabel.setText("Stage height : "+Integer.toString(scY));
	}


	public void displayPlayers(int pl) {
		/**Met a jour le nombre de joueurs a montrer dans le menu.
    	 */
		this.playerLabel.setText("Number of players : "+Integer.toString(pl));
	}
	
}
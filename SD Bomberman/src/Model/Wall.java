package Model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class Wall {
	/**Les murs constituent la plupart de la topographie
	 * du tableau de jeu. Ils peuvent etre cassables ou pas
	 * par des explosions.
	 */

	private int posX; private int posY; //Position en carrés de 32x32
	private Boolean solid; private int breaking;
	private ActionListener removeThread;
	private Timer time;
	
	
	//----------------------------------------------------------
	
	
	public Wall(int pX, int pY, Boolean sol){
		posX = pX; posY = pY; solid = sol; breaking = 0;
	}
	
	
	//----------------------------------------------------------
	
	
	public void breakTimer(){
		/**Lance la "pose d'explosion" du mur.
		 */
		breaking = 1;
		removeThread = new ActionListener(){
			public void actionPerformed(ActionEvent e) {toRemove();}
		};
		time = new Timer(1001, removeThread); time.start();
	}
	
	
	
	public void toRemove(){
		/**Marque le mur pour etre supprime par le modele.
		 */
		time.stop(); breaking = 2;
	}


	//----------------------------------------------------------
	//-------ACCESSEURS ET MUTATEURS (GETTERS ET SETTERS)-------
	//----------------------------------------------------------
	
	
	public int getPosX() {
		return posX;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public int getPosY() {
		return posY;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}


	public Boolean getSolid() {
		return solid;
	}


	public void setSolid(Boolean solid) {
		this.solid = solid;
	}


	public int getBreaking() {
		return breaking;
	}


	public void setBreaking(int breaking) {
		this.breaking = breaking;
	}
	
}

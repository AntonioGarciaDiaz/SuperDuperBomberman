package PowerUp;

import Model.Player;



public class Pow_Clog extends PowerUp{

	public Pow_Clog(int pX, int pY) {
		super(pX, pY);
	}	
		
	@Override 	
	public void affectPlayer(Player pl){
		/**Diminue la vitesse du joueur, si elle est superieure a 1.
		 */
		int sp = pl.getSpeed();
		if(sp > 1){pl.setSpeed(sp-1);}
	}
		
}

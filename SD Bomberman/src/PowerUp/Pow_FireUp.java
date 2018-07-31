package PowerUp;

import Model.Player;



public class Pow_FireUp extends PowerUp{

	public Pow_FireUp(int pX, int pY) {
		super(pX, pY);
	}
	
	@Override
	public void affectPlayer(Player pl){
		/**Augmente la portee des bombes d'une case.
		 */
		pl.setFireUp(pl.getFireUp()+1);
	}
	
}

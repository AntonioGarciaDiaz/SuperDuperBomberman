package PowerUp;

import Model.Player;



public class Pow_FireDown extends PowerUp{
	
	public Pow_FireDown(int pX, int pY) {
		super(pX, pY);
	}	

	
	@Override 	
	public void affectPlayer(Player pl){
		/**Diminue la portee des bombes d'une case, si elle est superieure a 1.
		 */
		int fu = pl.getFireUp();
		if (fu > 1) {pl.setFireUp(fu-1);}
	}
	
}
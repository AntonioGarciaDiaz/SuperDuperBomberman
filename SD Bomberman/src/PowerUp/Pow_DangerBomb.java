package PowerUp;

import Model.Player;



public class Pow_DangerBomb extends PowerUp{

	public Pow_DangerBomb(int pX, int pY) {
		super(pX, pY);
	}
	
	@Override
	public void affectPlayer(Player pl){
		/**Fait que la prochaine bombe posee soit une Bombe Dangereuse.
		 */
		pl.setSpareEffect("D");
	}
	
}

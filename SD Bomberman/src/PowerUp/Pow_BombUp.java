package PowerUp;

import Model.Bomb;
import Model.Player;



public class Pow_BombUp extends PowerUp{

	public Pow_BombUp(int pX, int pY) {
		super(pX, pY);
	}
	
	@Override
	public void affectPlayer(Player pl){
		/**Ajoute une bombe a la liste du joueur.
		 */
		pl.getSpareBombs().add(new Bomb());
	}
	
}

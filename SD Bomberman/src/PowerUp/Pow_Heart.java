package PowerUp;

import Model.Player;


public class Pow_Heart extends PowerUp {

	public Pow_Heart(int pX, int pY) {
		super(pX, pY);
	}
	
	@Override
	public void affectPlayer(Player pl){
		/**Ajoute une vie supplementaire au joueur s'il n'en possede pas une,
		 * ce qui lui donne une chance pour resister à une explosion.
		 */
		if(pl.getLives()==1){pl.setLives(pl.getLives()+1);} //Pas plus de deux vies en meme temps.
	}
	
}

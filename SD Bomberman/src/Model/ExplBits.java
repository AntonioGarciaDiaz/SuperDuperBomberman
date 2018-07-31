package Model;



public class ExplBits {
	/**Les morceaux d'une explosion. Ils constituent la partie active
	 * de l'explosion, qui colisionne avec les murs, les joueurs, et
	 * les powerups. Les murs touches par des morceaux marques "au bout"
	 * de l'explosion ne se cassent pas.
	 */
	
	private int posX; private int posY; //Position en carrés de 32x32
	private String dir; //Direction du bras de l'explosion (R, L, U, D ou C)
	private Boolean onEnd; //Si c'est le boutde l'explosion
	
	
	//----------------------------------------------------------
	
	
	public ExplBits(int pX, int pY, String d){
		posX = pX; posY = pY; dir = d;
		onEnd = false;
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


	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}


	public Boolean getOnEnd() {
		return onEnd;
	}


	public void setOnEnd(Boolean onEnd) {
		this.onEnd = onEnd;
	}
}

package org.tbhizzle;
import org.powerbot.script.wrappers.Tile;


public enum KeySpot {
	
	BANK(new Tile(3185,3434,0)),
	RUINS(new Tile(3128,3407,0)),
	ALTAR(new Tile(2843,4832,0));
	
	
	
	private final Tile loc;
	KeySpot(Tile t){
		this.loc = t;
	}
	
	public Tile getT(){
		return this.loc;
	}
	
}

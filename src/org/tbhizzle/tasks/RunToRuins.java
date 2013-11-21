package org.tbhizzle.tasks;
import org.tbhizzle.*;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;


//ruins position (3128,3407,0)
//portal home (2841,4829,0)
public class RunToRuins extends Task {
	//private Tile ruins = new Tile(3128,3407,0);
	//private LocalPath toRuins;
	
	public RunToRuins(MethodContext ctx) {
		super(ctx);

	}

	public boolean activate() {
		boolean ess = false;
		//back pack has ess
		//nearish to monument
		Item[] items = ctx.backpack.getAllItems();
		for(Item i: items){
			if(i.getId() == 1436){//it is ess
				ess = true;
				break;
			}
		}
		return  ess 
				&& ctx.players.local().getLocation()
				.distanceTo(KeySpot.RUINS.getT()) > 5;
	}

	public boolean execute() {
		AirCrafter.setStatus("Walking to the ruins");
		if(!ctx.movement.isRunning()){
			ctx.movement.setRunning(true);
		}
		if(ctx.bank.isOpen())
			ctx.bank.close();
		return ctx.movement.stepTowards(KeySpot.RUINS.getT());
	}

}

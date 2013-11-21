package org.tbhizzle.tasks.crafting;
import org.tbhizzle.*;
import org.tbhizzle.tasks.Task;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;


public class ExitRuins extends Task {

	public ExitRuins(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		//no ess
		//near altar
		boolean ess = false;
		for(Item item: ctx.backpack.getAllItems()){
			if(item.getId() == 1436)
				ess = true;
		}
		return !ess 
				&& ctx.players.local().getLocation()
				.distanceTo(KeySpot.ALTAR.getT()) < 30;
	}

	@Override
	public boolean execute() {
		AirCrafter.setStatus("Leaving Ruins");
		GameObject portal = null;
		if(portal == null){
			ctx.objects.select().id(2465);
			portal = ctx.objects.nearest().poll();
		}
		ctx.camera.turnTo(portal);
		portal.click();
		return false;
	}

}

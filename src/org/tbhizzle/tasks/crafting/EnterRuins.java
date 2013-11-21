package org.tbhizzle.tasks.crafting;
import org.tbhizzle.*;
import org.tbhizzle.tasks.Task;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

public class EnterRuins extends Task {

	public EnterRuins(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		// has ess
		// close to ruins
		boolean ess = false;
		for (Item item : ctx.backpack.getAllItems()) {
			if (item.getId() == 1436)// if there is ess
				ess = true;
		}
		return ess
				&& ctx.players.local().getLocation()
						.distanceTo(KeySpot.RUINS.getT()) < 6;
	}

	@Override
	public boolean execute() {
		AirCrafter.setStatus("Entering Ruins");
		ctx.objects.select().id(2452);
		GameObject ruin = ctx.objects.nearest().poll(); 
		ctx.camera.turnTo(ruin);
		int count = 0;
		while (ctx.players.local().getLocation()
				.distanceTo(KeySpot.ALTAR.getT()) > 30 
				&& count < 5) {
			ruin.click();
			sleep(600);
			count++;
		}

		return ctx.objects.nearest().poll().click();
	}

}

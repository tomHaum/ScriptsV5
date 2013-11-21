package org.tbhizzle.tasks.crafting;
import org.tbhizzle.*;
import org.tbhizzle.tasks.Task;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.GameObject;
import org.powerbot.script.wrappers.Item;

public class Craft extends Task {
	public Craft(MethodContext ctx) {
		super(ctx);
	}

	@Override
	public boolean activate() {
		boolean ess = false;
		// back pack has ess
		// nearish to monument
		Item[] items = ctx.backpack.getAllItems();
		for (Item i : items) {
			if (i.getId() == 1436) {// it is ess
				ess = true;
				break;
			}
		}
		return ess && ctx.players.local().getLocation()
						.distanceTo(KeySpot.ALTAR.getT()) < 30;
	}

	@Override
	public boolean execute() {
		//System.out.println("Crafting");
		ctx.objects.select().id(2478);//filter altars
		GameObject altar = ctx.objects.nearest().poll();//select closest altar
		ctx.movement.stepTowards(altar);//move to altar
		if(!altar.isOnScreen())
			ctx.camera.turnTo(altar);
		altar.click();//try to craft rune
		return false;
	}
}

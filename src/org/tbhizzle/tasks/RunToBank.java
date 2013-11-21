package org.tbhizzle.tasks;
import org.tbhizzle.*;

import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Tile;

//1435 is rune ess
//556 is air rune
public class RunToBank extends Task {
	
	public RunToBank(MethodContext ctx) {
		super(ctx);
	}

	public boolean activate(Tile l) {
		// if it does not have ess
		// and not in bank

		Item[] items = ctx.backpack.getAllItems();
		for (Item i : items) {
			if (i.getId() == 1436)// 556 is air rune
				return false;
		}
		
		return l.distanceTo(KeySpot.ALTAR.getT()) > 30
				&&
				l.distanceTo(KeySpot.BANK.getT()) >5;
	}

	public boolean execute() {
		AirCrafter.setStatus("Walking to the Bank");
		if(!ctx.movement.isRunning()){
			ctx.movement.setRunning(true);
		}
		return ctx.movement.stepTowards(KeySpot.BANK.getT());
	}
}

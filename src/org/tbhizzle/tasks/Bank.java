package org.tbhizzle.tasks;
import org.tbhizzle.*;

import org.powerbot.script.methods.Bank.Amount;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;
import org.powerbot.script.wrappers.Locatable;
import org.powerbot.script.wrappers.Tile;



public class Bank extends Task {

	public Bank(MethodContext ctx) {
		super(ctx);
	}

	public boolean activate(Tile l) {
		Item[] items = ctx.backpack.getAllItems();
		for (Item i : items) {
			if (i.getId() == 1436)// 1436 is ess
				return false;
		}
		// close to banker and inventory does not have ess
		return l.getLocation().distanceTo(KeySpot.BANK.getT())

		<

		6;
	}

	public boolean execute() {

		AirCrafter.setStatus("Banking");
		
		if (!ctx.bank.isOpen()) {
			
			
			while (!ctx.bank.isOpen()) {
				ctx.camera.setAngle(ctx.camera.getYaw() + 10);
				ctx.bank.open();
				sleep(500, 700);
			}
		}

		while (ctx.backpack.select().id(556).count() > 0){
			ctx.bank.depositInventory();
			//ctx.bank.deposit(556, Amount.ALL);
			sleep(300);
		}
		while (ctx.backpack.select().id(1436).count() < 1) {
			ctx.bank.withdraw(1436, Amount.ALL);
			sleep(300);
		}

		return ctx.bank.close();
	}
}

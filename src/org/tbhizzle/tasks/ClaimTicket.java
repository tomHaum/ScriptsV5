package org.tbhizzle.tasks;

import org.powerbot.script.methods.Hud;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.wrappers.Item;

public class ClaimTicket extends Task {

	public ClaimTicket(MethodContext ctx) {
		super(ctx);
	}

	public boolean activate() {
		//24154
		for(Item i:ctx.backpack.getAllItems()){
			if(i.getId() == 24154){
				return true;
			}
		}
		return false;
	}


	public boolean execute() {
		ctx.backpack.select().id(24154);
		ctx.hud.view(Hud.Window.BACKPACK);
		ctx.backpack.getSelectedItem().interact("Claim");
		
		return false;
	}

}

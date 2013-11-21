package org.tbhizzle.tasks;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.wrappers.Tile;


public abstract class Task extends MethodProvider{
	public Task(MethodContext ctx){
		super(ctx);
	}
	public abstract boolean activate(Tile l);
	public abstract boolean execute();
}

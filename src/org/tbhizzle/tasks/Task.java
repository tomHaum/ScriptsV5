package org.tbhizzle.tasks;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;


public abstract class Task extends MethodProvider{
	public Task(MethodContext ctx){
		super(ctx);
	}
	public abstract boolean activate();
	public abstract boolean execute();
}

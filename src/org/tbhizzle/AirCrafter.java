package org.tbhizzle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.powerbot.event.MessageEvent;
import org.powerbot.event.MessageListener;
import org.powerbot.event.PaintListener;
import org.powerbot.script.Manifest;
import org.powerbot.script.PollingScript;
import org.powerbot.script.wrappers.Tile;

import org.tbhizzle.tasks.*;
import org.tbhizzle.tasks.crafting.*;
@Manifest(name = "Air Rune Crafter", authors = "tbhizzle", description = "crafts air runes")
public class AirCrafter extends PollingScript implements MessageListener,
		PaintListener {
	private List<Task> taskList = new ArrayList<Task>();
	private int trips = 0;
	private long startTime;
	public  static String status = "initialize";

	@Override
	public void start() {
		taskList.add(new Bank(getContext()));
		taskList.add(new Craft(getContext()));
		taskList.add(new RunToBank(getContext()));
		taskList.add(new RunToRuins(getContext()));
		taskList.add(new EnterRuins(getContext()));
		taskList.add(new ExitRuins(getContext()));
		startTime = System.currentTimeMillis();
	}

	@Override
	public int poll() {
		Tile location = ctx.players.local().getLocation();
		for (Task task : taskList) {
			if (task.activate(location)) {
				task.execute();
				return 500;
			}
		}
		return 700;
	}

	@Override
	public void messaged(MessageEvent e) {
		String message = e.getMessage().toLowerCase();
		if (message.contains("you bind the temple")) {
			trips++;
		}
	}

	@Override
	public void repaint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, 200, 60);
		g.setColor(Color.BLACK);
		g.drawString("Trips made: " + trips, 10, 15);
		g.drawString(timeFormatter(System.currentTimeMillis() - startTime), 
				10, 27);
		g.drawString(status,10,45);
	}

	private String timeFormatter(long x) {
		final long hr = TimeUnit.MILLISECONDS.toHours(x);
		final long min = TimeUnit.MILLISECONDS.toMinutes(x
				- TimeUnit.HOURS.toMillis(hr));
		final long sec = TimeUnit.MILLISECONDS.toSeconds(x
				- TimeUnit.HOURS.toMillis(hr) - TimeUnit.MINUTES.toMillis(min));

		return String.format("%02d:%02d:%02d", hr, min, sec);
	}
	public static void setStatus(String s){
		status = s;
	}

}

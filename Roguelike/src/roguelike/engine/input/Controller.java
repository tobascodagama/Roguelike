package roguelike.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.List;

import roguelike.engine.GameDriver;
import roguelike.engine.world.World;

public class Controller implements KeyListener
{
	public static final int KEY_DELAY_MILLIS = 200;

	private World world;
	private List<KeyEvent> keyQueue = new LinkedList<KeyEvent>();

	long lastKeyPress;

	public Controller(World world)
	{
		this.world = world;
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_F11)
		{
			GameDriver.debugging = !GameDriver.debugging;
		}

		else
		{
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastKeyPress > KEY_DELAY_MILLIS)
			{
				lastKeyPress = currentTime;
				keyQueue.add(e);
			}
		}

		e.consume();
	}

	public void keyReleased(KeyEvent e)
	{
		e.consume();
	}

	public void keyTyped(KeyEvent e)
	{
		e.consume();
	}

	public void processKeyEvents()
	{
		while (!keyQueue.isEmpty())
		{
			KeyEvent event = keyQueue.remove(0);
			int keyCode = event.getKeyCode();

			if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
					|| keyCode == KeyEvent.VK_LEFT
					|| keyCode == KeyEvent.VK_RIGHT)
			{
				world.processKeyEvent(event);
			}

			else if (keyCode == KeyEvent.VK_ESCAPE)
			{
				GameDriver.running = false;
			}
		}
	}
}

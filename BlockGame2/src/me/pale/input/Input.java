package me.pale.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import me.pale.main.BlockGame;

public class Input {
	
	private static float x = 0;
	private static float y = 0;
	private static float z = 0;
	
	private static float multiplier = 0.005f;
	
	private static float sinx;
	private static float sinz;

	public static void simpleControl() {
		
		sinx = (float) Math.sin(Math.toRadians(BlockGame.mouseX)) * multiplier;
		sinz = (float) Math.cos(Math.toRadians(BlockGame.mouseX)) * multiplier;
		
		// System.out.println(BlockGame.mouseX + ", " + BlockGame.mouseY);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			
			y -= 0.01f;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			
			y += 0.01f;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			
			// z += 0.01f;
			x += -sinx;
			z += sinz;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			
			x += sinx;
			z += -sinz;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			
			x += sinz;
			z += sinx;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			
			x += -sinz;
			z += -sinx;
			
		}
		
	}
	
	public static float getSinX() {
		
		return sinx;
		
	}
	
	public static float getSinZ() {
		
		return sinz;
		
	}
	
	public static float getX() {
		
		return x;
		
	}
	
	public static float getY() {
		
		return y;
		
	}
	
	public static float getZ() {
		
		return z;
		
	}
	
}

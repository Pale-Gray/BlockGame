package me.pale.gui;

import org.lwjgl.opengl.GL11;

import me.pale.shader.Shader;

public class GUI {
	
	private static Shader guiShader;
	
	private  static void init() {
		
		guiShader = new Shader("guiv.glsl", "guif.glsl");
		
	}
	
	public static void doGUI() {
		
		init();
		
		guiShader.start(guiShader.getProgID());
		
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		GL11.glNormal3f(0, 0, 1);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(1, 0);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(1, 0);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0 , 1);
		
		GL11.glEnd();
		
		guiShader.halt();
		
	}

}

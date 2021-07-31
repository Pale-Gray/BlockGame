package me.pale.postprocess;

import org.lwjgl.opengl.GL11;

public class PostProcessQuad {

	public PostProcessQuad() 
	{
		
	}
	
	public void draw() 
	{
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, -1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, 1, -1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, -1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, -1, -1);
		GL11.glEnd();
	}
	
}

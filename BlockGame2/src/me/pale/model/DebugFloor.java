package me.pale.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import me.pale.texture.Tex;

public class DebugFloor {
	
	private float size = 5.0f;
	
	Texture test;
	
	Tex tex = new Tex();
	
	public DebugFloor() {
	}
	
	public void draw() throws FileNotFoundException, IOException {
		// shader = new Shader();
		// shader.start();
		// test = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/testx16.png"));
		// GL13.glActiveTexture(GL13.GL_TEXTURE0);
		// GL11.glColor3f(0.5f, 0.5f, 0.5f);
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// Top Quad
		GL11.glNormal3f(0, 1, 0);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-size, -2, -size);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-size, -2, size);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(size, -2, size);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(size, -2, size);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(size, -2, -size);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-size, -2, -size);
		
		GL11.glEnd();
		// shader.halt();
		
	}
	
	public void debugDraw() throws IOException {
		
	}

}

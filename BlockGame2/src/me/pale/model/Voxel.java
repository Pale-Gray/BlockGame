package me.pale.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import me.pale.texture.Tex;

public class Voxel {
	
	Texture test;
	
	Tex tex = new Tex();
	
	int size = 1;
	
	public Voxel() {
	}
	
	public void draw() throws FileNotFoundException, IOException {
		// shader = new Shader();
		// shader.start();
		// test = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/testx16.png"));
		// GL13.glActiveTexture(GL13.GL_TEXTURE0);
		// GL11.glColor3f(0.5f, 0.5f, 0.5f);
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// Front Quad
		GL11.glNormal3f(0, 0, 1);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, 1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, -1, 1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, 1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, 1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, 1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, 1);
		
		// Left Quad
		GL11.glNormal3f(-1, 0, 0);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, -1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, -1, -1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-1, -1, 1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-1, -1, 1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(-1, 1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, -1);
		
		// Back Quad
		GL11.glNormal3f(0, 0, -1);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(1, 1, -1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(1, -1, -1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-1, -1, -1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(-1, -1, -1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(-1, 1, -1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(1, 1, -1);
		
		// Right Quad
		GL11.glNormal3f(1, 0, 0);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(1, 1, 1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(1, -1, 1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, -1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, -1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, 1, -1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(1, 1, 1);
		
		// Bottom Quad
		GL11.glNormal3f(0, -1, 0);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, -1, 1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, -1, -1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, -1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, -1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, -1, 1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, -1, 1);
		
		// Top Quad
		GL11.glNormal3f(0, 1, 0);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, -1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, 1, 1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, 1, 1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, 1, 1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, 1, -1);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, -1);
		
		GL11.glEnd();
		// shader.halt();
		
	}
	
	public void debugDraw() throws IOException {
		
	}

}

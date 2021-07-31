package me.pale.model;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

import me.pale.shader.Shader;
import me.pale.texture.Tex;

public class LeafParticle {

	
	Texture test;
	
	Tex tex = new Tex();
	
	Shader shader;
	
	int size = 1;
	
	int ms = 0;
	
	int frame = 0;
	
	public LeafParticle() {
		
		shader = new Shader("vertex.glsl", "fragment.glsl");
		
	}
	
	public void draw() throws FileNotFoundException, IOException {
		
		ms++;
		
		shader.start(shader.getProgID());
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		// Front Quad
		GL11.glNormal3f(0, 0, 1);

		if (ms == 1000 * 0.25f) {
			
			ms = 0;
			frame++;
			System.out.println("frame number: " + frame);
			if (frame == 4) {
				
				frame = 0;
				
			}
		}
		
		if (frame == 0) {
			
			GL11.glTexCoord2f(0.0f, 0.0f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(0.0f, 0.25f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(0.0f, 0.5f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(0.0f, 0.75f);
			
		}
		GL11.glVertex3f(-1, 1, 1);
		if (frame == 0) {
			
			GL11.glTexCoord2f(0.0f, 0.25f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(0.0f, 0.5f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(0.0f, 0.75f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(0.0f, 1.0f);
			
		}
		GL11.glVertex3f(-1, -1, 1);
		if (frame == 0) {
			
			GL11.glTexCoord2f(1.0f, 0.25f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(1.0f, 0.5f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(1.0f, 0.75f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(1.0f, 1.0f);
			
		}
		GL11.glVertex3f(1, -1, 1);
		
		if (frame == 0) {
			
			GL11.glTexCoord2f(1.0f, 0.25f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(1.0f, 0.5f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(1.0f, 0.75f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(1.0f, 1.0f);
			
		}
		GL11.glVertex3f(1, -1, 1);
		if (frame == 0) {
			
			GL11.glTexCoord2f(1.0f, 0.0f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(1.0f, 0.25f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(1.0f, 0.5f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(1.0f, 0.75f);
			
		}
		GL11.glVertex3f(1, 1, 1);
		if (frame == 0) {
			
			GL11.glTexCoord2f(0.0f, 0.0f);
			
		}
		if (frame == 1) {
			
			GL11.glTexCoord2f(0.0f, 0.25f);
			
		}
		if (frame == 2) {
			
			GL11.glTexCoord2f(0.0f, 0.5f);
			
		}
		if (frame == 3) {
			
			GL11.glTexCoord2f(0.0f, 0.75f);
			
		}
		GL11.glVertex3f(-1, 1, 1);
		
		GL11.glEnd();
		shader.halt();
		
	}
	
	public void wash() {
		
		shader.wash();
		
	}
	
	public void debugDraw() throws IOException {
		
	}
	
}

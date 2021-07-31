package me.pale.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.glu.GLU;

import me.pale.gui.GUI;
import me.pale.input.Input;
import me.pale.model.DebugFloor;
import me.pale.model.LeafParticle;
import me.pale.model.Voxel;
import me.pale.postprocess.FBO;
import me.pale.postprocess.PostProcessQuad;
import me.pale.shader.PostProcessShader;
import me.pale.shader.Shader;
import me.pale.texture.Tex;

public class BlockGame {
	
	ArrayList<Voxel> voxels = new ArrayList<Voxel>();
	
	boolean mousegrabbed = true;
	
	boolean isWireFrame = false;
	
	float w = 640;
	float l = 480;
	
	float x = 0;
	float z = 0;
	
	float x1 = 0;
	float y1 = 0;
	float z1 = 0;
	
	float at = 0;
	
	public static int mouseY = 0;
	public static int mouseX = 0;
	
	int amt = 1;
	
	int amt2 = 1;
	
	float fac = 640;
	
	float t = 640;
	
	float u_Time = 0;
	
	float x1t;
	float y1t;
	float z1t;
	
	Tex testtex;
	Tex leaftex;
	Tex floortex;
	
	PostProcessShader postprocess;
	Shader shader;
	
	FBO fbo;
	FBO testfbo;
	
	float rot;
	
	PostProcessQuad ppq = new PostProcessQuad();
	
	Voxel test = new Voxel();
	LeafParticle leaf;
	DebugFloor floor = new DebugFloor();
	
	// FBO fbo;
	
	public void window(String name, int width, int height) throws LWJGLException, FileNotFoundException, IOException {
		
		Display.setDisplayMode(new DisplayMode(width, height));
		Display.create();
		
		leaf = new LeafParticle();
		leaftex = new Tex();
		floortex = new Tex();
		
		fbo = new FBO(FBO.DEPTH_RENDER_BUFFER);
		testfbo = new FBO(FBO.DEPTH_TEXTURE);
		postprocess = new PostProcessShader();
		shader = new Shader("vertex.glsl", "fragment.glsl");
		testtex = new Tex();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glClearColor(0, 0, 0, 0);
		GLU.gluPerspective(90.0f, 1.333f, 0.1f, 650.0f);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		
		// GL11.glLoadIdentity();
		
		// GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		// GL11.glLineWidth(2.0f);
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		// GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "fac"), fac);
		
		// GL11.glTranslated(0.0f, 0.0f, -5.0f);
		
		// fbo.printAllThings();
		// System.out.println(testtex.getTextureID());
		System.out.println(fbo.getColorTexture());
		testtex.loadTexture("testx16");
		leaftex.loadTexture("leaf");
		floortex.loadTexture("iamfloor");

		System.out.println("PROGRAM ID FOR POST PROCESSING: " + postprocess.getProgID());
		System.out.println("PROGRAM ID FOR DEFAULT SHADER: " + shader.getProgID());
		
		GL11.glTranslatef(0.0f, 0.0f, 0.0f);
		
		float f = 0;
		
		Mouse.setGrabbed(true);
		
		while (!Display.isCloseRequested()) {
			
			x1 = (float) Math.toRadians( ( 125 * Math.sin(x1t += 0.001f)) );
			z1 = (float) Math.toRadians( ( 125 * Math.cos(z1t += 0.001f)) );
			y1 = 2;
			
			mouseY += Mouse.getDY();
			mouseX += Mouse.getDX();
			
			// GL11.glRotatef(mouseY, -1.0f, 0.0f, 0.0f);
			// GL11.glRotatef(mouseX, 0.0f, 1.0f, 0.0f);
			
			if (mouseY < -90) {
				
				mouseY = -90;
				
			}
			
			if (mouseY > 90) {
				
				mouseY = 90;
				
			}
			
			if (mouseX > 360 || mouseX < -360) {
				
				mouseX = 0;
				
			}
			
			Input.simpleControl();
			
			if (Keyboard.next()) {
				
				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					
					fbo.wash();
					postprocess.wash();
					shader.wash();
					testtex.wash();
					Display.destroy();
					System.exit(0);
					
				}
				
				if (Keyboard.isKeyDown(Keyboard.KEY_M)) {
					
					if (mousegrabbed == false) {
						
						Mouse.setGrabbed(true);
						mousegrabbed = true;
						
					} else {
						
						Mouse.setGrabbed(false);
						mousegrabbed = false;
						
					}
					
				}
				
			}

			shader.start(shader.getProgID());
			
			GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "x1"), x1);
			GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "y1"), y1);
			GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "z1"), z1);
			
			GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "u_Time"), at += 0.001f);
			
			GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "f"), f += 0.01f);
			
			fbo.bindFBO();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(1.0f, 1.0f, 0.2f, 1.0f);
			
			GL11.glPushMatrix();
				GL11.glRotatef(mouseY, -1.0f, 0.0f, 0.0f);
				GL11.glRotatef(mouseX, 0.0f, 1.0f, 0.0f);
				GL11.glTranslatef(Input.getX(), Input.getY(), Input.getZ());
				GL11.glTranslatef(0.0f, 0.0f, -5.0f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, testtex.getTextureID());
				test.draw();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, floortex.getTextureID());
				floor.draw();
				GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, leaftex.getTextureID());
				GL11.glDisable(GL11.GL_CULL_FACE);
				leaf.draw();
				GL11.glEnable(GL11.GL_CULL_FACE);
				
				GL11.glTranslatef(4.0f, 0.0f, 0.0f);
				GL11.glColor3f(1, 1, 1);
				GL11.glPointSize(10);
				GL11.glBegin(GL11.GL_POINTS);
				GL11.glVertex3f(x1, y1, z1);
				GL11.glEnd();
				
				// GUI.doGUI();
				
			GL11.glPopMatrix();
			fbo.unbindFBO();
			
			// TESTING
			testfbo.bindFBO();
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glClearColor(1.0f, 1.0f, 0.2f, 1.0f);
			
			GL11.glPushMatrix();
				GL11.glRotatef(mouseY, -1.0f, 0.0f, 0.0f);
				GL11.glRotatef(mouseX, 0.0f, 1.0f, 0.0f);
				GL11.glTranslatef(Input.getX(), Input.getY(), Input.getZ());
				GL11.glTranslatef(0.0f, 0.0f, -5.0f);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, testtex.getTextureID());
				test.draw();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, floortex.getTextureID());
				floor.draw();
				GL11.glDisable(GL11.GL_CULL_FACE);
				
				// light();
				
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, leaftex.getTextureID());
				GL11.glTranslatef(-4.0f, 0.0f, 0.0f);
				leaf.draw();
				GL11.glEnable(GL11.GL_CULL_FACE);
				
				// GUI.doGUI();
				
			GL11.glPopMatrix();
			testfbo.unbindFBO();
			shader.halt();
			// RENDER FBO QUAD
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			postProcess();
			// GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			
			// UPDATE DISPLAY
			Display.update();
			
		}
		
		fbo.wash();
		postprocess.wash();
		shader.wash();
		testtex.wash();
		Display.destroy();
		System.exit(0);
		
	}
	
	public void diagnostic() {
		
		//System.out.println(testtex.getTextureID());
		// System.out.println(fbo.getColorTexture());
		
		for(int i = 0; i < 1001; i++) {
			
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// System.out.println("THIS PRINTS EVERY SECOND.");
				// System.out.println(testtex.getWidth());
				System.out.println("## DIAGNOSTIC PER SECOND ## CUBE TEXTURE: " + testtex.getTextureID() + ", FBO TEXTURE: " + testtex.getTextureID() + "PROGRAM ID FOR POST PROCESSING: " + postprocess.getProgID() + ", PROGRAM ID FOR CUBE SHADER: " + shader.getProgID());
				
		}
		
	}

	public static void main(String[] args) throws LWJGLException, FileNotFoundException, IOException {
		
		BlockGame bg = new BlockGame();
		bg.window("Game", 640, 480);
		
	}
	
	public void postProcess() {
		
		postprocess.start(postprocess.getProgID());
		
		GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "u_Time"), u_Time += 0.005f);
		
		if (fac < 0) {
			
			fac = 0;
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			
			// GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "fac"), fac -= 0.25f);
			GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "w"), fac -= 0.3f);
			GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "l"), fac -= 0.3f);
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			
			// GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "fac"), fac += 0.25f);
			GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "w"), fac += 0.3f);
			GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "l"), fac += 0.3f);
			
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			
			fac = 1024;
			// GL20.glUniform1f(GL20.glGetUniformLocation(shader.getProgID(), "fac"), 1024);
			GL20.glUniform1f(GL20.glGetUniformLocation(postprocess.getProgID(), "t"), 640);
			
		}
		
		// GL20.glUniform1i(GL20.glGetUniformLocation(postprocess.getProgID(), "renderTex"), fbo.getColorTexture());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbo.getColorTexture());
		renderQuad();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		postprocess.halt();
		
	}
	
	/* public void renderPP() {
		
		// postprocess.start();
		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbo.getColorTexture());
		
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		postprocess.start();
		
		GL20.glUniform1i(GL20.glGetUniformLocation(postprocess.getProgID(), "renderTex"), fbo.getColorTexture());
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, fbo.getColorTexture());
		
		renderQuad();
		postprocess.halt();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
	} */
	
	public void renderQuad() {
		
		// fbo.bindFBO();
		// GL11.glRotatef(1.0f, 1.0f, 1.0f, 0.0f);
		
		GL11.glBegin(GL11.GL_TRIANGLES);
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(-1, 1);
		
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(-1, -1);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(1, -1);
		
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(1, -1);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(1, 1);
		
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(-1, 1);
		
		
		/* GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(-1, -1);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(1, -1);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(-1, 1);
		
		GL11.glTexCoord2f(-1, 1);
		GL11.glVertex2f(-1, 1);
		GL11.glTexCoord2f(1, -1);
		GL11.glVertex2f(1, -1);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(1, 1); */
		
		/* GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(1, 1, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, 0);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, 0);
		
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1, -1, 0);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1, 1, 0);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-1, -1, 0); */
		
		GL11.glEnd();
		// fbo.unbindFBO();
		
	}
	
}

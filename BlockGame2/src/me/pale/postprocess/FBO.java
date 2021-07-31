package me.pale.postprocess;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class FBO {
	
	public static final int NONE = 0;
	public static final int DEPTH_TEXTURE = 1;
	public static final int DEPTH_RENDER_BUFFER = 2;
	
	public int multiplier = 1;
	
	public int i;
	
	private int frameBuffer;
	private int colorTexture;
	private int depthTexture;
	
	private int colorBuffer;
	private int depthBuffer;
	
	public FBO(int DBT) {
		
		initFBO(DBT);
		
	}
	
	public void wash() {
		
		GL30.glDeleteFramebuffers(frameBuffer);
		GL11.glDeleteTextures(colorTexture);
		GL11.glDeleteTextures(depthTexture);
		GL30.glDeleteRenderbuffers(depthBuffer);
		GL30.glDeleteRenderbuffers(colorBuffer);
		
	}
	
	private int createFBO() {
		
		int fbo = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		
		return fbo;
		
	}
	
	private int createTexAttach() {
		
		int colt = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, colt);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, 640 * multiplier, 480 * multiplier, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, colt, 0);
		// GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		
		i = GL11.glGetError();
		
		if (i != 0) {
			
			System.out.println("ERR MAKING TEXTURE " + i);
			
		}
		
		return colt;
		
	}
	
	public int createDTA() {
		
		int st = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, st);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, 640 * multiplier, 480 * multiplier, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, st, 0);
		
		return st;
		
	}
	
	public void bindFBO() {
		
		// GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		if (GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) != GL30.GL_FRAMEBUFFER_COMPLETE) {
			
			int i = GL11.glGetError();
			System.out.println("ERR: FAILED TO BIND FBO: " + i);
			System.exit(-1);
			
		}
		
	}
	
	public void unbindFBO() {
		
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		// GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		
	}
	
	public void bindandreadFBO() {
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, frameBuffer);
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);
		
	}
	
	public int createDBA() {
		
		int db = GL30.glGenRenderbuffers();
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, db);
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, 640 * multiplier, 480 * multiplier);
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, db);
		
		return db;
		
	}
	
	public void initFBO(int type) {
		
		frameBuffer = createFBO();
		colorTexture = createTexAttach();
		if (type == DEPTH_RENDER_BUFFER) {
			
			depthBuffer = createDBA();
			
		} else if (type == DEPTH_TEXTURE) {
			
			depthTexture = createDTA();
			
		}
		unbindFBO();
		
	}

	public int getFrameBuffer() {
		return frameBuffer;
	}

	public int getColorTexture() {
		return colorTexture;
	}

	public int getDepthTexture() {
		return depthTexture;
	}

	public int getColorBuffer() {
		return colorBuffer;
	}

	public int getDepthBuffer() {
		return depthBuffer;
	}
	
	public void printAllThings() {
		
		System.out.println("FBO ID: " + frameBuffer + ", Color Buffer ID: " + colorBuffer + ", Depth Buffer ID: " + depthBuffer + ", Color Texture ID: " + colorTexture + ", Depth Texture ID: " + depthTexture);
		
	}
	
}

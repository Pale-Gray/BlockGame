package me.pale.postprocess;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;

public class FBOdebug {
	
	private int frameBuffer;
	private int frameTexture;
	private int depthBuffer;
	
	public FBOdebug() {
		
		
		
	}
	
	public void wash() {
		
		GL30.glDeleteFramebuffers(frameBuffer);
		GL11.glDeleteTextures(frameTexture);
		GL30.glDeleteRenderbuffers(depthBuffer);
		GL30.glDeleteFramebuffers(frameBuffer);
		
	}
	
	public void unbindCurrentFBO() {
		
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		
	}
	
	public void bind() {
		
		bindFBO(frameBuffer, 640, 480);
		
	}
	
	private void bindFBO(int frameBuffer, int width, int height) {
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		GL11.glViewport(0, 0, width, height);
		
	}
	
	private int createFBO() {
		
		int frameBuffer = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, frameBuffer);
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		
		return frameBuffer;
		
	}
	
	private int createRBO() {
		
		int rbo = GL30.glGenRenderbuffers();
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, rbo);
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL30.GL_DEPTH24_STENCIL8, 640, 480);
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, 0);
		
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_STENCIL_ATTACHMENT, GL30.GL_RENDERBUFFER, rbo);
		
		return rbo;
		
	}
	
	private int newTexAttach(int width, int height) {
		
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGB, width, height, 0, GL11.GL_RGB, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, texture, 0);
		
		return texture;
		
	}
	
	private int newDepthTexAttach(int width, int height) {
		
		int texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT32, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL32.glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, texture, 0);
		
		return texture;
		
	}
	
	private int newDepthBuffAttach(int width, int height) {
		
		int depthBuffer = GL30.glGenRenderbuffers();
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL11.GL_DEPTH_COMPONENT, width, height);
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER, depthBuffer);
		
		return depthBuffer;
		
	}
	
	public void init() {
		
		frameBuffer = createFBO();
		frameTexture = newTexAttach(640, 480);
		depthBuffer = newDepthBuffAttach(640, 480);
		
		unbindCurrentFBO();
		
	}

	public int getFrameBuffer() {
		return frameBuffer;
	}

	public int getFrameTexture() {
		return frameTexture;
	}

	public int getDepthBuffer() {
		return depthBuffer;
	}

}

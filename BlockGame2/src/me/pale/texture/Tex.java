package me.pale.texture;

import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Tex implements Texture {
	
	int texID;
	Texture texture;
	
	public void loadTexture(String filename) {
		
		try {
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/" + filename + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// texID = texture.getTextureID();
		
		// return texID;
		
	}
	
	public int getTextureID() {
		
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		
		return texture.getTextureID();
		
	}
	
	public void wash() {
		
		GL11.glDeleteTextures(texID);
		
	}

	@Override
	public void bind() {
		texture.bind();
	}

	@Override
	public float getHeight() {
		return texture.getHeight();
	}

	@Override
	public int getImageHeight() {
		return texture.getImageHeight();
	}

	@Override
	public int getImageWidth() {
		return texture.getImageWidth();
	}

	@Override
	public byte[] getTextureData() {
		return texture.getTextureData();
	}

	@Override
	public int getTextureHeight() {
		return texture.getTextureHeight();
	}

	@Override
	public String getTextureRef() {
		return texture.getTextureRef();
	}

	@Override
	public int getTextureWidth() {
		return texture.getTextureWidth();
	}

	@Override
	public float getWidth() {
		return texture.getWidth();
	}

	@Override
	public boolean hasAlpha() {
		return texture.hasAlpha();
	}

	@Override
	public void release() {
		texture.release();
	}

	@Override
	public void setTextureFilter(int arg0) {
		texture.setTextureFilter(arg0);
	}

}

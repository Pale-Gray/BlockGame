package me.pale.shader;

import java.io.File;

import org.lwjgl.opengl.GL20;

public class PostProcessShader extends ShaderProgram {

	private static final String VERTEX_FILE = "res/ppvert.glsl";
	private static final String FRAGMENT_FILE = "res/ppfrag.glsl";
	
	static File vertexShad = new File("res/vertex.txt".toString());
	static File fragShad = new File("res/fragment.txt".toString());
	
	public PostProcessShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
		// loadShader(VERTEX_FILE, GL20.GL_VERTEX_SHADER);
		// loadShader(FRAGMENT_FILE, GL20.GL_FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttribs() {
		
	}

}
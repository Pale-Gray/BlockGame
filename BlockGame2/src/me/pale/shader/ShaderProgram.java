/*
 * # This class was mainly copied for help from this URL
 * # https://lwjglgamedev.gitbooks.io/3d-game-development-with-lwjgl/content/chapter04/chapter4.html
 */

package me.pale.shader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public abstract class ShaderProgram {
	
	protected final int progID;
	
	private int vertexShaderID;
	
	private int fragShaderID;
	
	public ShaderProgram(String vertfile, String fragfile) {
		
		vertexShaderID = loadShader(vertfile, GL20.GL_VERTEX_SHADER);
		fragShaderID = loadShader(fragfile, GL20.GL_FRAGMENT_SHADER);
		
		progID = GL20.glCreateProgram();
		
		GL20.glAttachShader(progID, vertexShaderID);
		GL20.glAttachShader(progID, fragShaderID);
		GL20.glLinkProgram(progID);
		GL20.glValidateProgram(progID);
		bindAttribs();
		
	}

	public void start(int progID) {
		
		GL20.glUseProgram(progID);
		
	}
	
	public void halt() {
		
		GL20.glUseProgram(0);
		
	}
	
	protected abstract void bindAttribs();
	
	public void wash() {
		
		halt();
		GL20.glDetachShader(progID, vertexShaderID);
		GL20.glDetachShader(progID, fragShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragShaderID);
		GL20.glDeleteProgram(progID);
		
	}
	
	protected void bindAttrib(int attrib, String varName) {
		
		GL20.glBindAttribLocation(progID, attrib, varName);
		
	}
	
	public static int loadShader(String file, int type) {
		
		StringBuilder shaderSource = new StringBuilder();
		try{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!=null){
				shaderSource.append(line).append("\n");
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, shaderSource);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.err.println("Could not compile shader!");
			System.exit(-1);
		}
		return shaderID;
		
	}

	public int getProgID() {
		return progID;
	}
	
	public void updateUniforms() {
		
		int uniform1 = GL20.glGetUniformLocation(progID, "u_time");
		
		GL20.glUniform1f(GL20.glGetUniformLocation(getProgID(), "u_time"), 0f);
		
	}
	
}

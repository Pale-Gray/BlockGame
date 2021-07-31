#version 120

varying vec2 v_TexCoord;

varying vec4 v_Color;

varying vec4 v_Normal; 

varying vec4 v_Vertex;

void main(void) {

	v_Vertex = gl_Vertex;
	
	v_TexCoord = gl_MultiTexCoord0.xy;
	
	v_Normal = vec4(gl_Normal, 1.0);
	
	v_Color = gl_Color;

	gl_Position = gl_Vertex;
	
	// gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;

}
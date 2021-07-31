#version 120

varying vec4 vertColor;

varying vec2 v_TexCoord;

varying vec4 v_Color;

uniform float u_Time = 0;

varying vec4 v_Normal; 

varying vec4 v_Vertex;

varying vec4 v_Position;

out vec4 color;

float rand2D(in vec2 co){
    return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453);
}

float rand3D(in vec3 co){
    return fract(sin(dot(co.xyz ,vec3(12.9898,78.233,144.7272))) * 43758.5453);
}

void main(void) {

	v_Vertex = gl_Vertex;
	
	vec4 v_DVertex = vec4(gl_Vertex.x, gl_Vertex.y, gl_Vertex.z, gl_Vertex.w);
	
	v_Position = gl_Position;
	
	v_TexCoord = gl_MultiTexCoord0.xy;
	
	v_Normal = vec4(gl_Normal, 1.0);
	
	v_Color = gl_Color;
	
	float noise = 10.0 * -.10 * fract(sin(dot(v_Vertex.xy ,vec2(12.9898,78.233))) * 43758.5453);
	
	float disp = - 10.0 * noise - 10;
	
	vec3 newpos = v_Vertex.xyz * disp;
	
	gl_Position = gl_ModelViewProjectionMatrix * vec4(v_Vertex.xyz, 1.0);

}
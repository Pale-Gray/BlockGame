#version 120

varying vec4 v_Vertex;

varying vec2 v_TexCoord;

varying vec4 v_Position;

uniform float f = 0.0;

varying vec4 v_Normal;

vec4 n_Normal;

varying vec4 v_Color;

uniform sampler2D u_Texture;

uniform float fac = 16;

in vec4 color;

vec4 texture;

uniform float x1 = 2;
uniform float y1 = 3;
uniform float z1 = 1;

float rand2D(in vec2 co){
    return fract(sin(dot(co.xy ,vec2(12.9898,78.233))) * 43758.5453);
}
float rand3D(in vec3 co){
    return fract(sin(dot(co.xyz ,vec3(12.9898,78.233,144.7272))) * 43758.5453);
}

vec4 directLight(in sampler2D tex, in vec2 coords, in vec3 lightCol) 
{

	float amb = 0.3f;
	
	vec3 normal = normalize(v_Normal.xyz);
	vec3 lightDir = normalize(vec3(x1, y1, z1));
	float diffuse = max(dot(normal, lightDir), 0.0f);
	
	return (texture2D(tex, coords) * (diffuse + amb) * lightCol);

}

void alphaTransparency()
{

	texture = texture2D(u_Texture, v_TexCoord);

	if (texture.rgb == vec3(1.0, 0.0, 1.0))
		discard;

}

void main(void) {

	vec4 fakeshading = vec4(1.0);
	
	float fac = 0.0;

	alphaTransparency();

	n_Normal = normalize(v_Normal);
	
	// gl_FragColor = texture;
	
	// gl_FragColor = texture;
	
	gl_FragColor = directLight(u_Texture, v_TexCoord, vec3(1.0, 1.0, 1.0));
	
}
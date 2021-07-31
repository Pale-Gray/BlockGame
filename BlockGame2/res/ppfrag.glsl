#version 120

uniform sampler2D renderTex;

// layout(location = 0) out vec3 color;

varying vec4 v_Vertex;

uniform float u_Time = 0;

varying vec2 v_TexCoord;

varying vec4 v_Normal;

varying vec4 v_Color;

uniform float w = 640;
uniform float l = 480;

uniform int texture;

const float offset = 1.0 / 300.0;

vec2 pixelize() {

	vec2 t = vec2(w, l);

	vec2 xy = vec2(v_TexCoord.x, v_TexCoord.y);

	vec2 a = floor((xy) * t.x);
	vec2 b = a / t.x;
	
	return b.rg;

}

void wiggle() {

	int fac = 8;

	vec2 texC = v_TexCoord;
	texC.x += sin(texC.y * fac *3.14159 + u_Time)/200;
	gl_FragColor = texture2D(renderTex, texC);

}

void default() {

	vec4 tex = texture2D(renderTex, v_TexCoord);

	gl_FragColor = tex.rgba;
	
}

void pixelation()
{

	vec4 tex = texture2D(renderTex, pixelize());

	gl_FragColor = tex.rgba;

}

void highContrast() 
{

	vec4 tex = texture2D(renderTex, pixelize());

	gl_FragColor = tex.rgba;

	gl_FragColor.rgb = gl_FragColor.rgb - abs(sin(u_Time/5)) + 0.3;

}

void depth()
{

	gl_FragColor = vec4(vec3(gl_FragCoord.z), 1.0);

}


void main(void) {
	
	pixelation();

}
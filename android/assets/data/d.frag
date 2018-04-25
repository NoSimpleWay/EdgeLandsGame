//#ifdef позвол€ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс€ на телефоне(GL_ES) то  
//используетс€ низка€ разр€дность (точность) данных.(highp Ц высока€ точность; mediump Ц средн€€ точность; lowp Ц низка€ точность)
#ifdef GL_ES   
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying vec2 v_texCoords;
// sampler2D это специальный формат данных в  glsl дл€ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float x;
uniform LOWP float y;
uniform LOWP float dst;

uniform LOWP float uTime;
uniform LOWP float zoom;
void main(){

	gl_FragColor.rgb=texture2D(u_texture, v_texCoords)/2;
	gl_FragColor*=v_color;
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a*v_color.a;

	
	zoom=sin((gl_FragCoord.y+uTime/1)/1)+cos((gl_FragCoord.y+uTime/2)/2)+sin((gl_FragCoord.y+uTime/3)/3);
	
	zoom=1-zoom/(zoom+0.5);
	
	gl_FragColor+=v_color*texture2D(u_texture, v_texCoords+vec2(zoom/500, 0))/2;
	gl_FragColor.rgb*=1+zoom/50;

}
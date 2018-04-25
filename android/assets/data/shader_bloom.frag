//#ifdef позвол€ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс€ на телефоне(GL_ES) то  
//используетс€ низка€ разр€дность (точность) данных.(highp Ц высока€ точность; mediump Ц средн€€ точность; lowp Ц низка€ точность)
#ifdef GL_ES   
    #define LOWP lowp
    precision mediump float;
#else
    #define LOWP
#endif

varying LOWP vec4 v_color;
varying LOWP float gray;
varying LOWP float post_gray;

varying vec2 v_texCoords;
// sampler2D это специальный формат данных в  glsl дл€ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float value;
void main(){


	



	//value=1-value;
	
	
	
	
/*		gl_FragColor.rgb=texture2D(u_texture2,v_texCoords*0+
	vec2(
	(texture2D(u_texture, v_texCoords).r)*1,
	(texture2D(u_texture, v_texCoords).g)*1
	)).rgb;
*/	


	gl_FragColor.rgb=texture2D(u_texture2,v_texCoords).rgb*0.75;
	

	
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0040,0.0040)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0040,0.0040)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0040,-0.0040)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0040,-0.0040)).rgb*0.03125;
	
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0060,0)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0060,0)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0,-0.0060)).rgb*0.03125;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0,0.0060)).rgb*0.03125;


	//gl_FragColor.rgb=texture2D(u_texture2,v_texCoords).rgb*0.1875;

	
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a;
	
gl_FragColor*=v_color;
	
/*
	value=(1-value)/2;
	gl_FragColor.r+=(1-gray)*value;
	gl_FragColor.g+=(gray)*value;
	gl_FragColor.b+=(gray)*value;*/
	
	
	

}
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

/*
	gl_FragColor.rgb=texture2D(u_texture2,v_texCoords).rgb*0.875;
	
	
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0015,0.0015)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0015,0.0015)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0015,-0.0015)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0015,-0.0015)).rgb/86;
	
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0021,0)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0021)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0,-0.0021)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0021,0)).rgb/86;
	
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0030,0.0030)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0030,0.0030)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(0.0030,-0.0030)).rgb/86;
	gl_FragColor.rgb+=texture2D(u_texture2,v_texCoords+vec2(-0.0030,-0.0030)).rgb/86;
	*/
	
		
gl_FragColor.r=texture2D(u_texture2,v_texCoords+vec2(0,0.00125)).g/3.0;
	gl_FragColor.g=texture2D(u_texture2,v_texCoords+vec2(-0.00075,-0.00075)).b/3.0;
	gl_FragColor.b=texture2D(u_texture2,v_texCoords+vec2(0.00075,-0.00075)).r/3.0;
	
	gl_FragColor.r+=texture2D(u_texture2,v_texCoords+vec2(0,0.005)).b/3.0;
	gl_FragColor.g+=texture2D(u_texture2,v_texCoords+vec2(-0.003,-0.003)).r/3.0;
	gl_FragColor.b+=texture2D(u_texture2,v_texCoords+vec2(0.003,-0.003)).g/3.0;
	
	gl_FragColor.r+=texture2D(u_texture2,v_texCoords+vec2(0,0.0025)).r/3.0;
	gl_FragColor.g+=texture2D(u_texture2,v_texCoords+vec2(-0.0015,-0.0015)).r/3.0;
	gl_FragColor.b+=texture2D(u_texture2,v_texCoords+vec2(0.0015,-0.0015)).g/3.0;

	
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a;
	
gl_FragColor*=v_color;
	
/*
	value=(1-value)/2;
	gl_FragColor.r+=(1-gray)*value;
	gl_FragColor.g+=(gray)*value;
	gl_FragColor.b+=(gray)*value;*/
	
	
	

}
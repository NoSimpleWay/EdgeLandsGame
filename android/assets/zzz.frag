//#ifdef позвол¤ет коду работать на слабых телефонах, и мощных пк.≈сли шейдер используетс¤ на телефоне(GL_ES) то  
//используетс¤ низка¤ разр¤дность (точность) данных.(highp Ц высока¤ точность; mediump Ц средн¤¤ точность; lowp Ц низка¤ точность)
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
// sampler2D это специальный формат данных в  glsl дл¤ доступа к текстуре
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float value;
void main(){


	


	
    float summ=(texture2D(u_texture2,v_texCoords).r+texture2D(u_texture2,v_texCoords).g+texture2D(u_texture2,v_texCoords).b)/3.0;
    float dist=0.0005;
    
    float summ1=(texture2D(u_texture2,v_texCoords+vec2(-dist,-dist)).r+texture2D(u_texture2,v_texCoords+vec2(-dist,-dist)).g+texture2D(u_texture2,v_texCoords+vec2(-dist,-dist)).b)/3.0;
    float summ2=(texture2D(u_texture2,v_texCoords+vec2(dist,dist)).r+texture2D(u_texture2,v_texCoords+vec2(dist,dist)).g+texture2D(u_texture2,v_texCoords+vec2(dist,dist)).b)/3.0;
    
    gl_FragColor.r = 1.0-(summ1-summ2)*2.0;
    gl_FragColor.g = summ-abs(summ1-summ2)*2.0;
    gl_FragColor.b = summ-abs(summ1-summ2)*2.0;

    
    gl_FragColor.a=texture2D(u_texture2,v_texCoords).a;

	

}
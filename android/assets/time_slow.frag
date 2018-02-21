//#ifdef ��������� ���� �������� �� ������ ���������, � ������ ��.���� ������ ������������ �� ��������(GL_ES) ��  
//������������ ������ ����������� (��������) ������.(highp � ������� ��������; mediump � ������� ��������; lowp � ������ ��������)
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
// sampler2D ��� ����������� ������ ������ �  glsl ��� ������� � ��������
uniform sampler2D u_texture;
uniform sampler2D u_texture2;

uniform LOWP float value;
void main(){


	



	value=1-value;
	
	
	
	
		gl_FragColor.rgb=texture2D(u_texture2,v_texCoords*(1-value)+
	vec2(
	(texture2D(u_texture, v_texCoords).r)*value,
	(texture2D(u_texture, v_texCoords).g)*value
	)).rgb;
	

	
	
	
	
	
	gl_FragColor.a=texture2D(u_texture, v_texCoords).a;
	
	gl_FragColor*=v_color;
	
/*
	value=(1-value)/2;
	gl_FragColor.r+=(1-gray)*value;
	gl_FragColor.g+=(gray)*value;
	gl_FragColor.b+=(gray)*value;*/
	
	
	

}
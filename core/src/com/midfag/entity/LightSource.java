package com.midfag.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.midfag.game.GScreen;
import com.midfag.game.Helper;
import com.midfag.game.Main;

public class LightSource {

	public byte light_size=15;
	public int bound=light_size*2+1;
	public float light_array[][]=new float[bound][bound];
	public Color color=new Color(Color.WHITE);
	
	public float light_power=1;
	
	public float R=1;
	public float G=0.95f;
	public float B=0.85f;
	
	public Color color1=new Color(Color.WHITE);
	public Color color2=new Color(Color.WHITE);
	public Color color3=new Color(Color.WHITE);
	public Color color4=new Color(Color.WHITE);
	
	public int pos_x;
	public int pos_y;
	
	public LightSource()
	{
		//Helper.log("LÃLÃLÃLÃLÃLÃLÃLÃLÃLÃLÃ LIGHTMAP INIT LÃLÃLÃL");
	}
	
	public void update_light()
	{
		for (int i=0; i<bound; i++)
		for (int j=0; j<bound; j++)
		{
			
			if ((GScreen.path[pos_x+j-light_size][pos_y+i-light_size]<800))
			{light_array[j][i]=-1;} else {light_array[j][i]=-2f;}
		}
		
		for (int i=-0; i<=0; i++)
		for (int j=-0; j<=0; j++)
		{light_array[(int)(bound/2f)+j][(int)(bound/2f)+i]=(float) (light_power);}
		
		//{light_array[5][5]=(float) (1f+Math.random());}
		//light_array[(int)(bound/2f)][(int)(bound/2f)]=1f;
		

		
		for (int k=0; k<30; k++)
		for (int i=0; i<bound; i++)
		for (int j=0; j<bound; j++)
		{
			if (light_array[j][i]>=0)
			{

				/*
				if ((i>0)&&(j>0)&&(light_array[j-1][i-1]==-1)) {light_array[j-1][i-1]=light_array[j][i]*0.65f;}
				if ((i>0)&&(j<bound-1)&&(light_array[j+1][i-1]==-1)) {light_array[j+1][i-1]=light_array[j][i]*0.65f;}
				
				if ((i<bound-1)&&(j<bound-1)&&(light_array[j+1][i+1]==-1)) {light_array[j+1][i+1]=light_array[j][i]*0.65f;}
				if ((i<bound-1)&&(j>0)&&(light_array[j-1][i+1]==-1)) {light_array[j-1][i+1]=light_array[j][i]*0.65f;}
				*/
				
				if ((i>0)&&(light_array[j][i-1]==-1)) {light_array[j][i-1]=light_array[j][i]*0.55f;}
				if ((j>0)&&(light_array[j-1][i]==-1)) {light_array[j-1][i]=light_array[j][i]*0.75f;}
				
				if ((i<bound-1)&&(light_array[j][i+1]==-1)) {light_array[j][i+1]=light_array[j][i]*0.55f;}
				if ((j<bound-1)&&(light_array[j+1][i]==-1)) {light_array[j+1][i]=light_array[j][i]*0.75f;}
				
				

			}
		}
		
		for (int i=0; i<bound; i++)
		for (int j=0; j<bound; j++)
		{
			if (light_array[j][i]>1) {light_array[j][i]=1;}
			if (light_array[j][i]<0) {light_array[j][i]=0;}
		}
		

		update_global_light_mask();
	}
	
	public void update_global_light_mask()
	{
		for (int i=0; i<bound; i++)
		for (int j=0; j<bound; j++)
		{
			GScreen.light_mask_R[pos_x+j-light_size][pos_y+i-light_size]+=light_array[j][i]*R;
			GScreen.light_mask_G[pos_x+j-light_size][pos_y+i-light_size]+=light_array[j][i]*G;
			GScreen.light_mask_B[pos_x+j-light_size][pos_y+i-light_size]+=light_array[j][i]*B;
		}
	}
	
	public void draw_light()
	{
		//Helper.log("Draw_light");
		//for (int k=0; k<160; k++)
		for (int i=1; i<bound-1; i++)
		for (int j=1; j<bound-1; j++)
		{
			float mul=light_array[j][i];//LB
			float mul_up=light_array[j][i+1];//RB
			float mul_diagonal_up=light_array[j+1][i+1];//RU
			float mul_right=light_array[j+1][i];//LU
			
			//float mul_center=(mul+mul_diagonal_up)/2f;//LU
			
			

			/*
			if (j+i==Math.round((j+i)/2f)*2)
			{
				mul=1;
				mul_up=0.2f;
				mul_down=0.2f;
				mul_left=0.2f;
				mul_right=0.2f;
			}
			else
			{
				mul=0.2f;
				mul_up=0.2f;
				mul_down=0.2f;
				mul_left=0.2f;
				mul_right=0.2f;
			}*/
			
			if (mul==-1)
			{Main.shapeRenderer.setColor(1,0,0,1);}
			else
			{
				color1.set(mul*R, mul*G, mul*B,1);
				color2.set(mul_right*R, mul_right*G, mul_right*B,1);
				color3.set(mul_diagonal_up*R, mul_diagonal_up*G, mul_diagonal_up*B,1);
				color4.set(mul_up*R, mul_up*G, mul_up*B,1);
			}

			float vertex_x=(pos_x-light_size)*30+j*30+15;
			float vertex_y=(pos_y-light_size)*30+i*30+15;
			
			Main.shapeRenderer.rect(vertex_x, vertex_y, 30, 30,color1,color2,color3,color4);
			
			/*Main.shapeRenderer.setColor(mul*R, mul*G, mul*B, 1f);
			Main.shapeRenderer.rect(vertex_x, vertex_y, 30, 30);*/
			
			/*
			//--------------------------------------
			Main.shapeRenderer.end();
			Main.shapeRenderer.begin(ShapeType.Filled);
			//--------------------------------------
			
			
			Main.shapeRenderer.getRenderer().color(mul*R, mul*G, mul*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x, vertex_y, 0);

			Main.shapeRenderer.getRenderer().color(mul_center*R,mul_center*G, mul_center*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+15f, vertex_y+15f, 0);
			
			Main.shapeRenderer.getRenderer().color(mul_right*R, mul_right*G, mul_right*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+30f, vertex_y, 0);
			
			
			
			Main.shapeRenderer.getRenderer().color(mul_up*R, mul_up*G, mul_up*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x, vertex_y+30, 0);

			Main.shapeRenderer.getRenderer().color(mul_center*R, mul_center*G, mul_center*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+15f, vertex_y+15f, 0);
			
			Main.shapeRenderer.getRenderer().color(mul_diagonal_up*R, mul_diagonal_up*G, mul_diagonal_up*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+30f, vertex_y+30f, 0);
			
			

			Main.shapeRenderer.getRenderer().color(mul_diagonal_up*R, mul_diagonal_up*G, mul_diagonal_up*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+30f, vertex_y+30f, 0);

			Main.shapeRenderer.getRenderer().color(mul_center*R, mul_center*G, mul_center*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+15f, vertex_y+15f, 0);
			
			Main.shapeRenderer.getRenderer().color(mul_right*R, mul_right*G, mul_right*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+30f, vertex_y, 0);
			
			
			
			Main.shapeRenderer.getRenderer().color(mul_up*R, mul_up*G, mul_up*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x, vertex_y+30f, 0);

			Main.shapeRenderer.getRenderer().color(mul_center*R, mul_center*G, mul_center*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x+15f, vertex_y+15f, 0);
			
			Main.shapeRenderer.getRenderer().color(mul*R, mul*G, mul*B, 1f);
			Main.shapeRenderer.getRenderer().vertex(vertex_x, vertex_y, 0);
			*/
			
			
			
			
			
		}
	}

	public void update_light_position(float _x, float _y) {
		// TODO Auto-generated method stub
		pos_x=((int)(_x/30f));
		pos_y=((int)(_y/30f));
		
		Helper.log("LIGHT pos_x="+pos_x+" pos_y="+pos_y);
		
		update_light();
	}
}

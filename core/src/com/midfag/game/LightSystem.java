package com.midfag.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.midfag.entity.Entity;

public class LightSystem {

	public static Batch dynamic_batch_illum=GScreen.batch_illum;
	public static FrameBuffer lightmap_fbo=GScreen.lightmap_fbo;
	public static int light_map_size=1;
	
	public static Texture shadow_texture;
	
	
	public LightSystem()
	{
		
	}
	
	public static void static_light_update()
	{
		GScreen.need_light_update=true;

		//GScreen.add_timer("shadow_update");
		
		dynamic_batch_illum.enableBlending();
		dynamic_batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		
    		lightmap_fbo.begin();
	    		dynamic_batch_illum.begin();
	    			dynamic_batch_illum.enableBlending();
	    			dynamic_batch_illum.setColor(0.0f,0.0f,0.0f,1f);
		    		dynamic_batch_illum.draw(GScreen.rect_white,0,0,300*light_map_size,300*light_map_size);
		    		
		        	for (int x=GScreen.cluster_x-4; x<=GScreen.cluster_x+4; x++)
		        	for (int y=GScreen.cluster_y-4; y<=GScreen.cluster_y+4; y++)
		        	if ((x>=0)&&(y>=0)&&(x<30)&&(y<30))
		        	for (int i=0; i<GScreen.cluster[x][y].Entity_list.size();i++)
		        	{
		        		Entity e=GScreen.cluster[x][y].Entity_list.get(i);
		        		
		        		dynamic_batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);

		        		
		        		if ((!e.hidden)&&(e.light_source!=null))
		        		{
		        			dynamic_batch_illum.setColor(e.light_source.R,e.light_source.G,e.light_source.B,1f);
		        			dynamic_batch_illum.draw(GScreen.rect_white, (int)(e.pos.x/30-1)*light_map_size, (int)(e.pos.y/30-1)*light_map_size,3f*light_map_size, 3f*light_map_size); 
		        		}
		        		
		        		dynamic_batch_illum.setColor(1,1,1,1f);
		        		
		        		dynamic_batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
		        		dynamic_batch_illum.draw(shadow_texture, 0, 300*light_map_size,300*light_map_size, -300*light_map_size); 
		        	}
		        
	    		dynamic_batch_illum.end();
    		lightmap_fbo.end();
    		
    					//GScreen.add_timer("light_source_light_generate");
    		 
    		lightmap_fbo.begin();
    			dynamic_batch_illum.begin();

    				dynamic_batch_illum.enableBlending();
    			
    				dynamic_batch_illum.setColor(0.1f,0.1f,0.1f,1.0f);
    				
    				for (int k=0; k<16*light_map_size; k++)
    				{
    					dynamic_batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
			        	dynamic_batch_illum.setColor(0.1f,0.1f,0.10f,1.0f);				
    				
	    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0+1, 300*light_map_size, 300*light_map_size, -300*light_map_size);
	    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0-1, 300*light_map_size, 300*light_map_size, -300*light_map_size);
	    				
	    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, 300*light_map_size+0.5f, 300*light_map_size, -300*light_map_size);
	    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, 300*light_map_size-0.5f, 300*light_map_size, -300*light_map_size);
	    				
		    				dynamic_batch_illum.setColor(1,1,1,1f);
			        		
			        		dynamic_batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
			        		dynamic_batch_illum.draw(shadow_texture, 0, 300*light_map_size,300*light_map_size, -300*light_map_size); 

    				}
    				
    					//GScreen.add_timer("additive_blur");
    				
    				dynamic_batch_illum.setColor(0.0f,1.0f,0.0f,1f);
		    		dynamic_batch_illum.draw(GScreen.rect_white,250,250,50,50);
    			dynamic_batch_illum.end();
    		lightmap_fbo.end();
    		
    		lightmap_fbo.begin();
			dynamic_batch_illum.begin();

				dynamic_batch_illum.setColor(1f,1f,1f,0.5f);
				dynamic_batch_illum.enableBlending();
				
				
				for (int i=0; i<16*light_map_size; i++)
				{
					dynamic_batch_illum.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
					
					dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, 300*light_map_size, 300*light_map_size, -300*light_map_size);
    				
    				
    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0+1, 300*light_map_size, 300*light_map_size, -300*light_map_size);
    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0-1, 300*light_map_size, 300*light_map_size, -300*light_map_size);
    				
    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, 300*light_map_size+0.5f, 300*light_map_size, -300*light_map_size);
    				dynamic_batch_illum.draw(lightmap_fbo.getColorBufferTexture(), 0, 300*light_map_size-0.5f, 300*light_map_size, -300*light_map_size);
    				
    				dynamic_batch_illum.setColor(1,1,1,1f);
    				
	        		dynamic_batch_illum.setBlendFunction(GL20.GL_DST_COLOR, GL20.GL_ZERO);
	        		dynamic_batch_illum.draw(shadow_texture, 0, 300,300, -300); 
				}
				
				dynamic_batch_illum.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE);
				dynamic_batch_illum.setColor(GScreen.global_illumination);
				dynamic_batch_illum.draw(GScreen.rect_white, 0, 0, 300*light_map_size, 300*light_map_size);
				
				
			dynamic_batch_illum.end();
			
					//GScreen.add_timer("opacity_blur");
					
								
								if (GScreen.need_pixmap_update)				
			    				{
									GScreen.need_pixmap_update=false;
									Gdx.gl.glReadPixels(0, 0, 300, 300, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE,  GScreen.pixmap.getPixels());
									
									Helper.log("PIXMAP UPDATED");
								}
				
							
		lightmap_fbo.end();
		//GScreen.add_timer("get_pixmap");
		GScreen.lightmap_texture=lightmap_fbo.getColorBufferTexture();
		
    	

	}
	
}

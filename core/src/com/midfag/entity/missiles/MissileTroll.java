/**
 * www
 */
package com.midfag.entity.missiles;


import com.badlogic.gdx.math.Vector2;


/**
 * @author MidFag
 *
 */


public class MissileTroll extends Missile {

	public float wait_time=1;
	public float return_timer=0.2f;

	
	public MissileTroll(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		have_shd=true;
		
		
	}
	
	@Override
	public void preupdate (float _d)
	{
		if (return_timer>0)
		{
			return_timer-=_d;
			
			if (return_timer<=0)
			{
				float a=master.pos.x+master.impulse.x/4f-(pos.x);
		    	float b=master.pos.y+master.impulse.y/4f-(pos.y);
		    	//float c=(float) Math.sqrt((a*a)+(b*b));
		    	float c=(float) Math.atan2(a, b);
				angle=c;
				
				update_vectors_state();
			}
		}
	}
	

	
	
}

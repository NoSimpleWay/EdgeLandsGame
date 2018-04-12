/**
 * www
 */
package com.midfag.entity.missiles;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdLaser;
import com.midfag.game.GScreen;

/**
 * @author MidFag
 *
 */


public class MissileLaser extends Missile {

	public float wait_time=1;
	
	public MissileLaser(Vector2 _v,float _a, float _s, boolean _b)
	{
		super(_v, _a, _s, _b);
		
		have_shd=true;
		
		
	}
	
	@Override
	public Shd get_shd(Vector2 _s,Vector2 _e)
	{
		Shd s=new ShdLaser(_s,_e);
		s.lifetime=1;
		
		return s;
	}
	
	@Override
	public void draw_shd(float delta) {
		// TODO Auto-generated method stub
		GScreen.sr.setColor(0.0f,0.5f,1.0f,shd_lifetime/2.0f);
		
		GScreen.sr.rectLine(start_x, start_y, pos.x, pos.y,7);
		
	}
}

/**
 * www
 */
package com.midfag.entity.missiles;

import com.badlogic.gdx.math.Vector2;
import com.midfag.entity.Entity;
import com.midfag.entity.Shd;
import com.midfag.entity.ShdLaser;
import com.midfag.game.GScreen;

/**
 * @author MidFag
 *
 */


public class MissileMirrorLaser extends Missile {

	public float wait_time=1;
	public float chance_to_clone=1;
	
	public MissileMirrorLaser(Vector2 _v,float _a, float _s, boolean _b)
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
	
	public void another_hit_action(Entity near_entity) {
		// TODO Auto-generated method stub
		if(Math.random()<chance_to_clone)
		{
		/*lifetime=1;
		angle=(float) (Math.random()*360);
		speed=700;
		update_vectors_state();
		
		damage/=2f;
		fire_damage/=2f;
		cold_damage/=2f;
		
		chance_to_clone/=2f;
		*/
			
		for (int k=0; k<4; k++)
		{
			Missile m=new MissileMirrorLaser(new Vector2(pos),(float) (Math.random()*360),700,is_enemy);
			
			m.damage=damage/2f;
			m.fire_damage=fire_damage/2f;
			m.cold_damage=cold_damage/2f;
			
			((MissileMirrorLaser)m).chance_to_clone=chance_to_clone/2f;
			
			GScreen.Missile_list.add(m);
		}
		}
	}
}

package com.midfag.entity.enemies;

import com.badlogic.gdx.math.Vector2;

public class EntityPyraFriend extends EntityPyra {

	public EntityPyraFriend(Vector2 _v) {
		super(_v);
		
		is_enemy=false;
		id=this.getClass().getName();
		uid="272b3224";
		// TODO Auto-generated constructor stub
	}

}

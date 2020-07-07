/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.geckolib.example.entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import software.bernie.geckolib.animation.AnimationBuilder;
import software.bernie.geckolib.animation.AnimationTestEvent;
import software.bernie.geckolib.animation.model.AnimationController;
import software.bernie.geckolib.animation.model.AnimationControllerCollection;
import software.bernie.geckolib.entity.IAnimatedEntity;

public class BrownEntity extends EntityMob implements IAnimatedEntity
{
	public AnimationControllerCollection animationControllers = new AnimationControllerCollection();
	private AnimationController animationController = new AnimationController(this, "animationController", 20,
			this::animationPredicate);

	public BrownEntity(World worldIn)
	{
		super(worldIn);
		registerAnimationControllers();
	}

	@Override
	public AnimationControllerCollection getAnimationControllers()
	{
		return animationControllers;
	}


	public void registerAnimationControllers()
	{
		if (world.isRemote)
		{
			animationController.setAnimation(new AnimationBuilder().addAnimation("running"));
			this.animationControllers.addAnimationController(animationController);
		}
	}

	public boolean animationPredicate(AnimationTestEvent event)
	{

		animationController.transitionLength = 40;
		animationController.setAnimation(new AnimationBuilder().addAnimation("running", true));

		return true;
	}


}

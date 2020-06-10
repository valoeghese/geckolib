/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.geckolib.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.animation.keyframe.AnimationPoint;
import software.bernie.geckolib.animation.model.AnimatedEntityModel;

public class AnimationUtils
{
	public static double convertTicksToSeconds(double ticks)
	{
		return ticks / 20;
	}

	public static double convertSecondsToTicks(double seconds)
	{
		return seconds * 20;
	}

	/**
	 * This is the actual function that smoothly interpolates (lerp) between keyframes
	 *
	 * @param currentTick The current tick (usually entity.ticksExisted + partialTicks to make it smoother)
	 * @param position    The animation's length in seconds
	 * @param startValue  The animation's start value
	 * @param endValue    The animation's end value
	 * @return The interpolated value
	 */
	public static float lerpValues(double currentTick, double position, double startValue, double endValue)
	{
		if (currentTick > position)
		{
			return (float) endValue;
		}
		if(currentTick == 0 && position == 0)
		{
			return (float) endValue;
		}
		// current tick / position should be between 0 and 1 and represent the percentage of the lerping that has completed
		return (float) (lerp(currentTick / position, startValue,
				endValue) * position / position);
	}

	public static double lerp(double pct, double start, double end) {
		return start + pct * (end - start);
	}


	/**
	 * Lerps an AnimationPoint
	 *
	 * @param animationPoint The animation point
	 * @return the resulting lerped value
	 */
	public static float lerpValues(AnimationPoint animationPoint)
	{
		return lerpValues(animationPoint.currentTick, animationPoint.animationEndTick, animationPoint.animationStartValue, animationPoint.animationEndValue);
	}

	/**
	 * Gets the renderer for an entity
	 * @return
	 */
	public static <T extends Entity> Render<Entity> getRenderer(T entity)
	{
		RenderManager renderManager = Minecraft.getMinecraft().getRenderManager();
		return renderManager.getEntityRenderObject(entity);
	}

	/**
	 * Gets the AnimatedEntityModel for an entity.
	 */
	public static <T extends Entity> AnimatedEntityModel getModelForEntity(T entity)
	{
		Render<Entity> entityRenderer = getRenderer(entity);
		if (entityRenderer instanceof RenderLiving)
		{
			RenderLiving renderer = (RenderLiving) entityRenderer;
			ModelBase entityModel = renderer.getMainModel();
			if (entityModel instanceof AnimatedEntityModel)
			{
				return (AnimatedEntityModel) entityModel;
			}
			else {
				GeckoLib.LOGGER.error("Model for " + entity.getName() + " is not an AnimatedEntityModel. Please inherit the proper class.");
				return null;
			}
		}
		else {
			GeckoLib.LOGGER.error("Could not find valid renderer for " + entity.getName());
			return null;
		}
	}

}

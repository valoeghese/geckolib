/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.geckolib.example.client.renderer.entity;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib.example.client.renderer.model.TigrisModel;
import software.bernie.geckolib.example.entity.TigrisEntity;

import javax.annotation.Nullable;

public class TigrisRenderer extends RenderLiving<TigrisEntity>
{
	public TigrisRenderer(RenderManager rendererManager)
	{
		super(rendererManager, new TigrisModel(), 0.5F);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(TigrisEntity entity)
	{
		return new ResourceLocation("geckolib" + ":textures/model/entity/tigris.png");
	}
}
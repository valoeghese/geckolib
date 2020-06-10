/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.geckolib;


import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import software.bernie.geckolib.example.client.renderer.entity.AscendedLegfishRenderer;
import software.bernie.geckolib.example.client.renderer.entity.StingrayRenderer;
import software.bernie.geckolib.example.entity.AscendedLegfishEntity;
import software.bernie.geckolib.example.entity.StingrayTestEntity;
import software.bernie.geckolib.example.entity.TigrisEntity;
import software.bernie.geckolib.example.client.renderer.entity.TigrisRenderer;

@Mod.EventBusSubscriber(modid= GeckoLib.ModID)
public class ModEventBus
{
	static int id = 50;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void doClientStuff(RegistryEvent.Register<EntityEntry> event)
	{
		event.getRegistry().register(EntityEntryBuilder.create().entity(StingrayTestEntity.class).name("stingray").id(new ResourceLocation(GeckoLib.ModID, "stingray"), id++).tracker(160, 2, false).build());
		event.getRegistry().register(EntityEntryBuilder.create().entity(AscendedLegfishEntity.class).name("ascended_legfish").id(new ResourceLocation(GeckoLib.ModID, "ascended_legfish"), id++).tracker(160, 2, false).build());
		event.getRegistry().register(EntityEntryBuilder.create().entity(TigrisEntity.class).name("ascended_legfish").id(new ResourceLocation(GeckoLib.ModID, "tigris"), id++).tracker(160, 2, false).build());

		RenderingRegistry.registerEntityRenderingHandler(StingrayTestEntity.class, StingrayRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(AscendedLegfishEntity.class, AscendedLegfishRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(TigrisEntity.class, TigrisRenderer::new);

	}
}

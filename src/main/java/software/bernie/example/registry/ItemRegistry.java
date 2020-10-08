package software.bernie.example.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import software.bernie.example.client.renderer.item.JackInTheBoxRenderer;
import software.bernie.example.item.JackInTheBoxItem;
import software.bernie.example.item.PotatoArmor;
import software.bernie.geckolib.GeckoLib;

public class ItemRegistry
{
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, GeckoLib.ModID);


	public static final RegistryObject<ArmorItem> POTATO_HELMET = ITEMS.register("potato_helmet", () -> new PotatoArmor(EquipmentSlotType.HEAD));
	public static final RegistryObject<ArmorItem> POTATO_CHEST = ITEMS.register("potato_chest", () -> new PotatoArmor(EquipmentSlotType.CHEST));
	public static final RegistryObject<ArmorItem> POTATO_LEGS = ITEMS.register("potato_legs", () -> new PotatoArmor(EquipmentSlotType.LEGS));
	public static final RegistryObject<ArmorItem> POTATO_BOOTS = ITEMS.register("potato_boots", () -> new PotatoArmor(EquipmentSlotType.FEET));
	public static final RegistryObject<JackInTheBoxItem> JACK = ITEMS.register("jackintheboxitem", () -> new JackInTheBoxItem(new Item.Properties().setISTER(() -> JackInTheBoxRenderer::new)));

}

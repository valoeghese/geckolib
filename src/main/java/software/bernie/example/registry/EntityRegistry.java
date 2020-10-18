/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.example.registry;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.util.EntityRegistryBuilder;

public class EntityRegistry {
    public static final EntityType<GeoExampleEntity> GEO_EXAMPLE_ENTITY = buildEntity(GeoExampleEntity::new, GeoExampleEntity.class, .7F, 1.3F);

    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass, float width, float height) {
        String name = entityClass.getSimpleName().toLowerCase();
        return EntityRegistryBuilder.<T>createBuilder(new Identifier(GeckoLib.ModID, name))
                .entity(entity)
                .category(SpawnGroup.CREATURE)
                .dimensions(EntityDimensions.fixed(width, height))
                .build();
    }
}
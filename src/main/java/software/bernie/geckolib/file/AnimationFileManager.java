/*
 * Copyright (c) 2020.
 * Author: Bernie G. (Gecko)
 */

package software.bernie.geckolib.file;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * An animation file manager is responsible for reading animation json files.
 */
public class AnimationFileManager
{
	private ResourceLocation location;

	/**
	 * Instantiates a new Animation file manager.
	 *
	 * @param Location the resource location of the json file
	 */
	public AnimationFileManager(ResourceLocation Location)
	{
		location = Location;
	}

	private JsonObject loadAnimationFile(ResourceLocation location) throws Exception
	{
		Gson GSON = new Gson();
		IReloadableResourceManager resourceManager = (IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager();
		SimpleResource resource = (SimpleResource) resourceManager.getResource(location);
		Reader reader = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
		JsonObject jsonobject = JsonUtils.fromJson(GSON, reader, JsonObject.class);
		resource.close();
		return jsonobject;
	}

	/**
	 * Loads json from the current animation file.
	 *
	 * @return the json object
	 * @throws Exception Thrown if an exception is encountered while loading the file
	 */
	public JsonObject loadAnimationFile() throws Exception
	{
		return loadAnimationFile(location);
	}
}

package software.bernie.geckolib.file;

import com.eliotlash.molang.MolangParser;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.gl.ShaderParseException;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.apache.commons.io.IOUtils;
import software.bernie.geckolib.GeckoLib;
import software.bernie.geckolib.core.builder.Animation;
import software.bernie.geckolib.util.json.JsonAnimationUtils;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

public class AnimationFileLoader {

    public AnimationFile loadAllAnimations(MolangParser parser, Identifier location, ResourceManager manager) {
        AnimationFile animationFile = new AnimationFile();
        JsonObject jsonRepresentation = loadFile(location, manager);
        Set<Map.Entry<String, JsonElement>> entrySet = JsonAnimationUtils.getAnimations(jsonRepresentation);
        for (Map.Entry<String, JsonElement> entry : entrySet) {
            String animationName = entry.getKey();
            Animation animation;
            try {
                animation = JsonAnimationUtils.deserializeJsonToAnimation(JsonAnimationUtils.getAnimation(jsonRepresentation, animationName), parser);
                animationFile.putAnimation(animationName, animation);
            } catch (ShaderParseException e) {
                GeckoLib.LOGGER.error("Could not load animation: {}", animationName, e);
                throw new RuntimeException(e);
            }
        }
        return animationFile;
    }


    /**
     * Internal method for handling reloads of animation files. Do not override.
     */
    private JsonObject loadFile(Identifier location, ResourceManager manager) {
        String content = getFileAsString(location, manager);
        Gson GSON = new Gson();
        return JsonHelper.deserialize(GSON, content, JsonObject.class);
    }


    public String getFileAsString(Identifier location, ResourceManager manager) {
        try (InputStream inputStream = getStreamForIdentifier(location, manager)) {
            return IOUtils.toString(inputStream);
        } catch (Exception e) {
            String message = "Couldn't load " + location;
            GeckoLib.LOGGER.error(message, e);
            throw new RuntimeException(new FileNotFoundException(location.toString()));
        }
    }

    public InputStream getStreamForIdentifier(Identifier resourceLocation, ResourceManager manager) {
        try {
            return new BufferedInputStream(manager.getResource(resourceLocation).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
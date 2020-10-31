package software.bernie.geckolib.geo.render.built;

import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;

public class GeoQuad
{
	public GeoVertex[] vertices;
	public final Vector3f normal;
	public Direction direction;

	public GeoQuad(GeoVertex[] verticesIn, float u1, float v1, float uSize, float vSize, float texWidth, float texHeight, Boolean mirrorIn, Direction directionIn)
	{
		this.direction = directionIn;
		this.vertices = verticesIn;

		/*
		u1 is the distance from the very left of the texture to where the uv region starts
		v1 is the distance from the very top of the texture to where the uv region starts
		u2 is the horizontal distance from u1 to where the uv region ends
		v2 is the vertical distance from the v1 to where the uv region ends
		 */

		float u2 = u1 + uSize;
		float v2 = v1 + vSize;

		// Normalize the coordinates to be relative (between 0 and 1)
		u1 /= texWidth;
		u2 /= texWidth;
		v1 /= texHeight;
		v2 /= texHeight;

		// u1, v1  - Top left corner of uv region
		// u2, v1  - Top right corner of uv region
		// u1, v2  - Bottom left corner of uv region
		// u2, v2  - Bottom right corner of uv region

		//Sets the new normalized texture coordinates of each vertex using the positions described above
		if (mirrorIn != null && mirrorIn) {
			vertices[0] = verticesIn[0].setTextureUV(u1, v1); // Top left corner
			vertices[1] = verticesIn[1].setTextureUV(u2, v1); // Top right corner
			vertices[2] = verticesIn[2].setTextureUV(u2, v2); // Bottom left corner
			vertices[3] = verticesIn[3].setTextureUV(u1, v2); // Bottom right corner
		} else {
			vertices[0] = verticesIn[0].setTextureUV(u2, v1); // Top left corner
			vertices[1] = verticesIn[1].setTextureUV(u1, v1); // Top right corner
			vertices[2] = verticesIn[2].setTextureUV(u1, v2); // Bottom left corner
			vertices[3] = verticesIn[3].setTextureUV(u2, v2); // Bottom right corner
		}

//		//Mirrors the quad i guess?
//		if (mirrorIn != null && mirrorIn)
//		{
//			int i = verticesIn.length;
//
//			for (int j = 0; j < i / 2; ++j)
//			{
//				GeoVertex modelrenderer$positiontexturevertex = verticesIn[j];
//				verticesIn[j] = verticesIn[i - 1 - j];
//				verticesIn[i - 1 - j] = modelrenderer$positiontexturevertex;
//			}
//		}

		//only god knows what this does, but eliot told me it generates a normal vector which helps the game do lighting properly or something idk i didnt pay attention in physics we were in remote learning gimme a break
		this.normal = directionIn.toVector3f();
//		if (mirrorIn != null && mirrorIn)
//		{
//			this.normal.mul(-1.0F, 1.0F, 1.0F);
//		}
	}


	public GeoQuad(GeoVertex[] verticesIn, double[] uvCoords, double[] uvSize, float texWidth, float texHeight, Boolean mirrorIn, Direction directionIn)
	{
		this(verticesIn, (float) uvCoords[0], (float) uvCoords[1], (float) uvSize[0], (float) uvSize[1], texWidth, texHeight, mirrorIn, directionIn);
	}
}

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBImageResize.*;
import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Texture
	{
		//Image Setup:
		ByteBuffer imageBuffer;
		IntBuffer w = createIntBuffer(1);
		IntBuffer h = createIntBuffer(1);
		IntBuffer c = createIntBuffer(1);
		int TexId;
		public void load(String filePath)
			{
				//Ucitava sliku iz resourse
				imageBuffer = stbi_load(filePath, w, h, c, STBI_rgb_alpha);
				TexId = glGenTextures();
				glBindTexture(GL_TEXTURE_2D, TexId);
				
				glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MIN_FILTER,GL_LINEAR);
				glTexParameteri(GL_TEXTURE_2D,GL_TEXTURE_MAG_FILTER,GL_LINEAR);

				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, w.get(), h.get(), 0,
					       	GL_RGBA, GL_UNSIGNED_BYTE, imageBuffer);
			}
		Texture(String filePath)
			{
				load(filePath);
			}
		int getTexId()
			{
				return TexId;
			}
	}

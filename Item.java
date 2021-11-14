import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBImageResize.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Item
	{
		//Image Setup:
		int TexId;
		//Item settins
		float posX, posY;
		float width, height;
		//Color
		float R, G, B;
		public void draw()
			{
				GL11.glColor3f(R,G,B);
				glBindTexture(GL_TEXTURE_2D, TexId);
				glBegin(GL_QUADS);
					{
						//pox-w/2, poxY + h/2 centrira teksture.
						//XY je sredina pravougaonika, w i h su jednaki sa obe strane
						glTexCoord2f(0.0f, 0.0f);
						glVertex2f(posX-(width/2.0f), posY+(height/2.0f));
						glTexCoord2f(1.0f, 0.0f);
						glVertex2f(posX+(width/2.0f), posY+(height/2.0f));
						glTexCoord2f(1.0f, 1.0f);
						glVertex2f(posX+(width/2.0f), posY-(height/2.0f));
						glTexCoord2f(0.0f, 1.0f);
						glVertex2f(posX-(width/2.0f), posY-(height/2.0f));
					}
				glEnd();
			}
		void setXY(float x, float y)
			{
				this.posX = x;
				this.posY = y;
			}
		void setWH(float w, float h)
			{
				this.width = w;
				this.height = h;
			}
		void setRGB(float R, float G, float B)
			{
				this.R = R;
				this.G = G;
				this.B = B;
			}
		void setTexture(int TexId)
			{
				this.TexId = TexId;
			}
		Item(int TexId, float x, float y, float w, float h, float r, float g, float b)
			{
				this.TexId=TexId;
				this.posX = x;
				this.posY = y;
				this.width = w;
				this.height = h;
				this.R = r;
				this.G = g;
				this.B = b;
			}

	}

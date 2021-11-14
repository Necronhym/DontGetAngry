import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
//NULL pointers dont exist in java :|
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Graphics
	{
		//Window ID
		private long windowId;
		//Windwo Settings:
		int posX, posY;
		int width, height;
		String Name;
		boolean readWindowConfig(String File)
			{
				//Get Pharsing to work later
				posX = 100;
				posY = 100;
				width = 600;
				height = 600;
				Name = "Man, Don't Get Mad";
				return true;
			}
		boolean init()
			{
				System.out.print("Initializing Graphics \n");
				//Inicira GLFW
				glfwInit();
				//Ucita podesavanja prozora
				readWindowConfig("config");
				//Kreira prozor sa podesavanjima
				windowId = glfwCreateWindow(width, height, Name, NULL, NULL);
				//Zakljuca resize i vidljivost prozora
				glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
				glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
				//Selektuje prozor
				glfwMakeContextCurrent(windowId);	
				//Pikaze prozor
				glfwShowWindow(windowId);
				//Centrira cursonr
				glfwSetCursorPos(windowId, width/2, height/2);
			
				//GL -> GLFW bindng	
				GL.createCapabilities();
				//Default color (Gray);
				glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
				//GL Blending fucntions, transparancy and textures
				glEnable(GL_TEXTURE_2D);
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
				return true;
			}
		long getWindowId()
			{
				return this.windowId;
			}
		void clear()
			{
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
			}
		void update()
			{
				glfwSwapBuffers(windowId);
			}
		void close()
			{
				System.out.print("Closing Graphics \n");
				//Reset Gl Blending
				glDisable(GL_TEXTURE_2D);
				glDisable(GL_BLEND);
				//Oslobacaj memoriju za prozor:
				glfwDestroyWindow(windowId);
				glfwTerminate();
			}
	}

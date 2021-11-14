import org.lwjgl.glfw.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Input
	{
		long windowId;
		double mouseX;
		double mouseY;
		int windowWidth;
		int windowHeight;
		boolean event;
		double eventX;
		double eventY;
		boolean init(long windowId)
			{
				this.windowId = windowId;
				System.out.print("Initializing Input\n");
				//Kyboard input
				glfwSetKeyCallback(windowId, (window, key, scancode, action, mods) ->
					{
						if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
							{
								glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
							}
					});

				//Get Window Dimentions:
				IntBuffer w = createIntBuffer(1);
				IntBuffer h = createIntBuffer(1);
				glfwGetWindowSize(windowId, w, h);
				windowWidth = w.get(0);
				windowHeight = h.get(0);

				//Mouse Cursor Pos
				glfwSetCursorPosCallback(windowId, (window, mx, my)->
					{
						//Convert mouse to GL style Coords:
						mouseX = -(1.0 - (mx/windowWidth*2.0));
						mouseY =  (1.0 - (my/windowHeight*2.0));
					});
				//Mouse Button Callback	
				glfwSetMouseButtonCallback(windowId, (window, button, action, mods) ->
					{
						event=false;
						if( button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS )
							{
								event=true;
								eventX = mouseX;
								eventY = mouseY;
							}
						if( button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS )
							{
							
							}
						if( button == GLFW_MOUSE_BUTTON_3 && action == GLFW_PRESS )
							{
							
							}
					});
				
				return true;
			}
		void update()
			{
				glfwPollEvents();
			}
		boolean exit()
			{
				return !glfwWindowShouldClose(windowId);
			}
		void close()
			{
				System.out.print("Closing Input\n");
			}
		double getMouseX()
			{
				return this.mouseX;
			}
		double getMouseY()
			{
				return this.mouseY;
			}
	}

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.openal.*;
import org.lwjgl.stb.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.openal.AL.*;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBImage.*;
import static org.lwjgl.stb.STBImageResize.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main 
	{
		public static void main(String[] args) 
			{
				Game G = new Game();
				if(args.length < 1)
					{
						System.out.print("Broj igraca 4\n");
						G.run(4);
					}
				else
					{
						if( 1<Integer.parseInt(args[0]) && Integer.parseInt(args[0]) < 5) 
							{
								System.out.print("Broj igraca ");
								System.out.print(Integer.parseInt(args[0])); 
								System.out.print('\n');
								G.run(Integer.parseInt(args[0]));
							}
						else
							{
								System.out.print("Neispravan broj igraca\n");
							}
					}

			}
	}


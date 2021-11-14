import org.lwjgl.openal.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.openal.AL.*;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Audio
	{
		//Sound Setup:
		long device;
		long context;

		boolean init()
			{
				System.out.print("Initializing Audio\n");
				//Setup AL	
				String defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
				device = alcOpenDevice(defaultDeviceName);

				int[] attributes = {0};
				context = alcCreateContext(device, attributes);
				alcMakeContextCurrent(context);
			
				ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
				ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

				return true;
			}
		void play(Sound File)
			{
				alSourcePlay(File.getSound());
			}
		void close()
			{
				System.out.print("Closing Audio\n");
				alcDestroyContext(context);
				alcCloseDevice(device);
			}
	}

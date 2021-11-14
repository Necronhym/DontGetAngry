import org.lwjgl.openal.*;
import org.lwjgl.system.*;

import java.nio.*;

import static org.lwjgl.openal.AL.*;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class Sound
	{
		int sourcePointer;	
		void load(String File)
			{	
				//Allocate space to store return information from the function
				String fileName = File;
				stackPush();
				IntBuffer channelsBuffer = stackMallocInt(1);
				stackPush();
				IntBuffer sampleRateBuffer = stackMallocInt(1);
	
				ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(fileName, channelsBuffer, sampleRateBuffer);
				//Retreive the extra information that was stored in the buffers by the function
				int channels = channelsBuffer.get();
				int sampleRate = sampleRateBuffer.get();
				//Free the space we allocated earlier
				stackPop();
				stackPop();
				//Set corrent openAL format:
				int format = -1;
				if(channels == 1) 
					{
						format = AL_FORMAT_MONO16;
					}
		       		else if(channels == 2) 
					{
    						format = AL_FORMAT_STEREO16;
					}
				//Request buffers:
				int bufferPointer = alGenBuffers();
				alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);
				sourcePointer = alGenSources();
				//Assign our buffer to the source
				alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
			}
		Sound(String File)
			{
				load(File);
			}
		int getSound()
			{
				return this.sourcePointer;
			}
	}

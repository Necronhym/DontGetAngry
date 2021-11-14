public class Paeon extends Active
	{
		int position;
		int steps;
		int player;
		float startX;
		float startY;
		int exitPos;
		Paeon(int player, int position, int TexId, float x, float y, float w, float h, float r, float g, float b)	
			{
				super(TexId, x, y, w, h, r, g, b);
				this.startX = x;
				this.startY = y;
				this.player = player;
				this.position = position;
				this.steps = 0;
				this.exitPos = 0;
				//set polor
				switch(player)
				{
					case 0:
						setRGB(0.9f, 0.1f, 0.1f);
					break;
					case 1:
						setRGB(0.1f, 0.9f, 0.1f);
					break;
					case 2:
						setRGB(0.1f, 0.1f, 0.9f);
					break;
					case 3:
						setRGB(0.9f, 0.9f, 0.1f);
					break;
				}
			}
		int getPlayer()
			{
				return this.player;
			}	
		int getPosition()
			{
				return this.position;
			}
		int getStartPosition()
			{
				return this.player * 10 + 6;
			}
		int getExitPosition()
			{
				return this.player * 10 + 6-1;
			}
		void sendStart()
			{
				position = 0;
				this.setXY(startX, startY);
				steps = 0;
			}
		boolean sendExit(int i, boolean[][] Exits)
			{
				if(Exits[player][i-1] == false)
					{
						position = -1;
						switch(player)
							{
								case 0:
									this.posX = 0.0f;
									this.posY = -0.9f+0.18f*i;
								break;
								case 1:
									this.posY = 0.0f;
									this.posX = -0.9f+0.18f*i;

								break;
								case 2:
									this.posX = 0.0f;
									this.posY = 0.9f-0.18f*i;
								break;
								case 3:
									this.posY = 0.0f;
									this.posX = 0.9f-0.18f*i;
								break;
							}
						if(exitPos > 0)
							{
								Exits[player][exitPos-1] = false;
							}
						exitPos=i;
						Exits[player][exitPos-1] = true;
						return true;
					}
				return false;
			}
		boolean move(int m, int p, boolean[][] Exits)
			{
				boolean skip = false;
				if(p != player)
					{
						return false;
					}
				if(position == 0 && m!=6)
					{
						position = 0;
						return false;
					}
				else if(position == 0 && m==6)
					{
						m = player*10 + 7;
						steps = 0;
						skip = true;
					}
				if(steps + m > 43)
					{
						return false;
					}
				if(steps + m > 39)
					{
						if (sendExit(steps+m-39, Exits))
							{
								steps = steps + m;
								return true;
							}
						return false;
					}
				if(!skip)
					{
						steps = steps + m;
					}
				for(int i=0; i<m; i++)
					{
						position++;
						if(position > 40)
							{
								position = 1;
							}
						if(position < 12)
							{
								this.posX = 1.08f - (position*0.18f);
								this.posY = -0.9f;
							}
						else if(position < 22)
							{
								this.posY = -0.9f +((position-11)*0.18f);
							}
						else if(position < 32)
							{
								this.posX = -0.9f + ((position-21)*0.18f);
							}
						else
							{
								this.posY = 0.9f - ((position-31)*0.18f);
							}
					}
				return true;
			}
	}

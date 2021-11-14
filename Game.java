public class Game
	{
		//Sub engines:
		//Input (Just Mouse)
		Input I = new Input();
		//Output
		Graphics G = new Graphics();
		Audio A = new Audio();
		//Game Variables:
		int playerNu;
		//Dice
		Dice D = new Dice();
		Texture[] DiceTextures;
		
		//Graphics objects
		Texture[] Textures;
		Texture[] WinScreenTex;
		Item[] Items;
		Item WinScreen;	

		//Dice
		Active Dice;
		//Peaons
		Paeon[] Paeons;

		//Sounds
		Sound[] DiceSounds;
		Sound EatSound;
		Sound KnockSound;
		Sound Win;
		//Win conditions
		boolean done=false;
		int winner=-1;

		//Load resources needed for the game
		public void load()
			{
				System.out.print("Loading Game Assets \n");
				
				//Graphics load:
				Textures = new Texture[3];
				//Piece textures	
				Textures[0] = new Texture("res/bg.jpg");
				Textures[1] = new Texture("res/circle.png");
				Textures[2] = new Texture("res/paeon.png");
				//Win screen textures
				WinScreenTex = new Texture[4];
				WinScreenTex[0] = new Texture("res/win0.png");
				WinScreenTex[1] = new Texture("res/win1.png");
				WinScreenTex[2] = new Texture("res/win2.png");
				WinScreenTex[3] = new Texture("res/win3.png");
				//Doce 1-6 tex
				DiceTextures = new Texture[7];
				DiceTextures[0] = new Texture("res/dice0.png");
				DiceTextures[1] = new Texture("res/dice1.png");
				DiceTextures[2] = new Texture("res/dice2.png");
				DiceTextures[3] = new Texture("res/dice3.png");
				DiceTextures[4] = new Texture("res/dice4.png");
				DiceTextures[5] = new Texture("res/dice5.png");
				DiceTextures[6] = new Texture("res/dice6.png");
			
				//Audio
				DiceSounds = new Sound[3];
				DiceSounds[0] = new Sound("res/dice1.ogg");
				DiceSounds[1] = new Sound("res/dice2.ogg");
				DiceSounds[2] = new Sound("res/dice3.ogg");

				EatSound = new Sound("res/crunch.ogg");
				KnockSound = new Sound("res/knock.ogg");
				Win = new Sound("res/win.ogg");
			}
		boolean[][] Exits;

		int turn;
		/*
		 * 0 Red
		 * 1 Green
		 * 2 Blue
		 * 3 Yellow
		 */

		public void init(int playerNu)
			{
				System.out.print("Initializing Game \n");
				//Creates all pawns
				this.playerNu = playerNu;

				WinScreen = new Item(WinScreenTex[0].getTexId(), 0f, 0f, 2.0f, 0.8f, 0.9f, 0.9f, 0.9f);
				Items = new Item[80];
				//Background:
				Items[0] = new Item(Textures[0].getTexId(), 0f, 0f, 2f, 2f, 0.9f, 0.9f, 0.9f);	
				
				//Board Graphics:	
				//Red Bot
				float tempX=1.08f;
				float tempY=-0.90f;
				for(int i=0; i<40; i++)
					{
						//Walk around and set dots
						if(i<11)
							{
								tempX = tempX - 0.18f;
							}
						else if(i<21)
							{
								tempY = tempY + 0.18f;
							}
						else if(i<31)
							{
								tempX = tempX + 0.18f;
							}
						else
							{
								tempY = tempY - 0.18f;
							}
						//Color dots for player entry
						switch(i)
							{
								case 6:
								Items[i+1] = new Item(Textures[1].getTexId(), tempX, tempY, 0.15f, 0.15f, 0.9f, 0.1f, 0.1f);
								break;
								case 16:
								Items[i+1] = new Item(Textures[1].getTexId(), tempX, tempY, 0.15f, 0.15f, 0.1f, 0.9f, 0.1f);
								break;
								case 26:
								Items[i+1] = new Item(Textures[1].getTexId(), tempX, tempY, 0.15f, 0.15f, 0.1f, 0.1f, 0.9f);
								break;
								case 36:
								Items[i+1] = new Item(Textures[1].getTexId(), tempX, tempY, 0.15f, 0.15f, 0.9f, 0.9f, 0.1f);
								break;

								default:	
								Items[i+1] = new Item(Textures[1].getTexId(), tempX, tempY, 0.15f, 0.15f, 0.9f, 0.9f, 0.9f);	
							}
					}
				//Exit:
				int k=0;
				float r=0,g=0,b=0;
				float Px=0, Py=0;
				float xMod=0, yMod=0;
				for(int j=0; j<4; j++)
					{
						switch(j)
							{
								case 0:
									r=0.9f;
									g=0.1f;
									b=0.1f;
									xMod = 0f;
									yMod = -0.18f;
								break;
								case 1:
									r=0.1f;
									g=0.9f;
									b=0.1f;	
									xMod = -0.18f;
									yMod = 0;
								break;
								case 2:
									r=0.1f;
									g=0.1f;
									b=0.9f;	
									xMod = 0f;
									yMod = 0.18f;
								break;
								case 3:
									r=0.9f;
									g=0.9f;
									b=0.1f;	
									xMod = 0.18f;
									yMod = 0;
								break;
							}
						for(int i=1; i<5; i++)
							{
		Items[42+k] = new Item(Textures[1].getTexId(), 0.0f+i*xMod, 0.0f+i*yMod, 0.15f, 0.15f, r, g, b);
								k++;
							}
					}
				//Declare exits as empty
				Exits = new boolean[4][4];
				for(int i=0; i<4; i++)
					{
						for(int j=0; j<4; j++)
							{
								Exits[i][j] = false;
							}
					}

				//Dice
				Dice = new Active(DiceTextures[1].getTexId(), 0.0f, 0.0f, 0.2f, 0.2f, 0.9f, 0.1f, 0.1f);	
				//Init Peaons:
				Paeons = new Paeon[playerNu*4];
				k=0;
				float modX=0;
			       	float modY=0;
				for(int i=0; i<playerNu; i++)
					{
						switch(i)
							{
								case 3:
									modX = 0.36f;
									modY = -0.54f;	
								break;
								case 0:
									modX = -0.54f;
									modY = -0.54f;
								break;
								case 1:
									modX = -0.54f;
									modY = 0.36f;
								break;
								case 2:
									modX = 0.36f;
									modY = 0.36f;
								break;
							}
						Paeons[k] = new Paeon(i, 0, Textures[2].getTexId(), modX, modY, 0.15f, 0.15f, 0.9f, 0.9f, 0.9f);	
						Paeons[k+1] = new Paeon(i, 0, Textures[2].getTexId(), modX, modY+0.18f, 0.15f, 0.15f, 0.9f, 0.9f, 0.9f);	
						Paeons[k+2] = new Paeon(i, 0, Textures[2].getTexId(), modX+0.18f, modY+0.18f, 0.15f, 0.15f, 0.9f, 0.9f, 0.9f);	
						Paeons[k+3] = new Paeon(i, 0, Textures[2].getTexId(), modX+0.18f, modY, 0.15f, 0.15f, 0.9f, 0.9f, 0.9f);	
						k = k + 4;
					}
				turn = 0;	
			}
		//Render Handler:
		void render()
			{
				//Render Passive Stuff:
				for(Item I : Items)
					{
						if(I != null)
							{
								I.draw();
							}
					}
				//Render Acrives:
				//Dice
				switch (turn)
					{
						case 0:
						Dice.setRGB(0.9f, 0.1f, 0.1f);
						break;
						case 1:
						Dice.setRGB(0.1f, 0.9f, 0.1f);
						break;
						case 2:
						Dice.setRGB(0.1f, 0.1f, 0.9f);
						break;
						case 3:
						Dice.setRGB(0.9f, 0.9f, 0.1f);
						break;
					}
					Dice.draw();
					Dice.setTexture(DiceTextures[lastRoll].getTexId());
					//Paeons:
					for(Paeon P : Paeons)
						{
							if(P != null)
								{
									P.draw();
								}
						}
				if(done)
					{
						WinScreen.setTexture(WinScreenTex[winner].getTexId());
						WinScreen.draw();
					}
			}
		//Event Handler:
		boolean oldE = false;
		boolean rolled = false;
		int lastRoll=0;
		void eventHandler()
			{
				//Sertard game
				if(done)
					{
						init(playerNu);
						done = false;
						winner = -1;
						return;
					}
				oldE = I.event;
				//Go over actives and if true execute logic:
				if(Dice.collision(I.eventX, I.eventY))
					{
						if(rolled & lastRoll!=6)
							{
								turn=(turn+1)%playerNu;
							}
						lastRoll = D.roll();
						A.play(DiceSounds[D.roll()%3]);
						rolled = true;
					}
				for(Paeon P: Paeons)
					{
						if(P != null && rolled == true && P.collision(I.eventX, I.eventY))
							{
								boolean ocupied = false;
								for(Paeon T: Paeons)
									{
										//Same color P on one spot
										if(P.position!=0 && T!= null && ((P.position+lastRoll-1)%40+1) == T.position && T.player == P.player && P.steps+lastRoll < 40 && P.exitPos < 1)
											{
												ocupied = true;
											}
										//Same colo P on start spot
										if(P.position == 0 && T != null && (P.position+(P.player*10)+7)== T.position && T.player == P.player && P.exitPos < 1)
											{
												ocupied = true;
											}
									}
								if(!ocupied)
									{
										if(P.move(lastRoll, turn, Exits))
											{
												A.play(KnockSound);
												for(Paeon T: Paeons)
													{
														if(T.player != turn && P!=T && P.position == T.position && T.exitPos == 0)
															{
																T.sendStart();
																A.play(EatSound);
															}
													}
												if(lastRoll != 6)
													{
														turn=(turn+1)%playerNu;
													}
												lastRoll = 0;
												rolled = false;
											}
									}
							}
					}
				
				//Check Win Conditions
				int stats=0;
				for(int i=0; i<playerNu; i++)
					{
						for(int j=0; j<4; j++)
							{
								if(Exits[i][j] == false)
									{
										break;
									}
								else
									{
										stats++;
									}
							}
						if(stats==4)
							{
								winner = i;
								done = true;
								A.play(Win);
								break;
							}
						stats=0;
					}
			}
		//Primarni Loop
		public void primaryGameLoop()
			{
				while(I.exit())

						{
							//Update chages
							//Clear old graphics:
							G.clear();
							//Rendering Loop Here:
							render();
							//Update Graphics:
							G.update();
							//Hvata Event:
							I.update();
							//Input hadlers Here:
							//Event semaphore
							if(I.event && !oldE)
								{
									eventHandler();
								}
							else
								{
									oldE = I.event;
								}
						}
			}
		public void run(int players)
			{
				System.out.print("Starting Dont Be Mad Man\n");
				//Initiate sub-engines:
				G.init();
				I.init(G.getWindowId());
				A.init();
				//Initiate game
				load();
				//Numer of players for testing its 2;
				init(players);
				primaryGameLoop();
				//Close sub-engines:
				A.close();
				I.close();
				G.close();
			}
	}

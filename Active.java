public class Active extends Item
	{
		Active(int TexId, float x, float y, float w, float h, float r, float g, float b)
			{
				super(TexId, x, y, w, h, r, g, b);
			}
		boolean collision(double x, double y)
			{
				if(x < this.posX+(this.width/2.0) && x > this.posX-(this.width/2.0) && y < this.posY+(this.height/2.0) && y > this.posY-(this.height/2.0))
					{
						return true;
					}
				return false;
			}
	}

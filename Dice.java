import java.util.Random;
public class Dice
	{
		Random rand = new Random();
		int roll()
			{
				return rand.nextInt(6)+1;
			}
	}

public class program
{
	public int test(char key[], char answer[])
	{
		int score = 0;

		for (int i = 0; i < key.length; i++) {
			if (answer[i] == key[i])   score += 4;
			else if (answer[i] == '?') continue;
			else                       score -= 1;
		}

		return score;
	}
}

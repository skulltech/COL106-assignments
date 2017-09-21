import java.util.Arrays;


public class program
{
	public int test(String number)
	{
		/*
		Exercise 18: Most frequent digit- Given a number, the objective is to find out
		the most frequently occuring digit in the number. If more than 2 digits have
		the same frequency, return the smallest digit. The number is input as a string
		and the output should be the digit as an integer. For e.g. if the number is
		12345121, the most frequently occuring digit is 1. If the number is 9988776655
		the output should be 5 as it is the smallest of the digits with the highest frequency.
		*/

		int[] frequency = new int[10];

		for (int i = 0; i < number.length(); i++) {
			frequency[Character.getNumericValue(number.charAt(i))]++;
		}

		int mostFreq = 0, freq = 0;

		for (int i = 9; i > -1; i--) {
			if (frequency[i] >= freq) {
				mostFreq = i;
				freq = frequency[i];
			}
		}

		return mostFreq;
	}
}

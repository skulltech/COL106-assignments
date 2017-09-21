public class program
{
	public String test(String hex)
	{
		/*
		Exercise 16: Hex to binary- Given a string representing a number in hexadecimal
		format, convert it into its equivalent binary string. For e.g. if the input if "1F1"
		then its binary equivalent is "111110001". If the input is "13AFFFF", the output
		should be "1001110101111111111111111".
		*/
		String binary = new String();
		for (int i = 0; i < hex.length(); i++) {
			binary = binary + bin(hex.charAt(i));
		}

		int initialzeroes = 0;
		for (int i = 0; i < 3; i++) {
			if (binary.charAt(i) == '0') initialzeroes++;
		}
 
		return binary.substring(initialzeroes);
	}

	private static String bin(char hex) {
		String bin = new String();
		
		switch (hex) {
			case '0':
				bin = "0000";
				break;
			case '1':
				bin = "0001";
				break;
			case '2':
				bin = "0010";
				break;
			case '3':
				bin = "0011";
				break;
			case '4':
				bin = "0100";
				break;
			case '5':
				bin = "0101";
				break;
			case '6':
				bin = "0110";
				break;
			case '7':
				bin = "0111";
				break;
			case '8':
				bin = "1000";
				break;
			case '9':
				bin = "1001";
				break;
			case 'A':
				bin = "1010";
				break;
			case 'B':
				bin = "1011";
				break;
			case 'C':
				bin = "1100";
				break;
			case 'D':
				bin = "1101";
				break;
			case 'E':
				bin = "1110";
				break;
			case 'F':
				bin = "1111";
				break;
		}

		return bin;
	}
}

public class program
{
	public int[][] test(int M1[][], int M2[][])
	{
		/*
		Exercise 19: Matrix addition- Given two matrices M1 and M2, the objective to
		add them. Each matrix is provided as an int[][], a 2 dimensional integer array.
		The expected output is also 2 dimensional integer array.
		*/

		int height = M1.length, width = M1[0].length; 
		int addition[][]= new int[height][width];
		
		for (int i = 0; i < height; i ++) {
			for (int j = 0; j < width; j++) {
				addition[i][j] = M1[i][j] + M2[i][j];
			}
		}

		return addition;
	}
}

package br.puc_rio.data_science.svd;

import java.awt.image.BufferedImage;

public class Image_to_Matrix {
	
	public int[][] getMatrix(BufferedImage image){// transforma a imagem em uma matriz
		int image_width = image.getWidth();
	    int image_height = image.getHeight();
		int[][] A = new int[image_width][image_height];
		
		for(int x=0; x< image_width;x++) {
			for(int y=0; y< image_height;y++) 
				A[x][y] = image.getRGB(x, y);			
		}		
		return A;
	}
	
	public void print_matrix(int [][] matrix, int w, int h) {// imprime uma dada matriz
		
		for(int x=0; x< w;x++) {
			for(int y=0; y< h;y++) 
			System.out.print(matrix[x][y]+" ");
		System.out.println();
		}		
	}
	
	public int[][] getMatrixTransposed(int[][] A, int w, int h){// retorna a matrix transposta
		 int [][] At = new int[h][w];
	
		for(int x=0; x< w;x++) {
			for(int y=0; y< h;y++) 				
				At[y][x] = A[x][y];				
	    }
		return At;
	}
	
	public int[][] multiply_matrices(int[][] A, int [][] At){
		int [][] U = new int[A.length][A.length];
		System.out.println(A.length +" "+ At.length);
				
		for( int x=0; x< A.length;x++) {
			U[x][x] = 0;
			for(int y=0; y< A.length;y++) {				
				int element = 0;
				for(int z=0; z< At.length;z++) {
					element = element + A[x][z] * At[z][y];	
					System.out.println("x:"+ x+" z:"+z+" y:"+y);
				}	
				U[x][y] = element;			
		   }		
	    }
		return U;	
	}
}

package br.puc_rio.data_science.svd;
import java.awt.image.BufferedImage;

public class ImageToMatrix {
	
	public double[][] imageToMatrix(BufferedImage image){// Dada uma imagem ele a transforma em uma matriz 
		int image_width = image.getWidth();// Pega a largura da imagem
	    int image_height = image.getHeight();// Pega a altura da imagem
		double[][] A = new double[image_width][image_height];
		
		for(int x=0; x< image_width;x++) {//gera a matriz
			for(int y=0; y< image_height;y++) 
				A[x][y] = image.getRGB(x, y);			
		}		
		return A;
	}
	
	public void printMatrix(double [][] matrix, int w, int h) {// imprime uma dada matriz
		
		for(int x=0; x< w;x++) {
			for(int y=0; y< h;y++) 
			System.out.print(matrix[x][y]+" ");
		System.out.println();
		}		
	}	
}

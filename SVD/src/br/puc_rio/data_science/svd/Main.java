package br.puc_rio.data_science.svd;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		
		Image_to_Matrix image_to_Matrix = new Image_to_Matrix();
		BufferedImage image = null;
		File arq = new File("images/image1.jpg");
		try {
			image = ImageIO.read(arq);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(image.getWidth()+" "+ image.getHeight());
		int [][] A = image_to_Matrix.getMatrix(image);// matriz original da imagem
		int[][] At = image_to_Matrix.getMatrixTransposed
				(A, image.getWidth(), image.getHeight());// matriz transposta da imagem	
		int [][] U = image_to_Matrix.multiplyMatrices(A, At);
		image_to_Matrix.printMatrix(U, image.getWidth(), image.getWidth());//imprime a multiplicação das matrizes
		
		
		
		
		
	
	

}}

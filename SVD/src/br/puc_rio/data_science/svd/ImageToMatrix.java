package br.puc_rio.data_science.svd;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class ImageToMatrix {
	
	public double[][] imageToMatrix(BufferedImage image){// Dada uma imagem ele a transforma em uma matriz 
		int image_width = image.getWidth();// Pega a largura da imagem
	    int image_height = image.getHeight();// Pega a altura da imagem
		double[][] A = new double[image_width][image_height];
		
		
		for(int x=0; x< image_width;x++) {//gera a matriz
			for(int y=0; y< image_height;y++) {
				A[x][y] = image.getRGB(x, y);	
			     System.out.println(A[x][y]);}
		}		
		return A;
	}
	
	public int[] matrixToVector(double[][] matrix, int w, int h) {
		int[] vector = new int[w*h];
		int d = 0;
		for(int x=0; x<w;x++) {//gera a matriz
			for(int y=0; y<h;y++) {
				vector[d] = (int) matrix[x][y];
				
				d++;}
			    
			}
		return vector;
	}
	public void printMatrix(double [][] matrix, int w, int h) {// imprime uma dada matriz
		
		for(int x=0; x< w;x++) {
			for(int y=0; y< h;y++) 
			System.out.print(matrix[x][y]+" ");
		System.out.println();
		}		
	}	
	
	public BufferedImage getImage(double[] [] matrix, int w, int h) {
		/*
		final BufferedImage image =
			    new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			
		           
		            image.setRGB(0,0,w,h,matrix, 0, w);   */
		
		final BufferedImage image =  new BufferedImage(w, h,BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = image.getRaster();
		for (int i = 0; i < w; i++) {
		for (int j = 0; j < h; j++) {
		raster.setSample(i, j,image.getSampleModel().getDataType(), matrix[i][j]);
		image.setData(raster);
		
		
		}
		}
		
		

			
		return image;
	}}
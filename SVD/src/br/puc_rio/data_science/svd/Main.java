package br.puc_rio.data_science.svd;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Jama.SingularValueDecomposition;
import Jama.Matrix;

public class Main {

	public static void main(String[] args) {
		
		/* Pega uma imagem qualquer e transforma em uma matriz de pixels*/
	    ImageToMatrix imageToMatrix = new ImageToMatrix();
	   	BufferedImage image = null;
		File arq = new File("images/image.jpg");
		try {
			image = ImageIO.read(arq);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Matrix A = new Matrix(imageToMatrix.imageToMatrix(image));// matriz gerada
		
		/* Decomposição da matriz da imagem nas suas componentes do SVD*/		
		 SingularValueDecomposition SVD = A.svd();
		 Matrix U = SVD.getU();
		 Matrix S = SVD.getS();
		 Matrix V = SVD.getV();
		imageToMatrix.printMatrix(U.getArray(),image.getWidth(), image.getWidth());
		imageToMatrix.printMatrix(S.getArray(),image.getHeight(), image.getHeight());
		imageToMatrix.printMatrix(V.getArray(),image.getHeight(), image.getHeight());
   } 
}

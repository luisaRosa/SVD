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
		File arq = new File("images/PB.jpg");
		try {
			image = ImageIO.read(arq);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Matrix A = new Matrix(imageToMatrix.imageToMatrix(image));// matriz gerada
		System.out.println("linha="+A.getRowDimension()+" coluna ="+ A.getColumnDimension());
		
	/* Decomposição da matriz da imagem nas suas componentes do SVD*/		
		 SingularValueDecomposition SVD = A.svd();
		 Matrix U = SVD.getU();
		 Matrix S = SVD.getS();
		 Matrix V = SVD.getV();
		 System.out.println("linha="+U.getRowDimension()+" coluna ="+ U.getColumnDimension());
		 System.out.println("linha="+S.getRowDimension()+" coluna ="+ S.getColumnDimension());
		 
	/* porcentagens das matrizes U, V e S*/		
		 Matrix U1= U.getMatrix(0, U.getRowDimension()-1, 0, (int)(U.getColumnDimension()*0.5));				
		 Matrix S1= S.getMatrix(0, (int)(S.getRowDimension()*0.5), 0, (int)(S.getColumnDimension()*0.5));			
		 Matrix V1= V.getMatrix(0, (int)(V.getRowDimension()*0.5), 0, V.getColumnDimension()-1);	
		 
		 System.out.println("linha="+U1.getRowDimension()+" coluna ="+ U1.getColumnDimension());
		 System.out.println("linha="+S1.getRowDimension()+" coluna ="+ S1.getColumnDimension());
		 
		 Matrix US = U1.times(S1);
		 Matrix A1 = US.times(V1);
		 System.out.println("linha="+A1.getRowDimension()+" coluna ="+ A1.getColumnDimension());
		 int [] v = imageToMatrix.matrixToVector(A1.getArray(), A1.getRowDimension(), A1.getColumnDimension());
		BufferedImage image1 = imageToMatrix.getImage(A1.getArray(), A1.getRowDimension(), A1.getColumnDimension()) ;
		try {
			ImageIO.write(image1, "JPG", new File("images/gato54.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
   } 
}

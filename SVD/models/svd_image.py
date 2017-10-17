
import numpy as np
import time
import scipy.misc
from PIL import Image
from numpy.linalg import svd


class SVDImage:
    def __init__(self):
        pass


    def image_to_matrix(self):

        image = np.array(Image.open("images/image.jpg"))

        image = image/125
        row, col, _ = image.shape

        print  'pixels: ', row, '*',col
        image_red = image[:,:,0]
        image_green = image[:,:,1]
        image_blue = image[:,:,2]
        original_bytes = image.nbytes
        print  'the space needed(in bytes) to store this image is ', original_bytes

        U_r, S_r, V_r = np.linalg.svd(image_red, full_matrices=True)
        U_g, S_g, V_g = np.linalg.svd(image_green, full_matrices=True)
        U_b, S_b, V_b = np.linalg.svd(image_blue, full_matrices=True)

        bytes_to_stored = sum([matrix.nbytes for matrix in [U_r, S_r, V_r, U_g, S_g, V_g, U_b, S_b, V_b]])
        print "the matrices that we stored have a  total size (in bytes):" ,bytes_to_stored

        k = int(col*0.5)


        U_r_k = U_r[:, 0:k]
        V_r_k = V_r[0:k,:]
        U_g_k = U_g[:,0:k]
        V_g_k = V_g[0:k,:]
        U_b_k = U_b[:,0:k]
        V_b_k = V_b[0:k,:]

        S_r_k = S_r[0:k]
        S_g_k = S_g[0:k]
        S_b_k = S_b[0:k]

        compressed_bytes = sum([matrix.nbytes for matrix in [U_r_k, S_r_k, V_r_k, U_g_k, S_g_k, V_g_k, U_b_k, S_b_k, V_b_k]])
        print 'the compressed matrices that we store now have total size (in bytes):' ,compressed_bytes

        image_red_approx = np.dot(U_r_k, np.dot(np.diag(S_r_k), V_r_k))
        image_green_approx = np.dot(U_g_k, np.dot(np.diag(S_g_k), V_g_k))
        image_blue_approx = np.dot(U_b_k, np.dot(np.diag(S_b_k), V_b_k))

        image_reconstructed = np.zeros((row, col, 3))

        image_reconstructed[:,:,0] = image_red_approx
        image_reconstructed[:, :, 1] = image_green_approx
        image_reconstructed[:, :, 2] = image_blue_approx

        image_reconstructed[image_reconstructed < 0] = 0.12
        #image_reconstructed[image_reconstructed == 0] = 0.12
        image_reconstructed[image_reconstructed > 1] = 0.88

        scipy.misc.imsave('result/image_2_K10.jpg', image_reconstructed)
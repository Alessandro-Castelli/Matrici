package MainMatrix;

import Matrice.Matrix;
import EccezioniMatrici.*;
import Matrice.Tipo;

public class Main {
    public static void main(String[] args) {

        float[] arr = {2f, 3f, 4f};
        Matrix m1 = new Matrix(5, 5, arr);

        float[] arr2 = {3f, 4f, 5f};
        Matrix m2 = new Matrix(5, 5, arr2);

        Matrix.toString(m1);
        System.out.printf("\n \n \n");
        Matrix.toString(m2);
        System.out.printf("\n \n \n");

        try {
            Matrix m3 = Matrix.prodottoMatriciale(m1, m2);
            Matrix.toString(m3);
        } catch (MatrixException e){
            System.out.printf("Impossibile eseguire il prodotto matriciale");
        }



    }
}
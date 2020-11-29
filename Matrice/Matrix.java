package Matrice;
import EccezioniMatrici.*;


public class Matrix {

/**Matrix.Matrix is mutable
 *
 *  Class overview:
 *
 *  Public Matrix.Matrix(int n, int m)
 *  Public Matrix.Matrix(int n, int m, float arr[])
 *  public void insert(int i, int j, float f) throws IndexException
 *  public static void toString(Matrix matrix)
 *  public void scalarProduct(float x)
 *  public Matrix add( Matrix m2) throws MatrixException
 *  public Matrix transpose()
 *  public float[] extractVector(Tipo tipo, int i) throws ExtractVectorException
 *
 */
    /**
     *INVARIANTE : matrice != NULL
     */

    private float matrice[][];
    private int numRighe;
    private int numColonne;


   /** Matrix
    * Costruttore: crea una nuova matrice di n righe x n colonne e la inizializza con 0
    *
    * Parametro n: intero non negativo, solleva un eccezione IndexException se n<0
    * Parametro m: intero non negativo, solleva un eccezione IndexException se n<0
    *
    */
    public Matrix(int n, int m) {
       assert (n>=0 && m>=0);
       this.matrice = new float[n][m];
       for (int i=0; i<n; i++){
           for (int j=0; j<m;j++ ){
               matrice[i][j]= 0;
           }
           this.numRighe=n;
           this.numColonne=m;
       }
    }//Matrice.Matrix


    /**  Matrix
     * Costruttore: crea una nuova Matricee di n righe x m colonne, riempie la matrice per righe con l'array arr[] ripetendo i dati
     * paramentro n: intero positivo, solleva un eccezione Index Exception se vengono inseriti dei numeri negativi
     * paramentro m: intero positivo, solleva un eccezione Index Exception se vengono inseriti dei numeri negativi
     * parametro arr: arr di lunghezza l, se l>m solleva LenghtException, se l<m riempie la riga con 0
     * arr solleva una LenghtException se l'array è più lungo della riga, se è piu corto riempie gli elementi restanti con 0
     */
    public Matrix(int n, int m, float[] arr){

        assert (n>=0 && m>=0);

        this.matrice = new float[n][m];

        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                if(j < arr.length) {
                    matrice[i][j] = arr[j];
                } else {
                    matrice[i][j] = 0;
                }
            } }
        this.numRighe = n;
        this.numColonne = m;
    }


    /**  insert
     * Effetto: Modifica this, inserisce float f all'interno della matrice alle posizione riga i, colonna j
     * parametro i: intero non negativo,indica la riga, se viene inserito un negativo solleva un'eccezione IndexException
     * parametro i: intero non negativo,indica la colonna, se viene inserito un negativo solleva un'eccezione IndexException
     * paramentro f: numero da inserire nella matrice, va bene qualsiasi float
     *
     */
    public void insert(int i, int j, float f) throws IndexException  {
        if(i<0 || j<0){
            throw  new IndexException("Errore lunghezza matrice");
        }

        assert (i>=0||j>=0);

        this.matrice[i][j] = f;
    }


    /**  toString
     * Effetto: void restituisce un visualizzazione dell'oggetto matrice
     * parametro matrix: una qualsiasi matrice valida
     */
    public static void toString(Matrix matrix){
        for (int i = 0; i < matrix.numRighe; i++) {
            for (int j = 0; j < matrix.numColonne; j++) {
                System.out.printf("%f ", matrix.matrice[i][j]);
            }
                System.out.printf("\n");
        }
    }


    /**  scalarProduct
    * EFFETTO : Modifica this, moltiplica la matrice matrix per lo scalare x;
    * PARAMETRO x : numero reale
    * PARAMETRO matrix : Una qualsiasi matrice
    */
    public void scalarProduct(float x){
        int n = this.numRighe;
        int m = this.numColonne;

        for (int i = 0; i < n; i++){
            for (int j=0; j < m; j++){
                this.matrice[i][j] = this.matrice[i][j] * x;
            }
        }
    }


    /** add
     * EFFETTO: crea una nuova matrice m3 che è la somma di m1 e m2
     * RETURN: new matrix che ha le stesse dimensioni di m2 e di this e che come valori ha la somma matriciale delle matrici
     * PARAMETRO m2 : matrice
     * THROW MatrixException se il numero di righe e colonne di this è diverso da quello di m2
     */
    public Matrix add( Matrix m2) throws MatrixException{
        if(this.numRighe != m2.numRighe || this.numColonne != m2.numColonne){
            throw  new MatrixException("Le matrici non hanno la stessa dimensione");
        }


          Matrix m3 = new Matrix(this.numRighe, this.numColonne);



        for (int i = 0; i < this.numRighe; i++){
            for (int j = 0; j < m2.numColonne; j++){
                m3.matrice[i][j] = this.matrice[i][j] + m2.matrice[i][j];
            }
        }

        return m3;
    }


    /**  transpose
     *  Effect: calcola la trasposta di  una matrice, non modifica this
     *  return : new matrix che è la trasposta di this, non modifico this
     */
    public Matrix transpose(){
        Matrix m1 = new Matrix(this.numColonne, this.numRighe);
        for (int i = 0; i<this.numColonne; i++){
            for (int j = 0; j<this.numRighe; j++){
                m1.matrice[i][j]= this.matrice[j][i];
            }
        }
        return m1;
    }

    /** extractVector
     * Effetto: restituisci una ruga o una colonna della matrice, non modifica this
     * return: restituisce un array con la riga o la colonna desiderata
     * parametro tipo: puo essere ROW o COL
     * paramentro: il numero di riga/colonna che si desidera estrarre
     * Throw ExtractVectorException: Crea un'eccezione se si inserisce qualcos'altro che non sia ROW o COL
     */
    public float[] extractVector(Tipo tipo, int i) throws ExtractVectorException{

        switch (tipo){
            case COL:
                float[] arr = new float[this.numRighe];
                for( int j = 0; j<this.numRighe; j++) {
                    arr[j] = this.matrice[j][i];
                }
                return arr;

            case ROW:
                float[] arr1 = new float[this.numRighe];
                for( int j = 0; j<this.numRighe; j++) {
                    arr1[j] = this.matrice[i][j];
                }
                return arr1;

                }
        throw new ExtractVectorException("Errore estrazione riga/colonna");

        }

/** prodottoMatriciale
* Effect : fa il prodotto mattricile tra due matrici
 * Return : ritorna una nuovo matrice che contiene il prodotto matriciale
 * PARAMETRI m2 : Una matrice di dimensione n*m
 * PARAMETRO m2 : una matrice di dimensione m*p
 * Throe matricException se con le matrici inserite non si può svolgere un prodotto matriciale
 */
    public static Matrix prodottoMatriciale(Matrix m1, Matrix m2) throws MatrixException {
        if (m1.numColonne != m2.numRighe) {
            throw new MatrixException("Non è possibile svolgere la moltiplicazione tra matrici");
        }
        assert (m1.numColonne != m2.numRighe);

        Matrix m3 = new Matrix(m1.numRighe, m2.numColonne);

        for (int i = 0; i < m1.numRighe; i++) {
            for (int j = 0; j < m2.numColonne; j++) {
                try {
                    float arr1[] = m1.extractVector(Tipo.ROW, i);
                    float arr2[] = m2.extractVector(Tipo.COL, j);
                    float number = sum(arr1, arr2);
                    m3.insert(i, j, number);
                } catch (ExtractVectorException e) {
                    System.out.printf("Impossibile estrarre il vettore");
                } catch (IndexException e) {
                    System.out.printf("Index Exception");
                }
            }
        }
        return  m3;
    }

 /** sum
  *  Effetto: dati due vettori multiplia Arr[i]*Arr1[i] e lo somma con il successivo finoo a fine arrai per 0 <= i <= arr.length
  *  return: creau un vettore che ha le caratterisdtiche indicate sopra
  *  Parametro arr1: un vettore di float non nullo
  *  Parametro arr2: un vettore di float non nullo
  */
    private static float sum(float[] arr1, float[] arr2){
        float n = 0;
        assert (arr1.length == arr2.length);

        for (int i = 0; i<arr1.length; i++){
            n = arr1[i]*arr2[i]+n;
        }
        return n;
    }


}// class Matrix

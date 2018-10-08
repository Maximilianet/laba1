import java.util.Arrays;

public class Main {
    static double[] temparray;
    static double min;
    static double max;

    public static void main(String[] args) {
        double[] vector = {74,93,100,71,91,95};
        methodOne(vector);
//        methodTwo(vector);
//        methodThree(vector);

        linNorm0_1(vector);
//        linNorm1_1(vector);
//        neLinSigmNorm0_1(vector, 0.2);
//        neLinSigmNorm1_1(vector, 0.2);

        linDenorm0_1();
//        linDenorm1_1(vector);
//        SigmoidDenom0_1(0.2);
//        SigmoidDenom1_1(0.2);
    }

//     денормализация в пределах от [0:1]
    static void linDenorm0_1(){
        double [] resultDenomnalizeData  = new double[temparray.length];
        for (int i = 0; i < temparray.length; i++) {
            resultDenomnalizeData[i] = min + temparray[i] * (max - min);
        }
        System.out.println(Arrays.toString(resultDenomnalizeData));
    }

    // сигмойдная денормализация в пределах от [0:1]
    public static void SigmoidDenom0_1(double alfa){
        double [] resultDenomnalizeData  = new double[temparray.length];
        for (int i = 0; i < temparray.length; i++) {
            resultDenomnalizeData[i] = ((min+max)/2) - (1/alfa) * Math.log((1/temparray[i]) - 1 );
        }
        System.out.println(Arrays.toString(resultDenomnalizeData));
    }
    // сигмойдная денормализация в пределах от [-1:1]
    public static void SigmoidDenom1_1(double alfa){
        double [] resultDenomnalizeData  = new double[temparray.length];
        for (int i = 0; i < temparray.length; i++) {
            resultDenomnalizeData[i] = ((min+max)/2) - (1/alfa) * Math.log((1 - temparray[i])/(1 + temparray[i]));
        }
        System.out.println(Arrays.toString(resultDenomnalizeData));
    }

    // нелинейная нормализация сигмойдной функцией в пределах от [-1:1]
    public static void neLinSigmNorm1_1(double[] array, double alfa){
        double resultMas[] = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            resultMas[i] = (Math.exp(alfa*(array[i] - ((min+max)/2))) - 1)/(Math.exp(alfa*(array[i] - ((min+max)/2))) + 1);
        }
        temparray = resultMas;
        System.out.println(Arrays.toString(resultMas));
    }
    // нелинейная нормализация сигмойдной функцией в пределах от [0:1]
    public static void neLinSigmNorm0_1(double[] array, double alfa){
        double resultMas[] = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            resultMas[i] = 1/(Math.exp(-alfa * (array[i] - ((min+max)/2))) + 1);
        }
        temparray = resultMas;
        System.out.println(Arrays.toString(resultMas));
    }

    // линейная нормализация в пределах [0:1]
    static void linNorm0_1(double[] array){
        double result[] = new double[array.length];
        for (int i = 0; i < array.length ; i++) {
            result[i] = (array[i] - min)/(max - min);
        }
        temparray = result;
        System.out.println(Arrays.toString(result));
    }

    // денормализация в пределах от [-1:1]
    public static double[] linDenorm1_1(double[] array){

        double [] resultDenomnalizeData  = new double[temparray.length];
        for (int i = 0; i < temparray.length; i++) {
            resultDenomnalizeData[i] = min + (temparray[i] + 1) * (max - min) / 2;
        }
        return resultDenomnalizeData;
    }

    // линейная нормализация в пределах [-1:1]
    static void linNorm1_1(double[] array){
        double result[] = new double[array.length];
        for (int i = 0; i < array.length ; i++) {
            result[i] = 2 * ((array[i] - min)/max - min) - 1;
        }
        temparray = result;
        System.out.println(Arrays.toString(result));
    }

    // метод нахождения минимального и максимального значения из текущего ветора
    static void methodOne(double[] array){
        max = getMaximum(array);
        min = getMinimum(array);
        System.out.println("Минимум: " + min + "   " + "Максимум: " + max);
    }

    // метод установки вручную данных из теоретичски возможных
    static void methodTwo(double[] array){
        max = getMaximum(array);
        min = getMinimum(array);
        System.out.println("Минимум: 71.0   Максимум: 100.0");
    }

    // метод выборки средних размеров , расширение
    static void methodThree(double[] array){
        min = getMinimum(array);
        max = getMaximum(array);
        double newMin = min - 0.1 * (max - min);
        double newMax = max + 0.1 * (max - min);
        min = newMin;
        max = newMax;
        System.out.println("Минимум: " + min + "   " + "Максимум: " + max);
    }

    static double getMaximum(double[] array){
        double max = array[0];
        for (int i = 0; i < array.length; i++)
            if (max < array[i])
                max = array[i];
        return max;
    }

    static double getMinimum(double[] array) {
        double min = array[0];
        for (int i = 0; i < array.length; i++)
            if (min > array[i])
                min = array[i];
        return min;
    }
}

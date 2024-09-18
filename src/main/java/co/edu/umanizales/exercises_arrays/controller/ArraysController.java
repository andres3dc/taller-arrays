package co.edu.umanizales.exercises_arrays.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/arrays")
public class ArraysController {
    @PostMapping("/exercise-1")
    public int exerciseOne(@RequestBody int[] array){
        return getPositionLargest(array);
    }
    @PostMapping("/exercise-2")
    public String exerciseTwo(@RequestBody int[] array){
        return getPositionLargestPrime(array);
    }
    @GetMapping("/exercise-3")
    public int[] exerciseThree(){
        return findPrimesInRange(100, 300);
    }
    @PostMapping("/exercise-4")
    public int[] exerciseFour(@RequestBody int[] array){
        return findNumberEndFour(array);
    }
    @PostMapping("/exercise-5")
    public int exerciseFive(@RequestBody int[] array){
        return countLargestNumber(array);
    }
    @PostMapping("/exercise-6")
    public String exerciseSix(@RequestBody int[] array) {
        int average = calculateAverageOfArray(array);
        return findElementOnArray(array, average) ? "El promedio: " + average + " si esta entre los datos:" : "El promedio: " + average + "  no esta entre los datos";
    }
    @PostMapping("/exercise-7")
    public int exerciseSeven(@RequestBody int[] array) {
        return getPositionSumDigitsLargest(array);
    }
    @PostMapping("/exercise-8")
    public String exerciseEight(@RequestBody int[] array) {
        return printNumberWithFactorial(array);
    }
    @PostMapping("/exercise-9")
    public String exerciseNine(@RequestBody int[] array) {
        return printRangeOfNumbers(array);
    }

    private boolean isPrime(int number){
        int contDiv = 0;
        int half = number / 2;
        for(int i = 2 ; i < half; i++){
            if(number % i == 0){
                contDiv++;
            }
        }
        if(contDiv == 0){
            return true;
        }
        else{
            return false;
        }
    }

    private int sumArray(int[] array){
        int sum = 0;
        for(int i=0; i<array.length; i++){
            sum += array[i];
        }
        return sum;
    }

    private int getPositionLargest(int[] array){
        int max = array[0];
        int position = 0;
        for(int i=1; i<array.length; i++){
            if(array[i] > max){
                max = array[i];
                position = i;
            }
        }
        return position;
    }

    private String getPositionLargestPrime(int[] array){
        int largestPrime = 0;
        int position = 0;
        System.out.println(isPrime(8));

        for(int i=0; i<array.length; i++){
            if(isPrime(array[i]) && array[i] > largestPrime){
                largestPrime = array[i];
                position = i;
            }
        }
        if(largestPrime == 0) {
            return "ninguno de los numeros ingresados es primo";
        }
        return  Integer.toString(position);
    }

    private int[] findPrimesInRange(int initialPosition, int finalPosition) {
        int[] primes = new int[10];
        int count = 0;

        for(int i=initialPosition; i<=finalPosition; i++){
            if(isPrime(i) && count < 10){
                primes[count] = i;
                count = count + 1;
            }
        }
        return primes;
    }

    private boolean validateNumberEnd(int number, int numberEnd) {
        return number % 10 == numberEnd;
    }

    private int[] findNumberEndFour(int[] array) {
        List<Integer> endsNumbers = new ArrayList<>();

        for(int i=0; i<array.length; i++){
            if(validateNumberEnd(array[i], 4)){
                endsNumbers.add(i);
            }
        }
        return endsNumbers.stream().mapToInt(Integer::intValue).toArray();
    }

    private int countLargestNumber(int[] array) {
        int positionLargest = getPositionLargest(array);
        int count = 0;

        for(int i = 0; i < array.length; i++) {
            if (array[i] == array[positionLargest]) {
                count = count + 1;
            }
        }
        return count;
    }


    private int calculateAverageOfArray(int[] array) {
        int arrayLength = array.length;
        int sum = sumArray(array);
        return sum / arrayLength;
    }

    private boolean findElementOnArray(int[] array, int element) {
        boolean flag = false;
        for(int i = 0; i < array.length; i++) {
            if (array[i] == element) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private int sumDigits(int number) {
        String numberToString = String.valueOf(number);
        int sum = 0;
        for (int i = 0; i < numberToString.length(); i++) {
            sum += Character.getNumericValue(numberToString.charAt(i));
        }
        return sum;
    }

    private int getPositionSumDigitsLargest(int[] array) {
        int largest = sumDigits(array[0]);
        int position = 0;
        for(int i=1; i<array.length; i++){
            if(sumDigits(array[i]) > largest){
                largest = sumDigits(array[i]);
                position = i;
            }
        }
        return position;
    }

    private int calculateFactorial(int number) {
        int factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial = factorial * i;
        }
        return factorial;
    }

    private int[] setFactorialToNewArray(int[] array) {
        List<Integer> factorialNumbers = new ArrayList<>();
        for(int i=0; i <array.length; i++) {
            factorialNumbers.add(calculateFactorial(array[i]));
        }
        return factorialNumbers.stream().mapToInt(Integer::intValue).toArray();
    }

    private String printNumberWithFactorial(int[] array) {
        StringBuilder text = new StringBuilder();
        int[] factorialArray = setFactorialToNewArray(array);
        for(int i=0; i<array.length; i++) {
            text.append("El factorial de: ").append(array[i]).append(" es ").append(factorialArray[i]).append("\n");
        }
        return text.toString();
    }

    private String printNumbersInRanger(int start, int end) {
        StringBuilder text = new StringBuilder();
        for (int i = start; i <= end; i++) {
            text.append(i).append(",");
        }
        return text.toString();
    }

    private String printRangeOfNumbers(int[] array) {
        StringBuilder text = new StringBuilder();
        for(int i=0; i<array.length; i++) {
            text.append("Los enteros comprendidos desde 1 hasta ").append(array[i]).append(" son: ").append(printNumbersInRanger(1, array[i])).append("\n");
        }
        return text.toString();
    }
}
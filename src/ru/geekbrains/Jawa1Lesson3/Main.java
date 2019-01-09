package ru.geekbrains.Jawa1Lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        task2();
    }

    //2 * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
// "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
// "pineapple", "pumpkin", "potato"};
//
//При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
//сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
//apple – загаданное
//apricot - ответ игрока
//ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
//Для сравнения двух слов посимвольно, можно пользоваться:
//String str = "apple";
//str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
//Играем до тех пор, пока игрок не отгадает слово
//Используем только маленькие буквы
    private static void task2() {
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
                "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper",
                "pineapple", "pumpkin", "potato"};


        String finishWord = takeRandomWord(words);
        /// for debug
        System.out.println(finishWord);

        System.out.println("Угадайте слово из массив: " + Arrays.toString(words));

        String input_string = "";

        while (input_string != finishWord) {
            if (input_string != "") {
                System.out.println("Попробуйте еще раз или введите \"exit\" для выхода");
            }
            input_string = getStringFromUser();
            if (input_string == "exit") {
                System.out.println("Загаданное слово: " + finishWord);
                break;
            }
            String res = verifyInputAndWord(input_string, finishWord);

            System.out.println(res);
        }
    }

    private static String verifyInputAndWord(String inputString, String finishWord) {
        char[] arrayOfInputString = inputString.toCharArray();
        char[] arrayOfFinishWord = finishWord.toCharArray();
        String outPutSting = "";

        int minWord = 0;
        if (arrayOfInputString.length < arrayOfFinishWord.length) {
            minWord = arrayOfInputString.length;
        } else {
            minWord = arrayOfFinishWord.length;
        }

        for (int i = 0; i < minWord; i++) {

            if (arrayOfInputString[i] == arrayOfFinishWord[i]) {
                outPutSting += arrayOfInputString[i];
            } else {
                outPutSting += "*";
            }

        }

        outPutSting = addSymvols(outPutSting, "*", 15);


        return outPutSting;
    }

    private static String addSymvols(String outPutSting, String sChar, int i) {
        while (outPutSting.length() < i) {
            outPutSting += sChar;
        }
        return outPutSting;
    }

    private static String takeRandomWord(String[] words) {
        int randomNum = (int) (Math.random() * (words.length - 1));
        return words[randomNum];
    }

    private static String getStringFromUser() {
        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        String letter = "qwertyuiopasdfghjklzxcvbnm";
        boolean endDo = true;

        do {
            if (endDo) {
                System.out.println("Ввыедите предполагеммое слово или можно только букву:");
            } else {
                System.out.println("Вы введи недопустимый символ, повторите попытку:");
            }
          //  try {
                inputString = scanner.nextLine();
           // } catch (java.util.NoSuchElementException ex) {
           //     scanner.nextLine();
           //    continue;
           // }
            endDo = true;
            for (char ch : inputString.toCharArray()) {
                if (letter.indexOf(ch) < 0) {
                    endDo = false;
                    break;
                }
            }
        } while (!endDo);
        scanner.close();
        return inputString;
    }
}


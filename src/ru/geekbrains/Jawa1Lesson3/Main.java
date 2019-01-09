package ru.geekbrains.Jawa1Lesson3;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        task2();
        scanner.close();
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

        String inputString = "";

        do {
            if (inputString != "") {
                System.out.println("Попробуйте еще раз или введите \"exit\" для выхода");
            }
            inputString = getStringFromUser();
            if (inputString.equals("exit")) {
                System.out.println("Загаданное слово: " + finishWord);
                break;
            }
            String res = verifyInputAndWord(inputString, finishWord);
            if (!inputString.equals(finishWord)) {
                System.out.println(res);
            }
        } while (!inputString.equals(finishWord));

        if (inputString.equals(finishWord)) {
            System.out.println("Правильно! Слово " + finishWord);
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
        /// мне надо инициализировать inputString именно тут, потому что если в трае то ошибка видимости.
        String inputString = "";
        String letter = "qwertyuiopasdfghjklzxcvbnm";
        boolean endDo = true;

        do {
            ///Выввожу сообщение пользователю в зависимсоти первый раз зашли в метод или нет.
            if (endDo) {
                System.out.println("Ввыедите предполагеммое слово или можно только букву:");
            } else {
                System.out.println("Вы введи недопустимый символ, повторите попытку:");
            }
            //// получаю строку от пользователя ( проверяю чтоб не было всяких исключений тип ESC
            // + scanner.nextLine() есть косяк что он второй раз читает /n от первого ввода.
            // поэтому делаю пустой scanner.nextLine(); на всяк случай (в инетах понахватался)
            try {
                inputString = scanner.nextLine();
            } catch (java.util.NoSuchElementException ex) {
                scanner.nextLine();
                continue;
            }
            ///Сбрасываю endDo на значение по дефолту
            endDo = true;
            ///Захожу в цикл проверки и смотрю, чтоб все символы что ввел пользователь присутствовали и в строке  "qwertyuiopasdfghjklzxcvbnm".
            // Если что лишнее в начало цикла.
            for (char ch : inputString.toCharArray()) {
                if (letter.indexOf(ch) < 0) {
                    endDo = false;
                    break;
                }
            }
        } while (!endDo);

        return inputString;
    }
}


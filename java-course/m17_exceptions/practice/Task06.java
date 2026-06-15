package m17_exceptions.practice;

/**
 * Задача 06 — Модуль 17: Собственное исключение
 *
 * ЗАДАНИЕ:
 *   Создайте собственный класс исключения InvalidPasswordException
 *   (наследник RuntimeException). Напишите метод validatePassword(String),
 *   который бросает это исключение, если пароль короче 8 символов или
 *   не содержит хотя бы одной цифры. В main проверьте несколько паролей.
 *
 * ПРИМЕРЫ:
 *   "abc"        → InvalidPasswordException: "Пароль слишком короткий"
 *   "password"   → InvalidPasswordException: "Пароль должен содержать цифру"
 *   "pass1234"   → "Пароль принят"
 *
 * ПОДСКАЗКА:
 *   class InvalidPasswordException extends RuntimeException {
 *       InvalidPasswordException(String msg) { super(msg); }
 *   }
 */
public class Task06 {
    public static void main(String[] args){
        // Проверьте несколько паролей через validatePassword в try-catch
        validatePassword("abc");
        validatePassword("pass1234");
        validatePassword("password");
    }

    // TODO: метод validatePassword(String password)
    private static void validatePassword(String password){
        if (password.length() < 8 || containsDigit(password)){
            throw new InvalidPasswordException("Your passowrd is bad");
        }else{
            System.out.println("Password is ok");
        }
    }

    private static boolean containsDigit(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}

// TODO: объявите класс InvalidPasswordException extends RuntimeException

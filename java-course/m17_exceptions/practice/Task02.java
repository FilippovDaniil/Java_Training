package m17_exceptions.practice;

/**
 * Задача 02 — Модуль 17: Несколько catch
 *
 * ЗАДАНИЕ:
 *   Дан массив из 3 элементов и строка. В блоке try выполните
 *   две потенциально опасные операции:
 *     - обращение к arr[10] (выход за границу);
 *     - Integer.parseInt("abc") (неверный формат).
 *   Перехватите ОБА типа исключения отдельными блоками catch
 *   и выведите соответствующие сообщения.
 *
 * ОЖИДАЕМЫЙ ВЫВОД (зависит от порядка операций):
 *   Выход за границу массива
 *
 * ПОДСКАЗКА:
 *   catch (ArrayIndexOutOfBoundsException e) { ... }
 *   catch (NumberFormatException e) { ... }
 *   Попробуйте поменять операции местами и понаблюдать, какой catch
 *   сработает.
 */
public class Task02 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        String text = "abc";
        // Ваш код здесь
        try{
            System.out.println(Integer.parseInt(text));
            System.out.println(arr[10]);
        }catch(NumberFormatException e){
            System.out.println(e.getMessage());
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
    }
}

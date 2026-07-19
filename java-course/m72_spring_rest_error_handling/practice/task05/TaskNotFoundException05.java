package m72_spring_rest_error_handling.practice.task05;

// Исключение
class TaskNotFoundException05 extends RuntimeException {
    TaskNotFoundException05(Long id) {
        super("Задача " + id + " не найдена");
    }
}
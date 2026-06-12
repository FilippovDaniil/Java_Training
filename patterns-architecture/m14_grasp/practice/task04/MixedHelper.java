package m14_grasp.practice.task04;

// «ДО» — низкая связность: две несвязанные обязанности в одном классе.
// НЕ используйте в решении; оставлено как образец того, что разносим.
class MixedHelper {
    boolean isStrongPassword(String pwd) {
        return pwd != null && pwd.length() >= 8;
    }

    String formatProfile(String name, int age) {
        return name + " (" + age + ")";
    }
}

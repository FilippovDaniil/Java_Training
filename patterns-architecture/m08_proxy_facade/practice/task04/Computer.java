package m08_proxy_facade.practice.task04;

// Facade: один простой вход start(), скрывающий порядок вызовов подсистем.
class Computer {
    private final Cpu cpu = new Cpu();
    private final Memory memory = new Memory();
    private final Disk disk = new Disk();

    public void start() {
        // TODO: вызвать cpu.boot(), memory.load(), disk.read() по порядку
        //       и напечатать каждый результат
    }
}

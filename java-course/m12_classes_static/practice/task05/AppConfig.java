package m12_classes_static.practice.task05;

class AppConfig {
    public static String version;

    // TODO: статический блок и блок инициализации экземпляра
    static {
        version = "Version 1.0";
        System.out.println("Static inicialzatiob");
        System.out.println(version);
    }

    {
        System.out.println("Object has created");
    }
}

package pl.zajavka.example;

public class Main {
    public static void main(String[] args) {
        ExampleBeanService exampleBeanService = new ExampleBeanServiceImpl();
        InjectedBeanService injectedBeanService = new InjectedBeanServiceImpl();

        exampleBeanService.setInjectedBeanService(injectedBeanService);

        System.out.println(exampleBeanService.sampleMethod());
    }
}

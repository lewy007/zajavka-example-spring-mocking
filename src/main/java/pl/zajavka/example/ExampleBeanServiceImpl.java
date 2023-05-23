package pl.zajavka.example;

public class ExampleBeanServiceImpl implements ExampleBeanService {

    private InjectedBeanService injectedBeanService;

    @Override
    public void setInjectedBeanService(InjectedBeanService injectedBeanService) {
        this.injectedBeanService = injectedBeanService;
    }

    @Override
    public boolean sampleMethod() {
        return injectedBeanService.anotherSampleMethod();
    }
}

package pl.zajavka.example;

public class ExampleBeanServiceImpl implements ExampleBeanService {

    private InjectedBeanService injectedBeanService;

    @Override
    public void setInjectedBeanService(InjectedBeanService injectedBeanService) {
        this.injectedBeanService = injectedBeanService;
    }

    @Override
    public String sampleMethod(Dog someValue) {
        return injectedBeanService.anotherSampleMethod(someValue);
    }
}

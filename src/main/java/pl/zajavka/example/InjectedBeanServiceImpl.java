package pl.zajavka.example;

public class InjectedBeanServiceImpl implements InjectedBeanService {

    @Override
    public String anotherSampleMethod(Dog someValue) {
        return "some Value: " + someValue;
    }

    @Override
    public String someOtherMethod() {
        return "some other value";
    }
}

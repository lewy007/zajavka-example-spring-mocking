## MOCKITO

## jeśli odpalimy program normalnie to obiekty zostana utworzone normalnie. Natomiast jesli w testach uzyjemy zaslepki (Mock) to prawdziwy obiekt nie zostanie utworzony, tylko sztuczny, wygenerowany przeze mnie na czas testu. Czyli klasa SomeClass będzie testowana w izolacji.

```java
class SomeClassTest {

    @InjectMocks
    private SomeClass someClass;

    @Mock
    private SomeOtherClass someOtherClass;

    @Test
    void someTest() {
        // to jest stub
        Mockito.when(someOtherClass.someOtherMethod()).thenReturn("great");

        someClass.someMethod();
        
        // to jest mock, czyli stub plus a do tego posiada wczesniej 
        // zdefiniowane uzyciektore w przypadku niespelnienia wyrzuci blad.
        Mockito.verify(someClass);
    }
}
```

### 1 pierwszy commit to test bez wykorzystania Mockito
```java
class ExampleBeanServiceImplTest {

    @Test
    void sampleMethod() {

        // given
        ExampleBeanService exampleBeanService = new ExampleBeanServiceImpl();
        exampleBeanService.setInjectedBeanService(new InjectedBeanService() {
            @Override
            public boolean anotherSampleMethod() {
                return true;
            }
        });

        //when
        boolean result = exampleBeanService.sampleMethod();

        //then
        Assertions.assertEquals(true, result);
    }
    
}
```

### 2 commit
### mockito stworzy samo zaślepkę, nie musimy tworzyc sami implementacji interfejsu z innym wynikiem, bo zrobi to za nas Mockito
```java
@ExtendWith(MockitoExtension.class)
class ExampleBeanServiceImplTest {

    @InjectMocks
    private ExampleBeanServiceImpl exampleBeanService;

    @Mock
    private InjectedBeanService injectedBeanService;

    @Test
    void sampleMethod() {
        // given
        Mockito.when(injectedBeanService.anotherSampleMethod()).thenReturn(true);

        //when
        boolean result = exampleBeanService.sampleMethod();

        //then
        Assertions.assertEquals(true, result);
    }
    
}
```
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

### 3 commit
argument matcher - jesli w metodzie pojawiaja sie jakies argumnety to stosujmey argument matcher
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
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.any())).thenReturn("my value");

        //when
        String result1 = exampleBeanService.sampleMethod(new Dog());
        String resul2 = exampleBeanService.sampleMethod(new Dog());
        String resul3 = exampleBeanService.sampleMethod(new Dog());
        String result4 = exampleBeanService.sampleMethod(new Dog());

        //then
        Assertions.assertEquals("my value", result1);
    }

}
```
### 4 commit
metoda verify() - pozwala na okreslenie czy dana metoda w zaslepce zostala wywolana, mozna ustawic zeby nie byla wykonywana lub policzyc ilosc wykonan. W ponizszym przykladzie spradzamy czy dokladnie jeden raz zostaly wykonane obie metody
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
        Mockito.when(injectedBeanService.someOtherMethod())
                .thenReturn("val1");
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.any()))
                .thenReturn("val2");
//        Mockito
//                .doReturn("my value")
//                .when(injectedBeanService)
//                .anotherSampleMethod(ArgumentMatchers.any());
        //when
        String result1 = exampleBeanService.sampleMethod(new Dog());

        //then
        Assertions.assertEquals("val1val2", result1);

        Mockito.verify(injectedBeanService, Mockito.times(1)).someOtherMethod();
        Mockito.verify(injectedBeanService, Mockito.times(1))
                .anotherSampleMethod(ArgumentMatchers.any());
    }

}
```
### 5 commit
ParameterizedTest sluzy do uzycia kombinacji parametrow do uruchoeminia testu. W naszym przypadku metoda wykona sie trzykrotnie, adokladnie raz dla kazdego przypadku:
```java
@ExtendWith(MockitoExtension.class)
class ExampleBeanServiceImplTest {

    @InjectMocks
    private ExampleBeanServiceImpl exampleBeanService;

    @Mock
    private InjectedBeanService injectedBeanService;

    @ParameterizedTest
    @MethodSource
    void sampleMethod(String val1, String val2) {
        // given
        Mockito.when(injectedBeanService.someOtherMethod())
                .thenReturn(val1);
        Mockito.when(injectedBeanService.anotherSampleMethod(ArgumentMatchers.any()))
                .thenReturn(val2);
//        Mockito
//                .doReturn("my value")
//                .when(injectedBeanService)
//                .anotherSampleMethod(ArgumentMatchers.any());
        //when
        String result1 = exampleBeanService.sampleMethod(new Dog());

        //then
        //Assertions.assertEquals("val1val2", result1);

        Mockito.verify(injectedBeanService, Mockito.times(1)).someOtherMethod();
        Mockito.verify(injectedBeanService, Mockito.times(1))
                .anotherSampleMethod(ArgumentMatchers.any());
    }
    static Stream<Arguments> sampleMethod() {
        return Stream.of(
                Arguments.of("val1", "val2"),
                Arguments.of("val3", "val4"),
                Arguments.of("val5", "val6")
        );
    }

}
```
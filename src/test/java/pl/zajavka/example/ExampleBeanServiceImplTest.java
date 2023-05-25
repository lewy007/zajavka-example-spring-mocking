package pl.zajavka.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.PreparedStatement;

import static org.junit.jupiter.api.Assertions.*;

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
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
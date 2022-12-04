package fh.aachen.praktikum2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.stream.Stream;

class While0_P2Test {

    @ParameterizedTest
    @MethodSource("testSources")
    void validWhile0(InputStream inputStream) throws ParseException {
        While0 testSubject = new While0(inputStream);
        testSubject.ReInit(inputStream);
        assertThat(testSubject.runParser()).isTrue();
    }

    @MethodSource("testSources")
    private static Stream<Arguments> testSources(){
        return Stream.of(
                Arguments.of(ClassLoader.getSystemResourceAsStream("simple_test.txt"), true),
                Arguments.of(ClassLoader.getSystemResourceAsStream("complex_test_single_row.txt"), true),
                Arguments.of(ClassLoader.getSystemResourceAsStream("asdasd.txt"), true),
                Arguments.of(ClassLoader.getSystemResourceAsStream("complex_test_multiple_row.txt"), true)
        );
    }
}
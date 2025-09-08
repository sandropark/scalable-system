package com.sandro.common.domain.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PageLimitCalculatorTest {
    @ParameterizedTest
    @MethodSource
    void calculatePageLimit(int pageNumber, int pageSize, int expected) throws Exception {
        assertThat(PageLimitCalculator.calculatePageLimit(pageNumber, pageSize)).isEqualTo(expected);
    }

    static Stream<Arguments> calculatePageLimit() {
        return Stream.of(
                Arguments.of(1, 30, 301),
                Arguments.of(5, 30, 301),
                Arguments.of(10, 30, 301),
                Arguments.of(11, 30, 601)
        );
    }
}
package com.sandro.common.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PageLimitCalculator {

    public static Long calculatePageLimit(int pageNumber, int pageSize) {
        return calculatePageLimit(pageNumber, pageSize, 10L);
    }

    public static Long calculatePageLimit(int pageNumber, int pageSize, Long moveablePageCount) {
        return ((pageNumber - 1) / moveablePageCount + 1) * pageSize * moveablePageCount + 1;
    }
}

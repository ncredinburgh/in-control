package com.ncredinburgh.incontrol;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class InControl {

    private InControl() {}

    private static final Map<Integer, Integer> controlPictureReplacements;
    private static final Collection<Integer> formattingCodes;

    static {
        controlPictureReplacements = new HashMap<>();
        controlPictureReplacements.put(0x0000, 0x2400);
        controlPictureReplacements.put(0x0001, 0x2401);
        controlPictureReplacements.put(0x0002, 0x2402);
        controlPictureReplacements.put(0x0003, 0x2403);
        controlPictureReplacements.put(0x0004, 0x2404);
        controlPictureReplacements.put(0x0005, 0x2405);
        controlPictureReplacements.put(0x0006, 0x2406);
        controlPictureReplacements.put(0x0007, 0x2407);
        controlPictureReplacements.put(0x0008, 0x2408);
        controlPictureReplacements.put(0x0009, 0x2409);
        controlPictureReplacements.put(0x000A, 0x240A);
        controlPictureReplacements.put(0x000B, 0x240B);
        controlPictureReplacements.put(0x000C, 0x240C);
        controlPictureReplacements.put(0x000D, 0x240D);
        controlPictureReplacements.put(0x000E, 0x240E);
        controlPictureReplacements.put(0x000F, 0x240F);
        controlPictureReplacements.put(0x0010, 0x2410);
        controlPictureReplacements.put(0x0011, 0x2411);
        controlPictureReplacements.put(0x0012, 0x2412);
        controlPictureReplacements.put(0x0013, 0x2413);
        controlPictureReplacements.put(0x0014, 0x2414);
        controlPictureReplacements.put(0x0015, 0x2415);
        controlPictureReplacements.put(0x0016, 0x2416);
        controlPictureReplacements.put(0x0017, 0x2417);
        controlPictureReplacements.put(0x0018, 0x2418);
        controlPictureReplacements.put(0x0019, 0x2419);
        controlPictureReplacements.put(0x001A, 0x241A);
        controlPictureReplacements.put(0x001B, 0x241B);
        controlPictureReplacements.put(0x001C, 0x241C);
        controlPictureReplacements.put(0x001D, 0x241D);
        controlPictureReplacements.put(0x001E, 0x241E);
        controlPictureReplacements.put(0x001F, 0x241F);
        controlPictureReplacements.put(0x007F, 0x2421);

        formattingCodes = Arrays.asList(0x009, 0x000A, 0x000D);
    }

    private static int convertCodePoint(final int codePoint, final boolean excludeFormatting) {
        if (excludeFormatting && formattingCodes.stream().anyMatch(c -> c.equals(codePoint))) {
            return codePoint;
        } else {
            return controlPictureReplacements.getOrDefault(codePoint, codePoint);
        }
    }


    /**
     *
     * For a given string that contains control codes, return a new string containing pictures in their place.
     * Excludes formatting codes (tab, new line, carriage return)
     *
     * @param value a string, optionally containing control codes
     * @return a new string with control codes replaced by control pictures
     */
    public static String controlCharactersToPictures(final String value) {
        return controlCharactersToPictures(value, true);
    }


    /**
     *
     * For a given string that contains control codes, return a new string containing pictures in their place.
     * Optionally exclude formatting codes (tab, new line, carriage return) from replacement.
     *
     * @param value a string, optionally containing control codes
     * @param excludeFormatting whether to exclude formatting codes from replacement
     * @return a new string with control codes replaced by control pictures
     */
    public static String controlCharactersToPictures(final String value, final boolean excludeFormatting) {
        final IntStream convertedCodePoints = value.codePoints().map(i -> convertCodePoint(i, excludeFormatting));

        final StringBuilder stringBuilder = convertedCodePoints.collect( StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append);

        return stringBuilder.toString();
    }
}

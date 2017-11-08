package com.ncredinburgh.incontrol;

import org.junit.Test;
import static com.ncredinburgh.incontrol.InControl.controlCharactersToPictures;

import static org.junit.Assert.*;

public class InControlTest {
    @Test
    public void shouldPassThroughSupplementaryCodePointCharacters() throws Exception {
        String stringWithSupplimentaryCodePoint = "\uD801\uDC00";

        String result = controlCharactersToPictures(stringWithSupplimentaryCodePoint);

        assertEquals("\uD801\uDC00", result);
    }

    @Test
    public void shouldPassThroughNonControlCharacters() throws Exception {
        String stringWithNonControlCharacters = "some non-control characters";

        String result = controlCharactersToPictures(stringWithNonControlCharacters);

        assertEquals("some non-control characters", result);
    }

    @Test
    public void shouldReplaceControlCharacters() throws Exception {
        String stringWithControlCharacters = "Some control characters follow\u0008\u0008";

        String result = controlCharactersToPictures(stringWithControlCharacters);

        assertEquals("Some control characters follow\u2408\u2408", result);
    }

    @Test
    public void shouldPassThroughNewLinesByDefault() throws Exception {
        String stringWithNewLines = "Every\nword\non\na\nnew\nline";

        String result = controlCharactersToPictures(stringWithNewLines);

        assertEquals("Every\nword\non\na\nnew\nline", result);
    }

    @Test
    public void shouldPassThroughNewLinesWhenFormattingExcluded() throws Exception {
        String stringWithNewLines = "Every\nword\non\na\nnew\nline";

        String result = controlCharactersToPictures(stringWithNewLines, true);

        assertEquals("Every\nword\non\na\nnew\nline", result);
    }

    @Test
    public void shouldNotPassThroughNewLinesWhenFormattingIncluded() throws Exception {
        String stringWithNewLines = "Every\nword\non\na\nnew\nline";

        String result = controlCharactersToPictures(stringWithNewLines, false);

        assertEquals("Every␊word␊on␊a␊new␊line", result);
    }
}
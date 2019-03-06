package enedis.romaindavid.com.algorithme;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static enedis.romaindavid.com.algorithme.Plugin.*;
import static org.junit.jupiter.api.Assertions.*;

class PluginTest {
    /**
     * Unit test of the classe generateRandom
     */
    @Test
    void Given_RandomNumber_When_GenerateRandom_Then_NumberGeneratesLessThanBound() {
         int bound = 10;
        assertTrue(generateRandom( bound ) < bound );
    }

    /**
     * Unit test of method generateRandomInRange
     */
    @Test
    void Given_RandomNumber_When_GenerateRandomInRange_Then_NumberMinBetweenMax() {
        int min = 4;
        int max = 8;
        int rand = generateRandomInRange( min, max );
        assertTrue( rand >= min && rand <= max );
    }

    /**
     * Unit test of method toInt
     */
    @Test
    void Given_String_When_StringToIntger_Then_CastInteger() {
        String str = "11";
        assertEquals(toInt( str ),11);
    }

    /**
     * Unit test of method toStr
     */
    @Test
    void Given_Integer_When_IntegerToString_Then_CastString() {
        Integer intStr = 11;
        assertEquals( toStr(intStr ),"11" );
    }

    /**
     * Unit test of method formatNumber
     */
    @Test
    void Given_IntegerValue_When_FormatIntegerToString_Then_FormatIntegerToStringLengthSpecific() {
        int len = 4;
        int value = 6;
        assertEquals(formatNumber(len,value),"0006");
    }

    /**
     * Unit test of method firstUpperCase
     */
    @Test
    void Given_StringValue_When_NeedToCapitalize_Then_StringValueToUppercaseFirstLetter() {
        String value = "romain";
        assertEquals(firstUpperCase(value ),"Romain");
    }

    /**
     * Unit test of method triAvecValeur
     */
    @Test
    void Given_HashMap_When_SortHashMap_Then_HasmapSortedDescendingOrder() {
        HashMap <String, Integer> map1 = new HashMap<>();
        HashMap <String, Integer> map2 = new HashMap<>();
        map1.put("10",10);
        map1.put("11",11);
        map2 = sortWithValue( map1 );
        assertNotSame( map1, map2 );
    }
}
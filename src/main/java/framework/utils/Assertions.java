package framework.utils;

import org.testng.Assert;


public final class Assertions {

    private Assertions() {
    }

    public static void assertTrue(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    public static void assertFalse(boolean condition, String message) {
        Assert.assertFalse(condition, message);
    }

    public static void assertFalse(boolean condition) {
        Assert.assertFalse(condition);
    }

    public static void assertEqual(Object actual, Object expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertEqual(Object actual, Object expected) {
        Assert.assertEquals(actual, expected);
    }

    public static void assertEqual(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertNotEqual(Object actual, Object expected, String message) {
        Assert.assertNotEquals(actual, expected, message);
    }

    public static void assertNotEqual(Object actual, Object expected) {
        Assert.assertNotEquals(actual, expected);
    }

    public static void assertNotNull(Object object, String message) {
        Assert.assertNotNull(object, message);
    }

    public static void assertNotNull(Object object) {
        Assert.assertNotNull(object);
    }

    public static void assertNull(Object object, String message) {
        Assert.assertNull(object, message);
    }

    public static void assertNull(Object object) {
        Assert.assertNull(object);
    }

    public static void assertContains(String actual, String substring, String message) {
        Assert.assertTrue(
                actual != null && actual.contains(substring),
                message + " → Beklenen: '" + substring + "', Gerçek: '" + actual + "'");
    }

    public static void assertContains(String actual, String substring) {
        assertContains(actual, substring,
                "Metin '" + substring + "' içermeli");
    }

    public static void assertNotEmpty(String actual, String message) {
        Assert.assertTrue(actual != null && !actual.isBlank(), message);
    }

    public static void assertGreaterThanZero(int value, String message) {
        Assert.assertTrue(value > 0, message + " → Gerçek değer: " + value);
    }

    public static void assertFail(String message) {
        Assert.fail(message);
    }

    public static void assertFail() {
        Assert.fail();
    }
}

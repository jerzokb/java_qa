package pl.qacourses.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {

    @Test
    public void test1() {
       // Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
        Assert.assertTrue(Primes.isPrime(29));
    }

    @Test
    public void test2() {
        // Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
        Assert.assertTrue(Primes.isPrimeWhile(13));
    }

    @Test (enabled = false)
    public void test3() {
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrime(n));
    }

    @Test
    public void test4() {
        // Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
        Assert.assertTrue(Primes.isPrimeFast(Integer.MAX_VALUE));
    }

}

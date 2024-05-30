package storage.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class testSumWater {
    @Test
    public void testSumWater1(){
        assertEquals(0, test.sumWater(-1));
    }
    @Test
    public void testSumWater2(){
        assertEquals(0.00, test.sumWater(0));
    }
    @Test
    public void testSumWater2_1(){
        assertEquals(0, test.sumWater(0));
    }
    @Test
    public void testSumWater3(){
        assertEquals(68689.5, test.sumWater(10));
    }
    @Test
    public void testSumWater4(){
        assertEquals(149787.5, test.sumWater(20));
    }
    @Test
    public void testSumWater5(){
        assertEquals(249481, test.sumWater(30));
    }
    @Test
    public void testSumWater6(){
        assertEquals(267799.35, test.sumWater(31));
    }
}

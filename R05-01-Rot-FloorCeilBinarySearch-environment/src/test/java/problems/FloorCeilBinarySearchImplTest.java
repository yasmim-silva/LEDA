package problems;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FloorCeilBinarySearchImplTest {
    
    @Test
    public void testFloor01() {
        Integer[] array = {1, 2, 3, 4};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.floor(array, 3);

        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    //Testa a primeira parte da busca binária.
    public void testFloor02() {
        Integer[] array = {0, 1, 2, 3, 8};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.floor(array, 4);

        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    //Testa a segunda parte da busca binária.
    public void testFloor03() {
        Integer[] array = {0, 2, 5, 6, 8};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.floor(array, 4);

        assertEquals(Integer.valueOf(2), result);
    }

    @Test
    //Testa quando não há floor.
    public void testFloor04() {
        Integer[] array = {7, 8, 9, 10};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.floor(array, 4);

        assertEquals(null, result);
    }

    @Test
    public void testCeil01() {
        Integer[] array = {1, 2, 3, 4};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.ceil(array, 3);

        assertEquals(Integer.valueOf(3), result);
    }

    @Test
    //Testa a primeira parte da busca binária.
    public void testCeil02() {
        Integer[] array = {0, 5, 6, 7, 8};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.ceil(array, 4);

        assertEquals(Integer.valueOf(5), result);
    }

    @Test
    //Testa a segunda parte da busca binária.
    public void testCeil03() {
        Integer[] array = {0, 1, 2, 5, 6};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.ceil(array, 4);

        assertEquals(Integer.valueOf(5), result);
    }

    @Test
    //Testa quando não há ceil.
    public void testCeil04() {
        Integer[] array = {0, 2, 3, 4};
        FloorCeil f = new FloorCeilBinarySearchImpl();
        Integer result = f.ceil(array, 8);

        assertEquals(null, result);
    }
}

package com.huysor.ecommerce.phoneshop.utl;

import com.huysor.ecommerce.phoneshop.services.util.GeneralUntils;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneralUntilTest {
    @Test
    public void testToInteger(){
        // Given
        List<String> names=List.of("Dara","Kok Lola","Thida");
        //When
        List<Integer>list= GeneralUntils.toInteger(names);
        //then
        // [4,8,5]
        assertEquals(3,list.size());
        assertEquals(4,list.get(0));
        assertEquals(8,list.get(1));
        assertEquals(5,list.get(2));

        // it we error when write
//        assertEquals(7,list.get(1)); cause the result actual is 8

    }
}

package com.awdean.poeima.data;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.awdean.poeima.data.ItemType;

public class ItemTypeTest {

    @Test
    public void testParseItemTypeDistinct() {
        List<ItemType> values = Arrays.asList(ItemType.values());
        Set<String> descriptions = values.stream()
                                         .map((ItemType itemType) -> itemType.toString())
                                         .collect(Collectors.toSet());
        assertThat(descriptions, iterableWithSize(values.size()));
    }
    
    @Test
    public void testParseItemTypeNull() {
        assertThat(ItemType.parseItemType(null), is(nullValue()));
        
        String rejected = "The quick brown fox jumped over the lazy dog.";
        assertThat(ItemType.parseItemType(rejected), is(nullValue()));
    }
    
    @Test
    public void testParseItemTypeOnto() {
        for (ItemType itemType : ItemType.values()) {
            assertThat(ItemType.parseItemType(itemType.toString()), is(itemType));
        }
    }
    
}

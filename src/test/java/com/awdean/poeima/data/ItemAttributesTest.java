package com.awdean.data;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.function.BiConsumer;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Range;

public class ItemAttributesTest {

    static final ItemAttributes DEFAULT = new ItemAttributes();
    static final ItemAttributes COMBINED = new ItemAttributes();
    static final ItemAttributes LHS = new ItemAttributes();
    static final ItemAttributes RHS = new ItemAttributes();

    ItemAttributes lhs;
    ItemAttributes rhs;

    @Before
    public void initialize() {
        lhs = LHS;
        rhs = RHS;
    }

    @Test
    public void testEquals() {
        ItemAttributes head = new ItemAttributes();
        ItemAttributes tail = new ItemAttributes();

        // handle reflexive comparison
        assertThat(head, is(head));
        // handle compare-to-null short-circuit
        assertThat(head, is(not(equalTo(null))));
        // handle class-based comparison
        assertThat(head, is(not(new Object())));

        // iterate over all fields of the object
        head.rarity = COMBINED.rarity;
        assertThat(head, is(not(tail)));
        tail.rarity = head.rarity;
        assertThat(head, is(tail));

        head.name = COMBINED.name;
        assertThat(head, is(not(tail)));
        tail.name = head.name;
        assertThat(head, is(tail));

        head.base = COMBINED.base;
        assertThat(head, is(not(tail)));
        tail.base = head.base;
        assertThat(head, is(tail));

        head.type = COMBINED.type;
        assertThat(head, is(not(tail)));
        tail.type = head.type;
        assertThat(head, is(tail));

        head.quality = COMBINED.quality;
        assertThat(head, is(not(tail)));
        tail.quality = head.quality;
        assertThat(head, is(tail));

        head.armour = COMBINED.armour;
        assertThat(head, is(not(tail)));
        tail.armour = head.armour;
        assertThat(head, is(tail));

        head.evasionRating = COMBINED.evasionRating;
        assertThat(head, is(not(tail)));
        tail.evasionRating = head.evasionRating;
        assertThat(head, is(tail));

        head.energyShield = COMBINED.energyShield;
        assertThat(head, is(not(tail)));
        tail.energyShield = head.energyShield;
        assertThat(head, is(tail));

        head.block = COMBINED.block;
        assertThat(head, is(not(tail)));
        tail.block = head.block;
        assertThat(head, is(tail));

        head.physical = COMBINED.physical;
        assertThat(head, is(not(tail)));
        tail.physical = head.physical;
        assertThat(head, is(tail));

        head.elemental = COMBINED.elemental;
        assertThat(head, is(not(tail)));
        tail.elemental = head.elemental;
        assertThat(head, is(tail));

        head.chaos = COMBINED.chaos;
        assertThat(head, is(not(tail)));
        tail.chaos = head.chaos;
        assertThat(head, is(tail));

        head.critical = COMBINED.critical;
        assertThat(head, is(not(tail)));
        tail.critical = head.critical;
        assertThat(head, is(tail));

        head.attacks = COMBINED.attacks;
        assertThat(head, is(not(tail)));
        tail.attacks = head.attacks;
        assertThat(head, is(tail));

        head.levelRequired = COMBINED.levelRequired;
        assertThat(head, is(not(tail)));
        tail.levelRequired = head.levelRequired;
        assertThat(head, is(tail));

        head.strengthRequired = COMBINED.strengthRequired;
        assertThat(head, is(not(tail)));
        tail.strengthRequired = head.strengthRequired;
        assertThat(head, is(tail));

        head.dexterityRequired = COMBINED.dexterityRequired;
        assertThat(head, is(not(tail)));
        tail.dexterityRequired = head.dexterityRequired;
        assertThat(head, is(tail));

        head.intelligenceRequired = COMBINED.intelligenceRequired;
        assertThat(head, is(not(tail)));
        tail.intelligenceRequired = head.intelligenceRequired;
        assertThat(head, is(tail));

        head.sockets = COMBINED.sockets;
        assertThat(head, is(not(tail)));
        tail.sockets = head.sockets;
        assertThat(head, is(tail));

        head.level = COMBINED.level;
        assertThat(head, is(not(tail)));
        tail.level = head.level;
        assertThat(head, is(tail));

        head.implicit = COMBINED.implicit;
        assertThat(head, is(not(tail)));
        tail.implicit = head.implicit;
        assertThat(head, is(tail));

        head.explicits = COMBINED.explicits;
        assertThat(head, is(not(tail)));
        tail.explicits = head.explicits;
        assertThat(head, is(tail));

        head.corrupted = COMBINED.corrupted;
        assertThat(head, is(not(tail)));
        tail.corrupted = head.corrupted;
        assertThat(head, is(tail));

        head.mirrored = COMBINED.mirrored;
        assertThat(head, is(not(tail)));
        tail.mirrored = head.mirrored;
        assertThat(head, is(tail));

        head.unidentified = COMBINED.unidentified;
        assertThat(head, is(not(tail)));
        tail.unidentified = head.unidentified;
        assertThat(head, is(tail));

        head.dirty = new HashSet<>();
        assertThat(head, is(not(tail)));
        tail.dirty = head.dirty;
        assertThat(head, is(tail));
        head.dirty = null; // need to reset this value, because it's special

        // ensure that we've touched all the object's fields
        assertThat(head, is(COMBINED));
    }

    @Test
    public void testHashCode() {
        assertThat(DEFAULT.hashCode(), is(not(COMBINED.hashCode())));
    }

    @Test
    public void testToString() {
        assertThat(DEFAULT.toString(), is(notNullValue()));
        assertThat(DEFAULT.toString(), is(not(COMBINED.toString())));

        assertThat(COMBINED.toString(), is(notNullValue()));
        assertThat(COMBINED.toString(), is(not(DEFAULT.toString())));
    }

    @Test
    public void testJoin() {
        Object combined = ItemAttributes.join(lhs, rhs);
        assertThat(combined, is(COMBINED));

        combined = ItemAttributes.join(combined, null);
        assertThat(combined, is(COMBINED));

        combined = ItemAttributes.join(null, combined);
        assertThat(combined, is(COMBINED));
    }

    @Test
    public void testJoinDirty() {
        ItemAttributes combined = (ItemAttributes) ItemAttributes.join(lhs, lhs);
        assertThat(combined.dirty, is(notNullValue()));
        combined = (ItemAttributes) ItemAttributes.join(rhs, rhs);
        assertThat(combined.dirty, is(notNullValue()));
    }

    @Test
    public void testJoinCore() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinCore;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.rarity = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.name = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.base = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinInherent() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinInherents;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.type = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.quality = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.armour = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.evasionRating = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.energyShield = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.block = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.physical = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.elemental = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.chaos = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.critical = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.attacks = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinRequirements() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinRequirements;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.levelRequired = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.strengthRequired = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.dexterityRequired = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.intelligenceRequired = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinSockets() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinSockets;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.sockets = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinLevel() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinLevel;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.level = 0;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinProperties() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinProperties;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.implicit = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.explicits = null;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));
    }

    @Test
    public void testJoinTags() {
        BiConsumer<ItemAttributes, ItemAttributes> testFunction = ItemAttributes::joinTags;

        assertJoinInvariants(lhs, rhs, testFunction);

        ItemAttributes target = new ItemAttributes();

        testFunction.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.corrupted = false;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.mirrored = false;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(notNullValue()));
        target.dirty = null;
        target.unidentified = false;

        testFunction.accept(target, target);
        assertThat(target.dirty, is(nullValue()));

        // we do a little bit extra here to test the 'dirty' metadata field
        ItemAttributes empty = new ItemAttributes();
        target.markDirty("dirty");
        testFunction.accept(empty, target);
        assertThat(empty.dirty, is(notNullValue()));
    }

    private void assertJoinInvariants(ItemAttributes lhs, ItemAttributes rhs,
            BiConsumer<ItemAttributes, ItemAttributes> joiner) {
        ItemAttributes combined = new ItemAttributes();

        joiner.accept(combined, lhs);
        assertThat(combined.dirty, is(nullValue()));
        joiner.accept(combined, rhs);
        assertThat(combined.dirty, is(nullValue()));

        ItemAttributes target = new ItemAttributes();

        joiner.accept(target, COMBINED);
        assertThat(target.dirty, is(nullValue()));

        assertThat(combined, is(not(DEFAULT)));
        assertThat(combined, is(target));

        joiner.accept(combined, null);
        assertThat(combined.dirty, is(nullValue()));
        assertThat(combined, is(target));
    }

    static {
        // Core attributes
        COMBINED.rarity = ItemRarity.NORMAL;
        LHS.rarity = ItemRarity.NORMAL;
        COMBINED.name = "Test Name";
        RHS.name = "Test Name";
        COMBINED.base = "Test Base";
        LHS.base = "Test Base";

        // Common attributes
        COMBINED.type = ItemType.AMULET;
        RHS.type = ItemType.AMULET;
        COMBINED.quality = 20;
        LHS.quality = 20;
        // Armour attributes
        COMBINED.armour = 111;
        RHS.armour = 111;
        COMBINED.evasionRating = 222;
        LHS.evasionRating = 222;
        COMBINED.energyShield = 333;
        RHS.energyShield = 333;
        COMBINED.block = 4;
        LHS.block = 4;
        // Weapon attributes
        COMBINED.physical = Range.closed(2, 3);
        RHS.physical = Range.closed(2, 3);
        COMBINED.elemental = Arrays.asList(Range.closed(5, 7), Range.closed(11, 13), Range.closed(17, 19));
        LHS.elemental = Arrays.asList(Range.closed(5, 7), Range.closed(11, 13), Range.closed(17, 19));
        COMBINED.chaos = Range.closed(23, 29);
        RHS.chaos = Range.closed(23, 29);
        COMBINED.critical = BigDecimal.ONE;
        LHS.critical = BigDecimal.ONE;
        COMBINED.attacks = BigDecimal.TEN;
        RHS.attacks = BigDecimal.TEN;

        // Item requirements
        COMBINED.levelRequired = 9;
        LHS.levelRequired = 9;
        COMBINED.strengthRequired = 18;
        RHS.strengthRequired = 18;
        COMBINED.dexterityRequired = 27;
        LHS.dexterityRequired = 27;
        COMBINED.intelligenceRequired = 36;
        RHS.intelligenceRequired = 36;

        // Item sockets
        COMBINED.sockets = "R-R-G-G-B-B";
        LHS.sockets = "R-R-G-G-B-B";

        // Item level
        COMBINED.level = 42;
        RHS.level = 42;

        // Item properties
        COMBINED.implicit = "Test Implicit";
        LHS.implicit = "Test Implicit";
        COMBINED.explicits = Arrays.asList("Test Explicit One", "Test Explicit Two");
        RHS.explicits = Arrays.asList("Test Explicit One", "Test Explicit Two");

        // Item tags
        COMBINED.corrupted = true;
        LHS.corrupted = true;
        COMBINED.mirrored = true;
        RHS.mirrored = true;
        COMBINED.unidentified = true;
        LHS.unidentified = true;
    }

}
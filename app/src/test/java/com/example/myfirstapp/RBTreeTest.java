package com.example.myfirstapp;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class RBTreeTest {
    @Test(timeout = 1000)
    public void RBinsert() {
        // Implement your test cases
        // START YOUR CODE
        RBTree<Integer> integerRBTree = new RBTree<>();
        integerRBTree.insert(12);
        integerRBTree.insert(1);
        integerRBTree.insert(9);
        integerRBTree.insert(2);

        Assert.assertTrue(integerRBTree.find(2).color.equals("RED"));
        Assert.assertTrue(integerRBTree.find(9).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(1).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(12).color.equals("BLACK"));
        integerRBTree.insert(0);
        Assert.assertTrue(integerRBTree.find(0).color.equals("RED"));
        integerRBTree.insert(11);
        integerRBTree.insert(7);
        Assert.assertTrue(integerRBTree.find(7).color.equals("RED"));
        Assert.assertEquals(integerRBTree.find(2),integerRBTree.find(7).parent);
        integerRBTree.insert(19);
        integerRBTree.insert(4);
        integerRBTree.insert(15);
        Assert.assertTrue(integerRBTree.find(15).color.equals("RED"));
        Assert.assertEquals(integerRBTree.find(19),integerRBTree.find(15).parent);
        Assert.assertEquals(integerRBTree.find(12),integerRBTree.find(19).parent);
        integerRBTree.insert(18);
        Assert.assertTrue(integerRBTree.find(18).color.equals("BLACK"));
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(15).parent);
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(19).parent);
        integerRBTree.insert(5);
        Assert.assertTrue(integerRBTree.find(9).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(1).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(0).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(2).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(7).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(4).color.equals("RED"));
        Assert.assertTrue(integerRBTree.find(5).color.equals("RED"));
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(15).parent);
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(19).parent);


    }

    public void RBdelete(){
        RBTree<Integer> integerRBTree = new RBTree<>();
        integerRBTree.insert(12);
        integerRBTree.insert(1);
        integerRBTree.insert(9);
        integerRBTree.insert(2);
        integerRBTree.insert(0);
        integerRBTree.insert(11);
        integerRBTree.insert(7);
        integerRBTree.insert(19);
        integerRBTree.insert(4);
        integerRBTree.insert(15);
        integerRBTree.insert(18);
        integerRBTree.insert(5);

        //general case
        integerRBTree.delete(integerRBTree.getRoot(),7);
        Assert.assertEquals(null,integerRBTree.find(7));
        Assert.assertTrue(integerRBTree.find(5).color.equals("BLACK"));

        //case2

        integerRBTree.delete(integerRBTree.getRoot(),4);
        Assert.assertEquals(null,integerRBTree.find(4));
        Assert.assertTrue(integerRBTree.find(5).color.equals("RED"));
        Assert.assertEquals(integerRBTree.find(2),integerRBTree.find(5).parent);

        //case3
        integerRBTree.delete(integerRBTree.getRoot(),12);
        Assert.assertEquals(null,integerRBTree.find(12));
        Assert.assertTrue(integerRBTree.find(15).color.equals("RED"));
        Assert.assertTrue(integerRBTree.find(18).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(11).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(19).color.equals("BLACK"));
        Assert.assertEquals(integerRBTree.find(11),integerRBTree.find(15).parent);
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(19).parent);
        Assert.assertEquals(integerRBTree.find(18),integerRBTree.find(11).parent);

        //case4
        integerRBTree.delete(integerRBTree.getRoot(),1);
        Assert.assertEquals(null,integerRBTree.find(1));
        Assert.assertTrue(integerRBTree.find(2).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(0).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(5).color.equals("BLACK"));
        Assert.assertEquals(integerRBTree.find(2),integerRBTree.find(5).parent);
        Assert.assertEquals(integerRBTree.find(2),integerRBTree.find(0).parent);

        //DELETE root
        integerRBTree.delete(integerRBTree.getRoot(),9);
        Assert.assertEquals(null,integerRBTree.find(9));
        Assert.assertTrue(integerRBTree.find(2).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(5).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(11).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(19).color.equals("BLACK"));
        Assert.assertTrue(integerRBTree.find(0).color.equals("RED"));
        Assert.assertTrue(integerRBTree.find(18).color.equals("RED"));
        Assert.assertEquals(integerRBTree.find(5),integerRBTree.find(2).parent);
        Assert.assertEquals(integerRBTree.find(2),integerRBTree.find(0).parent);


    }



}
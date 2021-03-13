package test;

import org.testng.annotations.Test;
import org.testng.Assert;
import logic.Department;

public class FirstTest {

    @Test
    public void testJustSimpleTest() {
        Assert.assertEquals(1, 2);
    }
    
    @Test
    public void testDepartmentCreationTest() {
        Department department = new Department();
        Assert.assertEquals(1, 3);
    }
}

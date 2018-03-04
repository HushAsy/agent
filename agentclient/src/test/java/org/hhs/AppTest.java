package org.hhs;

import junit.framework.TestCase;
import org.hhs.utils.LoadClassName;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    public void testPackage(){
        LoadClassName loadClass = new LoadClassName();
        loadClass.getStringList();
    }
}

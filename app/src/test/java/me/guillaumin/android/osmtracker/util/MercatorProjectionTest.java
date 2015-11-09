package me.guillaumin.android.osmtracker.util;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static me.guillaumin.android.osmtracker.util.MercatorProjection.*;

public class MercatorProjectionTest {

    @Test
    public void formatDegreesAsGCTest() {
        assertEquals("", formatDegreesAsGC(47.12345f, false));
    }
}

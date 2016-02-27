package org.dcsc.athena.objects;

import java.util.concurrent.*;
import java.util.ArrayList;

public class DebugLib {
    // debugPrint

    private static final boolean DEBUGMODE = true;

    public static void println(String str) {
        if (DEBUGMODE)
            System.out.println("\n\n    [DEBUG] " + str + "\n\n");
    }
    
}
package com.jpii.navalbattle.debug;

import java.util.Comparator;

public class CommandComparator implements Comparator<Command> {
    public int compare(Command cmd1, Command cmd2) {
        return cmd1.compareTo(cmd2);
    }
}

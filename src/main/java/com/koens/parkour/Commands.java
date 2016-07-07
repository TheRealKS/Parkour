package com.koens.parkour;

public enum Commands {
    START,
    END,
    CHECKPOINT,
    RESET,
    SELECT,
    SELECTED,
    ADD,
    DELETE,
    EDIT;

    private enum editorCommands {
        SETSTART,
        SETEND,
        RENAME;
    }
}

package com.ibagroup.bot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Command {

    START,
    HELP,
    AUTHENTICATE,
    CANCEL;

    private boolean isAnonymous = true;

}

package com.ibagroup.bot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Command {

    START,
    HELP;

    private boolean isAnonymous = true;

}

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
    CANCEL,
    REGISTER_PRODUCT(false),
    SHOW_PRODUCTS(false);

    private boolean isAnonymous = true;

}

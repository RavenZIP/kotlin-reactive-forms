package com.github.ravenzip.kotlinreactiveforms.validation

internal val emailRegex: Regex =
    Regex(
        "[a-zA-Z0-9+._%\\-]{1,256}" +
            "@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
    )

internal val phoneRegex: Regex =
    Regex("(\\+[0-9]+[\\- .]*)?" + "(\\([0-9]+\\)[\\- .]*)?" + "([0-9][0-9\\- .]+[0-9])")

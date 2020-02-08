package com.yuk.cspserver.rule

enum class ElementRuleType(val typeId : Int) {
    INITIALIZE(1),
    CONSTRAINT(2),
    MIGRATION(3),
    RETENTION(4);
}
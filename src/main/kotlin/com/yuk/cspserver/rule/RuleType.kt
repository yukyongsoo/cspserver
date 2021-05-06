package com.yuk.cspserver.rule

enum class RuleType(val typeId: Int) {
    INITIALIZE(1),
    MIGRATION(2),
    RETENTION(3);
}
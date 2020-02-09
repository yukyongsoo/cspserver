package com.yuk.cspserver.constraint

enum class ConstraintType(private val typeId: Int) {
    NOTEQUAL(1),
    EQUAL(2),
    OVER(3),
    UNDER(4),
    GREATER(5),
    LESS(6);
}
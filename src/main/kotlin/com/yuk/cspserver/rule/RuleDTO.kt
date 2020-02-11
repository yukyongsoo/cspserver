package com.yuk.cspserver.rule

abstract class RuleDTO(val id: Int,
                       val typeId: Int,
                       val ruleType: RuleType)

data class RuleReadDTO(val id: Int,
                       val typeId: Int,
                       val ruleType: RuleType)
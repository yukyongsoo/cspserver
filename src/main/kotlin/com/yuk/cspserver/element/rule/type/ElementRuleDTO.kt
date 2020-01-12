package com.yuk.cspserver.element.rule.type

import com.yuk.cspserver.element.rule.ElementRuleType

abstract class ElementRuleDTO(val elementTypeId: Int,
                              val ruleId: Int,
                              val elementRuleType: ElementRuleType)
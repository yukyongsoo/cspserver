package com.yuk.cspserver.element.rule.constraint

import com.yuk.cspserver.element.rule.ElementRuleDTO
import com.yuk.cspserver.element.rule.ElementRuleType

class ConstraintRuleDTO(elementTypeId: Int,
                        ruleId: Int,
                        elementRuleType: ElementRuleType) : ElementRuleDTO(elementTypeId, ruleId, elementRuleType)
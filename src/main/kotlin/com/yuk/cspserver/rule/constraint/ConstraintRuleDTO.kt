package com.yuk.cspserver.rule.constraint

import com.yuk.cspserver.rule.ElementRuleDTO
import com.yuk.cspserver.rule.ElementRuleType

class ConstraintRuleDTO(elementTypeId: Int,
                        ruleId: Int,
                        elementRuleType: ElementRuleType) : ElementRuleDTO(elementTypeId, ruleId, elementRuleType)
package com.yuk.cspserver.element.rule.type

import com.yuk.cspserver.element.rule.ElementRuleType

class InitializeRuleDTO(elementTypeId: Int,
                        ruleId: Int,
                        elementRuleType: ElementRuleType,
                        val archiveId : Int) : ElementRuleDTO(elementTypeId, ruleId, elementRuleType)
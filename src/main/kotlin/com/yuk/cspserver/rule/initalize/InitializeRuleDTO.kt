package com.yuk.cspserver.rule.initalize

import com.yuk.cspserver.rule.ElementRuleDTO
import com.yuk.cspserver.rule.ElementRuleType

class InitializeRuleDTO(elementTypeId: Int,
                        ruleId: Int,
                        elementRuleType: ElementRuleType,
                        val archiveId : Int) : ElementRuleDTO(elementTypeId, ruleId, elementRuleType)
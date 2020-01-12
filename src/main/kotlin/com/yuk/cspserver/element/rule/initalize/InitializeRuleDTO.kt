package com.yuk.cspserver.element.rule.initalize

import com.yuk.cspserver.element.rule.ElementRuleType
import com.yuk.cspserver.element.rule.ElementRuleDTO

class InitializeRuleDTO(elementTypeId: Int,
                        ruleId: Int,
                        elementRuleType: ElementRuleType,
                        val archiveId : Int) : ElementRuleDTO(elementTypeId, ruleId, elementRuleType)
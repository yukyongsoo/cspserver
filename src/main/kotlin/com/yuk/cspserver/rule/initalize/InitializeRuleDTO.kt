package com.yuk.cspserver.rule.initalize

import com.yuk.cspserver.rule.RuleDTO
import com.yuk.cspserver.rule.RuleType

class InitializeRuleDTO(id: Int,
                        typeId: Int,
                        ruleType: RuleType,
                        val archiveId: Int) : RuleDTO(id, typeId, ruleType)
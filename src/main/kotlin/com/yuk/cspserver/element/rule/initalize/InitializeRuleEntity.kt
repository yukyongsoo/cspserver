package com.yuk.cspserver.element.rule.initalize

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_RULE_INIT")
data class InitializeRuleEntity(@Column("RULE_ID") val ruleId : Int,
                                @Column("ARCHIVE_ID") val archiveId : Int)
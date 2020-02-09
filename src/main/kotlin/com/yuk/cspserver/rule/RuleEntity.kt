package com.yuk.cspserver.rule

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_TYPE_RULE")
data class RuleEntity(@Column("TYPE_ID") val id: Int,
                      @Column("RULE_TYPE") val ruleType: Int)

@Table("CSP_TYPE_RULE")
data class RuleReadEntity(@Column("ID") val id: Int,
                          @Column("TYPE_ID") val typeId: Int,
                          @Column("RULE_TYPE") val ruleType: Int)
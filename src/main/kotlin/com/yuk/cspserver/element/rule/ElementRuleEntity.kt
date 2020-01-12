package com.yuk.cspserver.element.rule

import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT_TYPE_RULE")
data class ElementRuleEntity(@Column("ELEMENT_TYPE_ID") val elementTypeId : Int,
                             @Column("RULE_TYPE") val ruleType : Int)

@Table("CSP_ELEMENT_TYPE_RULE")
data class ElementRuleReadEntity(@Column("ELEMENT_TYPE_ID") val elementTypeId : Int,
                             @Column("RULE_ID") val ruleId : Int,
                             @Column("RULE_TYPE") val ruleType : Int)
package com.yuk.cspserver.element.rule

import org.springframework.data.relational.core.mapping.Table

@Table("CSP_ELEMENT_TYPE_RULE")
data class ElementRuleEntity(private val elementTypeId : Int,
                             private val ruleId : Int,
                             private val ruleType : Int)

insert into CSP_RULE_INIT(RULE_ID, ARCHIVE_ID)
values (1, 1);

insert into CSP_ELEMENT_TYPE_RULE (ELEMENT_TYPE_ID, RULE_ID,RULE_TYPE)
values (1, 1, 1);

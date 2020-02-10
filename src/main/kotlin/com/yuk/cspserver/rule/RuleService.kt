package com.yuk.cspserver.rule

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.common.BadStateException
import com.yuk.cspserver.rule.initalize.InitializeRuleCommandDAO
import com.yuk.cspserver.rule.initalize.InitializeRuleDTO
import com.yuk.cspserver.rule.initalize.InitializeRuleQueryDAO
import org.springframework.stereotype.Service

@Service
class RuleService(private val ruleQueryDAO: RuleQueryDAO,
                  private val ruleCommandDAO: RuleCommandDAO,
                  private val initializeRuleCommandDAO: InitializeRuleCommandDAO,
                  private val initializeRuleQueryDAO: InitializeRuleQueryDAO,
                  private val archiveService: ArchiveService) {

    suspend fun getInitializeRule(typeId: Int): List<InitializeRuleDTO> {
        val ruleList = ruleQueryDAO.findByElementTypeIdAndRuleType(typeId, RuleType.INITIALIZE)
        val initializeRuleList = initializeRuleQueryDAO.initializeRule(ruleList.map { it.id })

        val initialRuleDTOList = ruleList.map { rule ->
            val initializeRule = initializeRuleList.first { rule.id == it.ruleId }
            InitializeRuleDTO(rule.id, rule.typeId, RuleType.INITIALIZE, initializeRule.archiveId)
        }

        return if (initialRuleDTOList.isNotEmpty()) initialRuleDTOList
        else throw BadStateException("can't found any InitializeRule for elementType $typeId")
    }

    suspend fun createInitializeRule(typeId: Int, archiveId: Int) {
        val ruleId = ruleCommandDAO.createRule(typeId, RuleType.INITIALIZE.typeId)
        archiveService.getArchive(archiveId)
        initializeRuleCommandDAO.createInitializeRule(ruleId, archiveId)
    }
}
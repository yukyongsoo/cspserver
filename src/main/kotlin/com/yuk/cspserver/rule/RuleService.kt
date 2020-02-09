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
        ruleList.map {


        }


        val initialRuleList = list.map { InitializeRuleDTO(it.id, it.typeId, it.RuleType.INITIALIZE) }

        return if (list.isNotEmpty()) initialRuleList
        else throw BadStateException("can't found any InitializeRule for elementType $typeId")
    }

    suspend fun createInitializeRule(typeId: Int, archiveId: Int) {
        val ruleId = ruleCommandDAO.createRule(typeId, RuleType.INITIALIZE.typeId)
        archiveService.getArchive(archiveId)
        initializeRuleCommandDAO.createInitializeRule(ruleId, archiveId)
    }
}
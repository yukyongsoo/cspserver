package com.yuk.cspserver.rule

import com.yuk.cspserver.archive.ArchiveService
import com.yuk.cspserver.common.BadRequestException
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
        val ruleList = ruleQueryDAO.findByTypeIdAndRuleType(typeId, RuleType.INITIALIZE)
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

    suspend fun getAllRule() =
            ruleQueryDAO.getAllRule().map {
                convertEntityToDTO(it)
            }

    suspend fun getTypeRules(typeId: String) =
            ruleQueryDAO.findByTypeId(typeId).map {
                convertEntityToDTO(it)
            }

    suspend fun getRule(typeId: Int, ruleId: Int) =
            ruleQueryDAO.findByTypeIdAndRuleId(typeId, ruleId)
                    ?: throw BadRequestException("can't find any rule for typeId : $typeId and ruleId : $ruleId")

    suspend fun deleteRule(typeId: Int, ruleId: Int) {
        val rule = getRule(typeId, ruleId)
        when(rule.ruleType) {
            1 -> initializeRuleCommandDAO.deleteInitializeRule(ruleId)
        }
        ruleCommandDAO.deleteRule(typeId, ruleId)
    }


    private fun convertEntityToDTO(ruleReadEntity: RuleReadEntity): RuleReadDTO {
        return when (ruleReadEntity.ruleType) {
            1 -> RuleReadDTO(ruleReadEntity.id, ruleReadEntity.typeId, RuleType.INITIALIZE)
            2 -> RuleReadDTO(ruleReadEntity.id, ruleReadEntity.typeId, RuleType.MIGRATION)
            3 -> RuleReadDTO(ruleReadEntity.id, ruleReadEntity.typeId, RuleType.RETENTION)
            else ->
                throw BadStateException("current rule has not support type. rule id is ${ruleReadEntity.id}")
        }

    }
}
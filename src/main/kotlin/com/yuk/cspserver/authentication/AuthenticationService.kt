package com.yuk.cspserver.authentication

import com.yuk.cspserver.common.AuthException
import org.springframework.stereotype.Service

@Service
class AuthenticationService(private val authentication: Authentication) {
    suspend fun check(headers: Map<String, MutableList<String>>) {
        try{
            authentication.check(headers)
        }
        catch (e : Exception){
            throw AuthException("login check Fail",e)
        }
    }

    suspend fun login(headers: Map<String, MutableList<String>>, body: String?): String {
        try{
            return authentication.login(headers,body)
        }
        catch (e : Exception){
            throw AuthException("login check Fail",e)
        }
    }
}